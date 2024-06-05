package ru.nikitin.jwt.controller.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.model.dto.FullUserData;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.model.dto.UserData;

import java.util.List;

import static ru.nikitin.jwt.controller.data.TestData.*;

@ExtendWith(SpringExtension.class)
@Rollback
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseTestController {


    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer("postgres:14.11-alpine3.19");

    @DynamicPropertySource
    static void register(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    public static String privateUrl = "/private";
    public static String users = privateUrl.concat("/users");
    public static String getAll = users.concat("/all");
    public static String publicUrl = "/public";
    public static String register = publicUrl.concat("/register");
    public static String token = publicUrl.concat("/token");
    public static String login = token.concat("/login");
    public static String admin = privateUrl.concat("/admin");


    @Autowired
    public void setRest(TestRestTemplate rest) {
        this.rest = rest;
    }

    protected TestRestTemplate rest;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    protected <T> ResponseEntity<T> postResponse(String url, Object obj, Class<T> clazz) {
        return rest.postForEntity(url, obj, clazz);
    }


    public ResponseEntity<List> forUserGetAll() {
        String token = getToken(getUser());
        var headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity(headers);
        return rest.exchange(getAll, HttpMethod.GET, entity, List.class);
    }

    public ResponseEntity<FullUserData> forAdminGetById() {
        String token = getToken(getAdmin());
        var headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity(headers);
        return rest.exchange(admin.concat("/1"), HttpMethod.GET, entity, FullUserData.class);
    }

    public ResponseEntity<FullUserData> forUserGetById() {
        String token = getToken(getUser());
        var headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity entity = new HttpEntity(headers);
        return rest.exchange(admin.concat("/1"), HttpMethod.GET, entity, FullUserData.class);
    }


    private String getToken(User user) {
        TokenResponse data = registerAndLogInUser(user).getBody();
        return data.token();
    }

    private ResponseEntity<TokenResponse> registerAndLogInUser(User user) {
        registerUser(user);
        ResponseEntity<TokenResponse> tokenResponseResponseEntity = loginUser(user);
        return tokenResponseResponseEntity;
    }


    public ResponseEntity<UserData> registerUser(User user) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(convertTo(user), headers);
        return postResponse(register, entity, UserData.class);
    }

    public ResponseEntity<UserData> registerUser() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(convertTo(getUser()), headers);
        return postResponse(register, entity, UserData.class);
    }

    public ResponseEntity<TokenResponse> loginUser(User user) {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(convertTo(getCreds(user)), headers);
        return postResponse(login, entity, TokenResponse.class);
    }
}
