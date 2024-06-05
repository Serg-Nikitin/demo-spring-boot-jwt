package ru.nikitin.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitin.jwt.controller.base.BaseController;
import ru.nikitin.jwt.model.dto.UserData;
import ru.nikitin.jwt.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/private/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends BaseController {

    @Autowired
     private UserService service;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/all")
    public List<UserData> getAll() {
        return service.getAll();
    }
}
