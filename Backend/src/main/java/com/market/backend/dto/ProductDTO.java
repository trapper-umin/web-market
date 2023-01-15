package com.market.backend.dto;

import com.market.backend.models.Feedback;
import jakarta.validation.constraints.*;

import java.util.List;

public class ProductDTO {

    @NotEmpty(message = "Product name should be not empty")
    @Size(min = 3,max = 255,message = "Product name size should be between 3 and 255")
    private String name;

    @NotNull(message = "Quantity should be not null")
    @Min(value = 0, message = "The quantity must be greater than 0")
    private int quantity;

    @NotEmpty(message = "Description should be not empty")
    @Size(max = 1000,message = "Max size description is 1000 characters")
    private String description;

    public ProductDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
