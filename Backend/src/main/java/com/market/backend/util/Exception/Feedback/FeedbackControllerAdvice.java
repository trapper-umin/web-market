package com.market.backend.util.Exception.Feedback;

import com.market.backend.util.Exception.Product.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.time.LocalDateTime;

@ControllerAdvice(annotations = FeedbackExceptionHandler.class)
public class FeedbackControllerAdvice {

    @ExceptionHandler
    private ResponseEntity<FeedbackErrorResponse> handleException(FeedbackNotFoundException e){
        FeedbackErrorResponse response=new FeedbackErrorResponse(
                "Feedback wasn't found",
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<FeedbackErrorResponse> handleException(FeedbackNotCreatedException e){
        FeedbackErrorResponse response=new FeedbackErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<FeedbackErrorResponse> handleException(FeedbackNotUpdatedException e){
        FeedbackErrorResponse response=new FeedbackErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<FeedbackErrorResponse> handleException(FeedbackNotDeletedException e){
        FeedbackErrorResponse response = new FeedbackErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<FeedbackErrorResponse> handleException(ProductNotFoundException e){
        FeedbackErrorResponse response=new FeedbackErrorResponse(
                e.getMessage(),
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
