package com.market.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message = "Name should be not empty")
    @Size(min=2,max = 255,message = "Name size should be between 2 and 255")
    private String name;

    public PersonDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
