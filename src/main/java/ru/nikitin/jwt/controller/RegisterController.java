package ru.nikitin.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitin.jwt.model.dto.UserData;
import ru.nikitin.jwt.service.UserService;

@RestController()
@RequestMapping("/public/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserData register(@RequestBody UserData user) {
        return userService.register(user);
    }
}
