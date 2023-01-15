package com.market.backend.util.Exception.Product;

public class ProductAlreadyExistException extends RuntimeException{
    public ProductAlreadyExistException(String message){
        super(message);
    }
}
