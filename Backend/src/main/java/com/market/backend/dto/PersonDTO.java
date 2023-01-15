package com.market.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message = "Name should be not empty")
    @Size(min=2,max = 255,message = "Name size should be between 2 and 255")
    private String username;
    @NotEmpty(message = "Name should be not empty")
    @Size(min=2,max = 255,message = "Name size should be between 2 and 255")
    private String password;

    public PersonDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
