package com.market.backend.services;

import com.market.backend.models.Product;
import com.market.backend.repositories.ProductsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
