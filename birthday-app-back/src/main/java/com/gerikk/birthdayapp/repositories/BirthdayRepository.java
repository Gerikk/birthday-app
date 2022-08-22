package com.gerikk.birthdayapp.repositories;

import com.gerikk.birthdayapp.models.Birthday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BirthdayRepository extends JpaRepository<Birthday, Long> {



}
