package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.exceptions.UserAlreadyExistsException;
import com.gerikk.birthdayapp.exceptions.UserNotFoundException;
import com.gerikk.birthdayapp.models.Role;
import com.gerikk.birthdayapp.models.User;
import com.gerikk.birthdayapp.repositories.UserRepository;
import com.gerikk.birthdayapp.security.MyUserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails login(String username, String password) {

        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        this.loadUserByUsername(userOptional.get().getUsername());

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

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Déjà en base");
        }

        Role roleUser = roleService.getRoleByUsername("ROLE_USER");

        user.getRoles().add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        return new MyUserPrincipal(userOptional.get());
    }
}
