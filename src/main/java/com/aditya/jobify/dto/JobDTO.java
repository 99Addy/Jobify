package com.aditya.jobify.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class JobDTO {

    @NotBlank(message = "Position is required.")
    private String position;

    @NotBlank(message = "Company is required")
    private String company;

    @NotBlank(message = "Job Status is required")
//    @JobStatusValidator
    private String jobStatus;

    @NotBlank(message = "Job Type is required")
    private String jobType;

    @NotBlank(message = "Job location is required")
    private String jobLocation;


}

