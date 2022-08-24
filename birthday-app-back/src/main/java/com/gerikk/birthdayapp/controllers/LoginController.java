package com.gerikk.birthdayapp.controllers;

import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public UserDetails login(@RequestBody User user){

        return userService.login(user.getUsername(), user.getPassword());
    }



}
