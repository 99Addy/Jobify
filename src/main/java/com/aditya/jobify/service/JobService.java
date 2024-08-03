package com.aditya.jobify.service;

import com.aditya.jobify.exception.NoSuchJobExistException;
import com.aditya.jobify.model.Job;
import com.aditya.jobify.model.User;
import com.aditya.jobify.repository.JobRepo;
import com.aditya.jobify.repository.UserRepo;
import com.aditya.jobify.response.ResponseWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<Job> getJob(int id) {
        Job existingJob = jobRepo.findById(id).orElse(null);
        if(existingJob == null){
            throw new NoSuchJobExistException("No Job with given Id found");
        }
        return new ResponseEntity<>(existingJob, HttpStatus.OK);
    }

    public ResponseEntity<Job> createJob(Job job, HttpServletRequest request) {
//        System.out.println("Service called with Job :" + job.toString());
        String userEmail = (String) request.getAttribute("email");
        User currentUser = userRepo.findByEmail(userEmail);
        job.setCreatedBy(currentUser);
        return new ResponseEntity<>(jobRepo.save(job), HttpStatus.CREATED);
    }

    public ResponseEntity<List<Job>> getAllJobs(HttpServletRequest request) {
        String userEmail = (String) request.getAttribute("email");
        User currentUser = userRepo.findByEmail(userEmail);
        return new ResponseEntity<>(jobRepo.findByCreatedBy(currentUser), HttpStatus.OK);
    }

    public ResponseEntity<ResponseWrapper<Job>> updateJob(int id, Job job) {
        Job existingJob = jobRepo.findById(id).orElse(null);
        if(existingJob == null){
            throw new NoSuchJobExistException("No Job with given Id found");
        }
        if (job.getPosition() != null) {
            existingJob.setPosition(job.getPosition());
        }
        if (job.getCompany() != null) {
            existingJob.setCompany(job.getCompany());
        }
        if (job.getJobStatus() != null) {
            existingJob.setJobStatus(job.getJobStatus());
        }
        if (job.getJobType() != null) {
            existingJob.setJobType(job.getJobType());
        }
        if (job.getJobLocation() != null) {
            existingJob.setJobLocation(job.getJobLocation());
        }
        existingJob.setUpdatedAt(LocalDateTime.now());

        return new ResponseEntity<>(new ResponseWrapper<>(jobRepo.save(existingJob),"Job updated"), HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseWrapper<Job>> deleteJob(int id) {
        Job job = jobRepo.findById(id).orElse(null);
        if(job == null){
            throw new NoSuchJobExistException("No Job with given Id found");
        }
        jobRepo.deleteById(id);
        return new ResponseEntity<>(new ResponseWrapper<>(job,"Job deleted"), HttpStatus.OK);
    }

}





