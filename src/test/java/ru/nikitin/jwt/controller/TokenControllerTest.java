package ru.nikitin.jwt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.nikitin.jwt.controller.base.BaseTestController;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.model.dto.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nikitin.jwt.controller.data.TestData.getUser;


class TokenControllerTest extends BaseTestController {

    @Test
    public void shouldLogInUserAndReturnTokenData() {
        UserData data = new UserData(getUser());
        ResponseEntity<UserData> userDataResponseEntity = registerUser();
        assertEquals(data, userDataResponseEntity.getBody());
        assertEquals(HttpStatus.OK, userDataResponseEntity.getStatusCode());

        ResponseEntity<TokenResponse> entity = loginUser(getUser());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void shouldRefreshAccessTokenAndReturnTokenData() {

    }


}