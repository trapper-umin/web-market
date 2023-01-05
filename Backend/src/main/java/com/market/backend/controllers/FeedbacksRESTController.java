package com.market.backend.controllers;

import com.market.backend.dto.FeedbackDTO;
import com.market.backend.models.Feedback;
import com.market.backend.services.FeedbacksService;
import com.market.backend.util.Exception.FeedbackErrorResponse;
import com.market.backend.util.Exception.FeedbackNotCreatedException;
import com.market.backend.util.Exception.FeedbackNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("market/api/product/feedback")
public class FeedbacksRESTController {

    private final FeedbacksService feedbacksService;
    private final ModelMapper modelMapper;

    public FeedbacksRESTController(FeedbacksService feedbacksService,
                                   ModelMapper modelMapper){
        this.feedbacksService=feedbacksService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<FeedbackDTO> showAllFeedbacks(){
        return feedbacksService.showAll().stream().map(this::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{product_id}/all")
    public List<FeedbackDTO> showFeedbacksAtProduct(@PathVariable("product_id") int productId){

        return feedbacksService.showFeedbacksAtProduct(productId).stream().map(this::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public FeedbackDTO showTheOnlyOneFeedback(@PathVariable("id") int id){
        return convertToFeedbackDTO(feedbacksService.findById(id));
    }

    @PostMapping("/{product_id}")
    public ResponseEntity<HttpStatus> createFeedback(@PathVariable("product_id") int productId,
                                                     @RequestBody @Valid FeedbackDTO feedbackDTO,
                                                     BindingResult bindingResult){
        System.out.println("Message length: "+feedbackDTO.getMessage().length());
        if(bindingResult.hasErrors()){
            StringBuilder message=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField()).append("-")
                        .append(error.getDefaultMessage()).append("; ");
            }
            throw new FeedbackNotCreatedException(message.toString());
        }

        feedbacksService.createFeedbackForProduct(productId,convertToFeedback(feedbackDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public HttpStatus updateFeedback(@PathVariable("id") int id,
                                     @RequestBody @Valid FeedbackDTO feedbackDTO,
                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //TODO
        }
        feedbacksService.updateFeedbackById(id,convertToFeedback(feedbackDTO));
        return HttpStatus.OK;
    }

    @ExceptionHandler
    private ResponseEntity<FeedbackErrorResponse> handleException(FeedbackNotFoundException e){
        FeedbackErrorResponse response=new FeedbackErrorResponse(
          "Feedback wasn't find",
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

    private Feedback convertToFeedback(FeedbackDTO feedbackDTO){
        return modelMapper.map(feedbackDTO,Feedback.class);
    }

    private FeedbackDTO convertToFeedbackDTO(Feedback feedback){
        return modelMapper.map(feedback, FeedbackDTO.class);
    }
}
