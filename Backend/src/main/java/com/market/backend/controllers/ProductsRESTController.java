package com.market.backend.controllers;

import com.market.backend.models.Product;
import com.market.backend.services.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("market/api/products")
public class ProductsRESTController {

    private final ProductsService productsService;

    public ProductsRESTController(ProductsService productsService){
        this.productsService=productsService;
    }

    @GetMapping()
    public List<Product> index(){
        return productsService.findAll();
    }

    @GetMapping("/{id}")
    public Product index(@PathVariable("id") int id){
        return productsService.findById(id);
    }
}
