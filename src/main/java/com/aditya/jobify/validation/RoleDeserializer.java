package com.aditya.jobify.validation;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class RoleDeserializer extends JsonDeserializer<Role> {
    @Override
    public Role deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        System.out.println("Value: " + value);
        for (Role role : Role.values()) {
            if (role.getValue().equalsIgnoreCase(value)) {
                return role;
            }
        }
        return Role.USER;
//        throw new InvalidFormatException(p, "Invalid value for job status. Accepted values are: INTERVIEW, PENDING, DECLINED.", value, JobStatus.class); // or throw an exception
    }
}
