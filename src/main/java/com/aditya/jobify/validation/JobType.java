package com.aditya.jobify.validation;

public enum JobType {
    FULL_TIME("full_time"),
    PART_TIME("part_time"),
    INTERNSHIP("internship");

    private final String type;

    JobType(String type){
        this.type = type;
    }

    public String getValue() {
        return type;
    }
}
