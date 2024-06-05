package ru.nikitin.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nikitin.jwt.controller.base.BaseController;
import ru.nikitin.jwt.model.dto.FullUserData;
import ru.nikitin.jwt.model.dto.Result;
import ru.nikitin.jwt.service.UserService;

@RestController
@RequestMapping(path = "/private/admin")
public class AdminController extends BaseController {

    @Autowired
    private UserService service;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public FullUserData create(@RequestBody FullUserData data) {
        return service.create(data);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public FullUserData update(@RequestBody FullUserData data) {
        return service.create(data);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public FullUserData getUser(@PathVariable Long id) {
        return service.getUserById(id);
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Long id) {
        return service.deleteById(id);
    }


}
