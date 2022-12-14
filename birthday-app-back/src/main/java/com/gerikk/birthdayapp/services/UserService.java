package com.gerikk.birthdayapp.services;

import com.gerikk.birthdayapp.exceptions.UserAlreadyExistsException;
import com.gerikk.birthdayapp.exceptions.UserNotFoundException;
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
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User login(String username, String password) {

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Pas d'user");
        }
        else if (!passwordEncoder.matches(password, userOptional.get().getPassword())) {
            throw new UserNotFoundException("Pas de mot de passe");
        }

        return userOptional.get();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public User save(User user) {

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Déjà en base");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
