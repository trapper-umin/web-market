package com.market.backend.services;

import com.market.backend.models.Product;
import com.market.backend.repositories.ProductsRepository;
import com.market.backend.util.Exception.ProductNotFoundException;
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
        return productsRepository.findAll();
    }

    public Product findById(int id){
        return productsRepository.findById(id).orElseThrow(ProductNotFoundException::new);
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

}
