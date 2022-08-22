package com.gerikk.birthdayapp.controllers;

import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping({"", "/"})
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Long id) {
        return userService.getAllUsers().stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

}
