package com.gerikk.birthdayapp.controllers;

import com.gerikk.birthdayapp.exceptions.UserNotFoundException;
import com.gerikk.birthdayapp.models.Birthday;
import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.services.BirthdayServiceImpl;
import com.gerikk.birthdayapp.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BirthdayServiceImpl birthdayService;

    @GetMapping({"", "/"})
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping({"", "/"})
    public ResponseEntity<User> createUser(@RequestBody User newUser) {

        userService.save(newUser);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Long id) {
        return userService.getAllUsers().stream().filter(user -> user.getId().equals(id)).findFirst().get();
    }

    @GetMapping("/{userId}/birthdays")
    public Set<Birthday> getBirthdays(@PathVariable("userId") Long id) {

        User user = userService.getUserById(id);

        return user.getBirthdays();
    }

    @PostMapping("/{userId}/birthdays")
    public ResponseEntity<Birthday> createBirthday(@PathVariable("userId") Long id, @RequestBody Birthday newBirthday) {

        User user = userService.getUserById(id);

        if (user == null) {
            throw new UserNotFoundException();
        } else {
            newBirthday.setUser(user);
            user.getBirthdays().add(newBirthday);
            birthdayService.save(newBirthday);
            return new ResponseEntity<>(newBirthday, HttpStatus.CREATED);
        }
    }

}
