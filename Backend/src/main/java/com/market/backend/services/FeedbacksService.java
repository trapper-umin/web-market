package com.market.backend.services;

import com.market.backend.models.Feedback;
import com.market.backend.repositories.FeedbacksRepository;
import com.market.backend.util.Exception.Feedback.FeedbackNotDeletedException;
import com.market.backend.util.Exception.Feedback.FeedbackNotFoundException;
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

    public List<Feedback> findByAuthorName(String author){
        return feedbacksRepository.findByAuthor(author);
    }

    public Feedback findById(int id){
        return feedbacksRepository.findById(id).orElseThrow(FeedbackNotFoundException::new);
    }

    @Transactional
    public void createFeedbackForProduct(int productId,Feedback feedback){
        productsService.findById(productId);
        feedback.setCreatedAt(LocalDateTime.now());
        feedback.setUpdatedAt(LocalDateTime.now());
        feedback.setProduct(productsService.findById(productId));
        feedbacksRepository.save(feedback);
    }

    @Transactional
    public void updateFeedbackById(int id,Feedback feedback){
        if(feedbacksRepository.findById(id).isEmpty())
            throw new FeedbackNotFoundException();
        feedback.setId(id);
        feedback.setCreatedAt(feedbacksRepository.findById(id).get().getCreatedAt());
        feedback.setUpdatedAt(LocalDateTime.now());
        feedback.setProduct(feedbacksRepository.findById(id).get().getProduct());
        feedbacksRepository.save(feedback);
    }

    @Transactional
    public boolean deleteById(int id){
        if(feedbacksRepository.findById(id).isPresent()){
            feedbacksRepository.deleteById(id);
            return true;
        }else return false;
    }
}
