package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.exceptions.UserNotFoundException;
import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        return userOptional.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
