package ru.nikitin.jwt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.nikitin.jwt.controller.base.BaseTestController;
import ru.nikitin.jwt.model.dto.TokenCredentialRequest;
import ru.nikitin.jwt.model.dto.TokenResponse;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.nikitin.jwt.controller.data.TestData.getTokenCreds;
import static ru.nikitin.jwt.controller.data.TestData.getUser;


class TokenControllerTest extends BaseTestController {


    @Test
    public void shouldLogInUserAndReturnTokenData() {
        registerUser();

        ResponseEntity<TokenResponse> entity = loginUser();
        System.out.println(entity.getBody());
        assertTrue( !Objects.requireNonNull(entity.getBody()).toString().isEmpty());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void shouldRefreshAccessTokenAndReturnTokenData() {

    }


}