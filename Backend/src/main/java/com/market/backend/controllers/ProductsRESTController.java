package com.market.backend.controllers;

import com.market.backend.models.Product;
import com.market.backend.services.ProductsService;
import com.market.backend.util.Exception.*;
import com.market.backend.util.Validation.ProductNameValidation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("market/api/products")
public class ProductsRESTController {

    private final ProductsService productsService;
    private final ProductNameValidation productNameValidation;

    public ProductsRESTController(ProductsService productsService, ProductNameValidation productNameValidation){
        this.productsService=productsService;
        this.productNameValidation = productNameValidation;
    }

    @GetMapping()
    public List<Product> index(){
        List<Product> products=productsService.findAll();
        if(products.size()==0){
            throw new ThereAreNoProductsException();
        }
        return products;
    }

    @GetMapping("/{id}")
    public Product index(@PathVariable("id") int id){
        return productsService.findById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid Product product,
                                             BindingResult bindingResult){
        productNameValidation.validate(product,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message =new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error :errors){
                message.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage()).append("; ");
            }
            throw new ProductNotCreatedException(message.toString());
        }
        productsService.create(product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,@RequestBody @Valid Product product,
                                             BindingResult bindingResult){
        productNameValidation.validate(product,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField())
                        .append("-").append(error.getDefaultMessage()).append(";");
            }
            throw new ProductNotUpdatedException(message.toString());
        }
        productsService.update(id,product);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable("id") int id){
        productsService.delete(id);
        return HttpStatus.OK;
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException e){
        ProductErrorResponse response=new ProductErrorResponse(
                " Product with this id wasn't found ",
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ThereAreNoProductsException e){
        ProductErrorResponse response =new ProductErrorResponse(
                "There are no products",
                HttpStatus.NOT_FOUND,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductNotCreatedException e){
        ProductErrorResponse response =new ProductErrorResponse(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ProductErrorResponse> handleException(ProductNotUpdatedException e){
        ProductErrorResponse response =new ProductErrorResponse(
          e.getMessage(),
          HttpStatus.BAD_REQUEST,
          LocalDateTime.now()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}