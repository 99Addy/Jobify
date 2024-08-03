package com.aditya.jobify.validation;

public enum JobStatus {
    INTERVIEW("interview"),
    PENDING("pending"),
    DECLINED("declined");

    private String value;

    JobStatus(String value){
        this.value = value;
    }
//
    public String getValue() {
        return value;
    }
}
