package com.market.backend.controllers;

import com.market.backend.dao.ProductsDAO;
import com.market.backend.models.Product;
import com.market.backend.services.ProductsService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/market/products")
public class ProductsController {

    private final ProductsService productsService;

    public ProductsController(ProductsService productsService){
        this.productsService=productsService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("products", productsService.findAll());
        return "product/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productsService.findById(id));
        return "product/show";
    }

    @GetMapping("/new")
    public String create(@ModelAttribute("product")Product product){
        return "product/create";
    }

    @PostMapping("/new")
    public String createPost(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "product/create";
        }
        productsService.create(product);
        return "redirect:/market/products";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("product",productsService.findById(id));
        return "product/edit";
    }

    @PatchMapping("/{id}/edit")
    public String editPatch(@PathVariable("id") int id,
                            @ModelAttribute("product")@Valid Product product,
                            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "product/edit";
        }
        productsService.update(id,product);
        return "redirect:/market/products/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        productsService.delete(id);
        return "redirect:/market/products";
    }
}
