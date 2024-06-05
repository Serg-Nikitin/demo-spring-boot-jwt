package ru.nikitin.jwt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import ru.nikitin.jwt.controller.base.PublicTestController;
import ru.nikitin.jwt.model.dto.TokenCredentialRequest;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.model.dto.UserData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.nikitin.jwt.controller.data.TestData.*;

public class RegisterTestController extends PublicTestController {

    @Test
    public void shouldRegisterUser() {
        UserData data = new UserData(getUser());


        ResponseEntity<UserData> userDataResponseEntity = registerUser();
        assertEquals(data, userDataResponseEntity.getBody());
        assertEquals(HttpStatus.OK, userDataResponseEntity.getStatusCode());
    }

    @Test
    public void shouldReturnUnauthorizedIfIncorrectData() {
        TokenCredentialRequest creds = getTokenCreds(getUser());
        ResponseEntity<TokenResponse> entity = postResponse(register, creds, TokenResponse.class);

        assertEquals(HttpStatus.UNAUTHORIZED, entity.getStatusCode());
    }

}
