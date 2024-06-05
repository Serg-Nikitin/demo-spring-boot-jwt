package ru.nikitin.jwt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.nikitin.jwt.controller.base.PrivateTestController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class UserControllerTest extends PrivateTestController {

    @Test
    public void shouldReturnUnauthorizedForNotSecurityRequest() {
        ResponseEntity<List> forEntity = rest.getForEntity(getAll, List.class);
        assertEquals(HttpStatus.UNAUTHORIZED, forEntity.getStatusCode());
    }

    @Test
    public void shouldReturnListUsersForRoleUser() {
        ResponseEntity<List> listResponseEntity = forUserGetAll();
        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());
    }

}