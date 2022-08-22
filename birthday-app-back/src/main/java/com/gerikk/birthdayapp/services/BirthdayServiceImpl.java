package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.models.Birthday;
import com.gerikk.birthdayapp.repositories.BirthdayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirthdayServiceImpl implements BirthdayService {

    @Autowired
    private BirthdayRepository birthdayRepository;

    @Autowired
    private UserService userService;


    @Override
    public List<Birthday> getAllBirthdays() {

        return birthdayRepository.findAll();
    }

    @Override
    public List<Birthday> getBirthdaysByUserId(Long userId) {

        return userService.getAllUsers().stream().filter(user -> user.getId().equals(userId)).findFirst().get().getBirthdays().stream().toList();
    }

    @Override
    public Birthday save(Birthday birthday) {
        return birthdayRepository.save(birthday);
    }
}
