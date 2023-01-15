package com.market.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTOAuth {

    @NotEmpty(message = "Username should be not empty")
    @Size(max=255,min=4, message = "Username should be between 4 and 255")
    private String username;

    @NotEmpty(message = "Password should be not empty")
    @Size(max=255,min=4, message = "Password should be between 4 and 255")
    private String password;

    public PersonDTOAuth(){}

    public PersonDTOAuth(String username, String password) {
        this.username = username;
        this.password = password;
    }

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
