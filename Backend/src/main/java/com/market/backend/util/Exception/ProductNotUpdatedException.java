package com.market.backend.util.Exception;

public class ProductNotUpdatedException extends RuntimeException{
    public ProductNotUpdatedException(String message){
        super(message);
    }
}
