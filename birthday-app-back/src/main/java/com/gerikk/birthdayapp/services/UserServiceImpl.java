package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.exceptions.UserAlreadyExistsException;
import com.gerikk.birthdayapp.exceptions.UserNotFoundException;
import com.gerikk.birthdayapp.models.Role;
import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.repositories.UserRepository;
import com.gerikk.birthdayapp.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public UserDetails login(String username, String password) {

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        return new MyUserPrincipal(userOptional.get());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User save(User user) {

        if (userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent()) {
            throw new UserAlreadyExistsException("Déjà en base");
        }

        Role roleUser = roleService.getRoleByUsername("ROLE_USER");

        user.getRoles().add(roleUser);
        this.userRepository.save(user);
        return user;
    }

}
