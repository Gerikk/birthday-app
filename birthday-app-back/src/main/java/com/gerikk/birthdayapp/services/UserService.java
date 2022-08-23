package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.models.User;

import java.util.List;

public interface UserService {

    public User login(String username, String password);

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User save(User user);

}
