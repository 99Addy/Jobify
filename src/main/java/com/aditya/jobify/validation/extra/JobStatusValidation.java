//package com.aditya.jobify.validation.extra;
//
//
//import com.aditya.jobify.validation.JobStatus;
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//public class JobStatusValidation implements ConstraintValidator<JobStatusValidator, String> {
//
//    @Override
//    public void initialize(JobStatusValidator constraintAnnotation) {
//    }
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        if (value == null) {
//            return false; // null values are valid, use @NotNull to enforce not null
//        }
//
//        try {
//            JobStatus.fromValue(value);
//            return true;
//        } catch (IllegalArgumentException ex) {
//            return false;
//        }
//    }
//}
//
