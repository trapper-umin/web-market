package com.market.backend.services;

import com.market.backend.models.Feedback;
import com.market.backend.models.Product;
import com.market.backend.repositories.ProductsRepository;
import com.market.backend.util.Exception.Product.ProductNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductsService {

    private final ProductsRepository productsRepository;


    public ProductsService(ProductsRepository productsRepository){
        this.productsRepository=productsRepository;

    }


    public List<Product> findAll(){
        List<Product>products= productsRepository.findAll();
        for(Product product : products){
            product.setRating(averageRating(product.getFeedbacks()));
        }
        return products;
    }

    public Product findById(int id){
        Optional<Product>product= productsRepository.findById(id);
        if(product.isPresent()){
            product.get().setRating(averageRating(product.get().getFeedbacks()));
            return product.get();
        }else throw new ProductNotFoundException();
    }

    public Optional<Product> findByProductName(String name){
        return productsRepository.findByName(name);
    }

    @Transactional
    public void create(Product product){
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        productsRepository.save(product);
    }

    @Transactional
    public void update(int id,Product product){
        product.setId(id);
        product.setUpdatedAt(LocalDateTime.now());
        product.setCreatedAt(productsRepository.findById(id).get().getCreatedAt());
        productsRepository.save(product);
    }

    @Transactional
    public void delete(int id){
        productsRepository.deleteById(id);
    }

    private double averageRating(List<Feedback> feedbacks){
        int sum=0;
        int i=0;
        if(feedbacks.size()==0){
            return 0;
        }
        for(Feedback feedback : feedbacks){
            sum+=feedback.getMark();
            i++;
        }

        return sum/i;
    }

}
