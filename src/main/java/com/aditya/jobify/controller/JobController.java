package com.aditya.jobify.controller;

import com.aditya.jobify.model.Job;
import com.aditya.jobify.response.ResponseWrapper;
import com.aditya.jobify.service.JobService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs(HttpServletRequest request){
        System.out.println("Controller called");
        return jobService.getAllJobs(request);
    }

    @GetMapping("{id}")
    public ResponseEntity<Job> getJob(@PathVariable int id){
        return jobService.getJob(id);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@Valid @RequestBody Job job, HttpServletRequest request){
//        System.out.println("Controller called with Job :" + job.toString());
        return jobService.createJob(job, request);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseWrapper<Job>> updateJob(@RequestBody Job job, @PathVariable int id){
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseWrapper<Job>> deleteJob(@PathVariable int id){
        return jobService.deleteJob(id);
    }
}















