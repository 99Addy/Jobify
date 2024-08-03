//package com.aditya.jobify.validation.extra;
//
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//
//@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
//@Retention(RetentionPolicy.RUNTIME)
//@Constraint(validatedBy = JobStatusValidation.class)
//public @interface JobStatusValidator {
//    String message() default "Invalid job status";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//}
//
