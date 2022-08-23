package com.gerikk.birthdayapp.controllers;

import com.gerikk.birthdayapp.dto.UserDto;
import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public User login(@RequestBody UserDto userDto){
        return userService.login(userDto.getUsername(), userDto.getPassword());
    }



}
