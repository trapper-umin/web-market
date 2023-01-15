package com.market.backend.util.Exception.Feedback;

public class FeedbackNotFoundException extends RuntimeException{
    public FeedbackNotFoundException(){}
    public FeedbackNotFoundException(String message){
        super(message);
    }
}