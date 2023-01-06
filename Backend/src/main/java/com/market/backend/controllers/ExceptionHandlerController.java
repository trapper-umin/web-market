package com.market.backend.controllers;

import com.market.backend.util.Exception.Feedback.FeedbackErrorResponse;
import com.market.backend.util.Exception.Feedback.FeedbackNotDeletedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

//For ExceptionHandler method
public class ExceptionHandlerController {

    @ExceptionHandler
    private ResponseEntity<FeedbackErrorResponse> handleException(FeedbackNotDeletedException e){
        FeedbackErrorResponse response = new FeedbackErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}
