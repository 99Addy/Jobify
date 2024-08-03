package com.aditya.jobify.validation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class JobTypeDeserializer extends JsonDeserializer<JobType> {
    @Override
    public JobType deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        for (JobType type : JobType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
//        throw new InvalidFormatException(p, "Invalid value for job status. Accepted values are: INTERVIEW, PENDING, DECLINED.", value, JobStatus.class); // or throw an exception
    }
}
