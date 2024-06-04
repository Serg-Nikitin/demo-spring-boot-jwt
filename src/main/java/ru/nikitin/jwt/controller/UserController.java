package ru.nikitin.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitin.jwt.model.dto.FullUserData;
import ru.nikitin.jwt.model.dto.UserData;
import ru.nikitin.jwt.model.security.AuthUser;
import ru.nikitin.jwt.model.security.JwtPrincipal;
import ru.nikitin.jwt.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/private/users")
public class UserController extends BaseController{

    @Autowired
     private UserService service;

//    @PreAuthorize("hasAnyRole('USER','ADMIN')")
//    @GetMapping
//    public FullUserData getUser(@AuthenticationPrincipal JwtPrincipal principal){
//        return service.getUserById(principal.getId());
//    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/all")
    public List<FullUserData> getAll(){
        return service.getAll();
    }


}
