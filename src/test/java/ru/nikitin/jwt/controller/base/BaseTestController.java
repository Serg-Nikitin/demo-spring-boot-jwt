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
import ru.nikitin.jwt.controller.data.TestData;
import ru.nikitin.jwt.model.dto.TokenData;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.model.dto.UserData;

import static ru.nikitin.jwt.controller.data.TestData.*;

@ExtendWith(SpringExtension.class)
//@Testcontainers
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
    public static String refresh = publicUrl.concat("/refresh");

    @Autowired
    protected TestRestTemplate rest;

    private HttpHeaders headers = new HttpHeaders();
    private StringBuilder url = new StringBuilder();
    private String body = null;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }


//    protected <T> ResponseEntity<T> requestPost(Class<T> clazz) {
//        HttpEntity entity = new HttpEntity(body, headers);
//        return postResponse(url.toString(), entity, clazz);
//
//    }

    protected <T> ResponseEntity<T> requestGet(Class<T> clazz) {
        return rest.getForEntity(this.url.toString(), clazz);
    }

    private BaseTestController addHeaders(String name, String value) {
        headers.add(name, value);
        return this;
    }

    private BaseTestController addUrl(String url) {
        this.url.append(url);
        return this;
    }

    private BaseTestController setBody(Object object) {
        body = TestData.convertTo(object);
        return this;
    }


    public HttpEntity<String> getRequest(String json) {
        var headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(json, headers);
    }

    protected HttpEntity<String> createRequestWithBody(Object object) {
        return getRequest(convertTo(object));
    }

    protected <T> ResponseEntity<T> postResponse(String url, Object obj, Class<T> clazz) {
        return rest.postForEntity(url, obj, clazz);
    }


    public ResponseEntity<UserData> forUserGetAll() {
        String token = getToken();
        var  headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        return rest.exchange(getAll, HttpMethod.GET,entity, UserData.class);
    }

    private String getToken(){
        TokenData data = convertFrom(registerAndLogInUser().getBody().toString(), TokenData.class);
        return data.token();
    }

    private ResponseEntity<TokenResponse> registerAndLogInUser() {
        registerUser();
        return loginUser();
    }

    public ResponseEntity<UserData> registerUser() {
        var  headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(convertTo(getUser()), headers);
        return postResponse(register, entity, UserData.class);
    }

    public ResponseEntity<TokenResponse> loginUser() {
        var  headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(convertTo(getUser()), headers);
        return postResponse(register, entity, TokenResponse.class);
    }
}
