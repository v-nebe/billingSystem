package com.shavneva.billingsystemserver.service;

import com.shavneva.billingsystemserver.entities.LogInResponse;
import com.shavneva.billingsystemserver.entities.User;
import com.shavneva.billingsystemserver.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User newUser) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setEmail(newUser.getEmail());
            existingUser.setFirstName(newUser.getFirstName());
            existingUser.setLastName(newUser.getLastName());
            existingUser.setNumber(newUser.getNumber());
            existingUser.setPassword(newUser.getPassword());
            return userRepository.save(existingUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow();
    }

    public UserDetails loadUserByNumber(String username) throws UsernameNotFoundException {
        return userRepository.findByNumber(username)
                .orElseThrow();
    }

    public LogInResponse logIn(String login, String password, HttpServletResponse response) {
        LogInResponse logInresponse = new LogInResponse();
        try {
            if (login.contains("@")) {
                logInresponse.setUser(loadUserByUsername(login));
            } else {
                logInresponse.setUser(loadUserByNumber(login));
            }
            if(Objects.equals(password, logInresponse.getUser().getPassword())){
                logInresponse.setErrorMessage("Login is successful");
                return logInresponse;
            }else{
                logInresponse.setUser(null);
                logInresponse.setErrorMessage("Password isn't correct");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logInresponse.setErrorMessage("Login isn't correct");
        }
        return logInresponse;
    }
}