package com.aditya.jobify.validation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class JobStatusDeserializer extends JsonDeserializer<JobStatus> {
    @Override
    public JobStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        for (JobStatus status : JobStatus.values()) {
            if (status.getValue().equalsIgnoreCase(value)) {
                return status;
            }
        }
        return null;
//        throw new InvalidFormatException(p, "Invalid value for job status. Accepted values are: INTERVIEW, PENDING, DECLINED.", value, JobStatus.class); // or throw an exception
    }
}

