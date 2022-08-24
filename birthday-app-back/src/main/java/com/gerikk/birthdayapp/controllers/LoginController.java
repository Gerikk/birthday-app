package com.gerikk.birthdayapp.controllers;

import com.gerikk.birthdayapp.dto.LoginDto;
import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginDto loginDto) {

        return userService.login(loginDto.getUsername(), loginDto.getPassword());
    }


}
