package com.gerikk.birthdayapp.controllers;

import com.gerikk.birthdayapp.models.Birthday;
import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.services.BirthdayService;
import com.gerikk.birthdayapp.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final BirthdayService birthdayService;


    public UserController(UserService userService, BirthdayService birthdayService) {
        this.userService = userService;
        this.birthdayService = birthdayService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping({"", "/"})
    public ResponseEntity<User> createUser(@RequestParam User user) {

        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") Long id) {

        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{userId}/birthdays")
    public Set<Birthday> getBirthdays(@PathVariable("userId") Long id) {

        User user = userService.getUserById(id);

        return user.getBirthdays();
    }

    @PostMapping("/{userId}/birthdays")
    public ResponseEntity<Birthday> createBirthday(
            @PathVariable("userId") Long id,
            @RequestParam("firstname") final String firstname,
            @RequestParam("lastname") final String lastname,
            @RequestParam("date") final String date) {
        try {
            User user = userService.getUserById(id);
            Birthday newBirthday = new Birthday(null, LocalDate.parse(date), firstname, lastname, user);
            return ResponseEntity.ok(birthdayService.save(newBirthday));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{userId}/birthdays/{birthdayId}")
    public ResponseEntity<Birthday> updateBirthday(
            @PathVariable("userId") Long id,
            @PathVariable("birthdayId") Long birthdayId,
            @RequestParam("firstname") final String firstname,
            @RequestParam("lastname") final String lastname,
            @RequestParam("date") final String date) {
        try {
            User user = userService.getUserById(id);
            Birthday birthday = new Birthday(birthdayId, LocalDate.parse(date), firstname, lastname, user);

            return ResponseEntity.ok(birthdayService.save(birthday));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
