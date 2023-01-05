package com.market.backend.dto;

import com.market.backend.models.Feedback;

import java.util.List;

public class ProductDTOResponse {

    private String name;

    private int quantity;

    private String description;

    private double rating;


    public ProductDTOResponse(){}

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
