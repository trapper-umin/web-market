package com.market.backend.controllers;

import com.market.backend.services.ProductsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/market")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService=productsService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("products",productsService.findAll());

        return "market/index";
    }

}
