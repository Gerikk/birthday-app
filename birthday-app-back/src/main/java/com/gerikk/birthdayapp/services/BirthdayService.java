package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.models.Birthday;

import java.util.List;

public interface BirthdayService {

    public List<Birthday> getAllBirthdays();

    public List<Birthday> getBirthdaysByUserId(Long userId);

    public Birthday save(Birthday birthday);
}
