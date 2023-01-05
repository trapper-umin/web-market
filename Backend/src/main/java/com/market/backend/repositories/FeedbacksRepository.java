package com.market.backend.repositories;

import com.market.backend.models.Feedback;
import com.market.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedback,Integer> {
    List<Feedback> findByProduct(Product product);
    List<Feedback> findByAuthor(String author);
}
