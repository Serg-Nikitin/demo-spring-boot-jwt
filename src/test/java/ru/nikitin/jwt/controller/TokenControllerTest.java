package ru.nikitin.jwt.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.nikitin.jwt.model.Role;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.model.dto.TokenData;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.service.JwtSecurityService;
import ru.nikitin.jwt.utils.ConverterToJson;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(TokenController.class)
class TokenControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @MockBean
    private JwtSecurityService securityService;


    @Test
    void login() throws Exception {
        User user = new User(1L, "admin", "secret", Set.of(Role.ADMIN, Role.USER));
        TokenData tokenData = new TokenData("first", "second");
        TokenResponse tokenResp = new TokenResponse(tokenData);
        when(securityService.login("admin", "secret")).thenReturn(tokenResp);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/token/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ConverterToJson.convert(user)))
                .andExpect(status().isOk());
    }

//    @Test
//    void refresh() {
//        TokenData tokenData = new TokenData("first", "second");
//        System.out.println(tokenData);
//    }
}