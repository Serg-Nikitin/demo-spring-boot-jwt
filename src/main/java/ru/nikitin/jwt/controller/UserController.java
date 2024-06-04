package ru.nikitin.jwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/users")
public class UserController extends BaseController{


    @GetMapping
    public String getUser(){
        return "test";
    }


}
