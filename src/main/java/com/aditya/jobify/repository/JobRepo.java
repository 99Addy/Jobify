package com.aditya.jobify.repository;

import com.aditya.jobify.model.Job;
import com.aditya.jobify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepo extends JpaRepository<Job, Integer> {
    List<Job> findByCreatedBy(User user);
}
