package com.market.backend.util.Exception.Feedback;

public class FeedbackNotDeletedException extends RuntimeException{
    public FeedbackNotDeletedException(String message){
        super(message);
    }
}
