package com.market.backend.dto;

import java.util.List;

public class PersonDTOResponse {

    private String name;

    private List<ProductDTO> productDTOS;

    public PersonDTOResponse(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getProductDTOS() {
        return productDTOS;
    }

    public void setProductDTOS(List<ProductDTO> productDTOS) {
        this.productDTOS = productDTOS;
    }
}
