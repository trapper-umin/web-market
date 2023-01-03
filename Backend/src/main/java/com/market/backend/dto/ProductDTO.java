package com.market.backend.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProductDTO {

    @NotEmpty(message = "Product name should be not empty")
    @Size(min = 3,max = 255,message = "Product name size should be between 3 and 255")
    private String name;

    @NotNull(message = "Quantity should be not null")
    @Min(value = 0,message = "The quantity must be greater than 0")
    private int quantity;

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
}
