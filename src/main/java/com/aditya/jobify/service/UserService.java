package com.aditya.jobify.service;

import com.aditya.jobify.exception.UserAlreadyExistException;
import com.aditya.jobify.repository.UserRepo;
import com.aditya.jobify.model.User;
import com.aditya.jobify.response.ResponseWrapper;
import com.aditya.jobify.validation.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<ResponseWrapper<User>> addUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user.getPassword());

        if(userRepo.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyExistException("User with email " + user.getEmail() + " already exists");
        }
        if(userRepo.findByRole(Role.ADMIN) == null) {
            user.setRole(Role.ADMIN);
        }
        User savedUser = userRepo.save(user);
        return new ResponseEntity<>(new ResponseWrapper<>(savedUser, "User Saved"), HttpStatus.CREATED);

    }
}
















