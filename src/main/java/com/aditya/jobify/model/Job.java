package com.aditya.jobify.model;

import com.aditya.jobify.validation.JobStatus;
import com.aditya.jobify.validation.JobStatusDeserializer;
import com.aditya.jobify.validation.JobType;
import com.aditya.jobify.validation.JobTypeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Position is required.")
    private String position;

    @NotBlank(message = "Company is required")
    private String company;

    @Enumerated(EnumType.STRING)
    //@Enumerated specify field should be persisted as an enumerated type
    //EnumType.STRING specifies how the enum should be stored in the database.
    @Column(nullable = false)
    @NotNull(message = "Valid Job status is required")
    @JsonDeserialize(using = JobStatusDeserializer.class)
    private JobStatus jobStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Valid Job Type is required")
    @JsonDeserialize(using = JobTypeDeserializer.class)
    private JobType jobType;

    @NotBlank(message = "Job location is required")
    private String jobLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    //@ManyToOne: This annotation is used to define a many-to-one relationship between two entities,
    // it means that many Job entities can be associated with one User entity,
    // a single user can create multiple job listings.
    //LAZY fetching means that the related entity (in this case, User) will not be immediately loaded when the Job entity is loaded from the database. Instead, it will be loaded on demand when the createdBy field is accessed for the first time. This can improve performance by avoiding unnecessary loading of related entities.
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Getters and setters

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        if (this.jobStatus == null) {
            this.jobStatus = JobStatus.PENDING;
        }
        if (this.jobType == null) {
            this.jobType = JobType.FULL_TIME;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}

