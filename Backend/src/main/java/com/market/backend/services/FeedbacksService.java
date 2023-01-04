package com.market.backend.services;

import com.market.backend.models.Feedback;
import com.market.backend.repositories.FeedbacksRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class FeedbacksService {

    private final FeedbacksRepository feedbacksRepository;
    private final ProductsService productsService;

    public FeedbacksService(FeedbacksRepository feedbacksRepository, ProductsService productsService) {
        this.feedbacksRepository = feedbacksRepository;
        this.productsService = productsService;
    }

    public List<Feedback> showAll(){
        return feedbacksRepository.findAll();
    }

    public List<Feedback> showFeedbacksAtProduct(int productId){
        return feedbacksRepository.findByProduct(productsService.findById(productId));
    }

    @Transactional
    public void createFeedbackForProduct(int productId,Feedback feedback){
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setUpdatedAt(LocalDateTime.now());
        feedback.setProduct(productsService.findById(productId));
        feedbacksRepository.save(feedback);
    }
}
