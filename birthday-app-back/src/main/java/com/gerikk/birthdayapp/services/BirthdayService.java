package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.models.Birthday;
import com.gerikk.birthdayapp.repositories.BirthdayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirthdayService {

    private final BirthdayRepository birthdayRepository;

    private final UserService userService;

    public BirthdayService(BirthdayRepository birthdayRepository, UserService userService) {
        this.birthdayRepository = birthdayRepository;
        this.userService = userService;
    }

    public List<Birthday> getAllBirthdays() {

        return birthdayRepository.findAll();
    }

    public List<Birthday> getBirthdaysByUserId(Long userId) {

        return userService.getAllUsers().stream().filter(user -> user.getId().equals(userId)).findFirst().get().getBirthdays().stream().toList();
    }

    public Birthday save(Birthday birthday) {
        return birthdayRepository.save(birthday);
    }
}
