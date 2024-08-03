package com.aditya.jobify.repository;

import com.aditya.jobify.model.User;
import com.aditya.jobify.validation.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    User findByRole(Role role);
}
