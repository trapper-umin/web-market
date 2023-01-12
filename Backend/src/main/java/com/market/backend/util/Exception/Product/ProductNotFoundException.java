package com.market.backend.util.Exception.Product;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(){}
    public ProductNotFoundException(String message){
        super(message);
    }
}
