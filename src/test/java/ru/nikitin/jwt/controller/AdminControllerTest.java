package ru.nikitin.jwt.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.nikitin.jwt.controller.base.BaseTestController;
import ru.nikitin.jwt.model.dto.FullUserData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminControllerTest extends BaseTestController {

    @Test
    public void shouldReturnFullUserById() {
        ResponseEntity<FullUserData> fullUserDataResponseEntity = forAdminGetById();
        assertEquals(HttpStatus.OK, fullUserDataResponseEntity.getStatusCode());
    }

    @Test
    public void shouldDontReturnFullUserById() {
        ResponseEntity<FullUserData> fullUserDataResponseEntity = forUserGetById();
        assertEquals(HttpStatus.FORBIDDEN, fullUserDataResponseEntity.getStatusCode());
    }


}
