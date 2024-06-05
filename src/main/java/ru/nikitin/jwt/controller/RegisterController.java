package ru.nikitin.jwt.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitin.jwt.controller.base.BaseController;
import ru.nikitin.jwt.model.dto.UserData;
import ru.nikitin.jwt.service.UserService;

@RestController()
@RequestMapping(path = "/public/register", produces = MediaType.APPLICATION_JSON_VALUE)
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserData register(@Valid @RequestBody UserData user) {
        return userService.register(user);
    }
}
