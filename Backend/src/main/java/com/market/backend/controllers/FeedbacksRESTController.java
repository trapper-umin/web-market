package com.market.backend.controllers;

import com.market.backend.dto.FeedbackDTO;
import com.market.backend.models.Feedback;
import com.market.backend.services.FeedbacksService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping("/{id}")
    public List<FeedbackDTO> showFeedbacksAtProduct(@PathVariable("id") int productId){

        return feedbacksService.showFeedbacksAtProduct(productId).stream().map(this::convertToFeedbackDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> createFeedback(@PathVariable("id") int productId,
                                                     @RequestBody @Valid FeedbackDTO feedbackDTO,
                                                     BindingResult bindingResult){
        feedbacksService.createFeedbackForProduct(productId,convertToFeedback(feedbackDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Feedback convertToFeedback(FeedbackDTO feedbackDTO){
        return modelMapper.map(feedbackDTO,Feedback.class);
    }

    private FeedbackDTO convertToFeedbackDTO(Feedback feedback){
        return modelMapper.map(feedback, FeedbackDTO.class);
    }
}
