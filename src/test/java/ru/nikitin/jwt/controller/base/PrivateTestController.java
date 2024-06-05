package ru.nikitin.jwt.controller.base;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrivateTestController extends BaseTestController {

    @Test
    void shouldReturnUnauthorizedForUrlNotImplemented() throws Exception {
        ResponseEntity<String> forEntity = rest.getForEntity(privateUrl.concat("/test/today"), String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, forEntity.getStatusCode());
    }

}
