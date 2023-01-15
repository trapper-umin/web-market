package com.market.backend.dto;

import java.util.List;

public class PersonDTOResponse {

    private String username;

    private List<ProductDTO> products;

    public PersonDTOResponse(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
