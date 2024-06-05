package ru.nikitin.jwt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.nikitin.jwt.controller.base.PrivateTestController;
import ru.nikitin.jwt.model.dto.UserData;

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
        ResponseEntity<UserData> listResponseEntity = forUserGetAll();
        System.out.println(listResponseEntity.getBody());
        assertEquals(HttpStatus.OK, listResponseEntity.getStatusCode());

    }

}