package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.models.Birthday;
import com.gerikk.birthdayapp.repositories.BirthdayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BirthdayService {

    private final BirthdayRepository birthdayRepository;

    public BirthdayService(BirthdayRepository birthdayRepository) {
        this.birthdayRepository = birthdayRepository;
    }

    public List<Birthday> getAllBirthdays() {

        return birthdayRepository.findAll();
    }

    public Birthday save(Birthday birthday) {
        return birthdayRepository.save(birthday);
    }
}
