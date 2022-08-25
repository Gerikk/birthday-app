package com.gerikk.birthdayapp.controllers;

import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User login(@RequestParam("username") final String username,
                      @RequestParam("password") final String password) {

        return userService.login(username, password);
    }


}
