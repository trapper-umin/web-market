package com.market.backend.util.Exception.Person;

public class PersonNotUpdatedException extends RuntimeException{
    public PersonNotUpdatedException(String message){
        super(message);
    }
}
