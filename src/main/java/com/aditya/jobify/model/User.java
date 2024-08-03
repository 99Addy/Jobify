package com.aditya.jobify.model;

import com.aditya.jobify.validation.Role;
import com.aditya.jobify.validation.RoleDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Email
    @PrimaryKeyJoinColumn
    @NotNull(message = "Email cannot be blank")
    private String email;

    @Column(nullable = false)
    @Size(min = 3, message = "Username must be at least 3 characters long")
    @NotNull(message = "Name cannot be blank")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Password cannot be blank")
    private String password;

    private String lastname;

    private String location;

    @Enumerated(EnumType.STRING)
    @JsonDeserialize(using = RoleDeserializer.class)
    private Role role;

    @PrePersist
    protected void onCreate() {
        if(this.lastname == null){
            this.lastname = "lastname";
        }
        if(this.location == null){
            this.location = "my city";
        }
    }

}

















