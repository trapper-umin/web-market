package com.market.backend.util.Exception.Person;

public class PersonOperationNotDoneException extends RuntimeException{
    public PersonOperationNotDoneException(String message){
        super(message);
    }
}
