package com.market.backend.util.Validation;

import com.market.backend.dto.FeedbackDTO;
import com.market.backend.models.Feedback;
import com.market.backend.services.FeedbacksService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
public class FeedbackDTOUpdateValidation implements Validator {

    private final FeedbacksService feedbacksService;

    public FeedbackDTOUpdateValidation(FeedbacksService feedbacksService){
        this.feedbacksService=feedbacksService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(FeedbackDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors){
        FeedbackDTO feedbackDTO=(FeedbackDTO) target;


    }
}