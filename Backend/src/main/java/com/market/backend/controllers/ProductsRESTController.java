package com.market.backend.controllers;

import com.market.backend.dto.ProductDTO;
import com.market.backend.dto.ProductDTOResponse;
import com.market.backend.models.Product;
import com.market.backend.services.ProductsService;
import com.market.backend.util.Exception.*;
import com.market.backend.util.Validation.ProductDTONameValidation;
import com.market.backend.util.Validation.ProductDTOUpdateValidation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("market/api/product")
public class ProductsRESTController {

    private final ProductsService productsService;
    private final ProductDTOUpdateValidation productDTOUpdateValidation;
    private final ProductDTONameValidation productDTONameValidation;
    private final ModelMapper modelMapper;

    public ProductsRESTController(ProductsService productsService, ProductDTOUpdateValidation productUpdateValidation,
                                  ModelMapper modelMapper, ProductDTONameValidation productDTONameValidation){
        this.productsService=productsService;
        this.productDTOUpdateValidation =productUpdateValidation;
        this.modelMapper=modelMapper;
        this.productDTONameValidation=productDTONameValidation;
    }

    @GetMapping()
    public List<ProductDTOResponse> index(){
        List<Product> products=productsService.findAll();
        if(products.size()==0){
            throw new ThereAreNoProductsException();
        }
        List<ProductDTOResponse> productsDTOResponse=new ArrayList<>();
        for(Product product : products)
            productsDTOResponse.add(convertToProductDTOResponse(product));
        return productsDTOResponse; 
    }

    @GetMapping("/{id}")
    public ProductDTOResponse index(@PathVariable("id") int id){
        return convertToProductDTOResponse(productsService.findById(id));
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult){
        productDTONameValidation.validate(productDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error :errors){
                message.append(error.getField())
                        .append("-").append(error.getDefaultMessage()).append("; ");
            }
            throw new ProductNotCreatedException(message.toString());
        }
        productsService.create(converToProduct(productDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/{id}/edit")
    public ResponseEntity<HttpStatus> update(@PathVariable("id") int id,@RequestBody @Valid ProductDTO productDTO,
                                             BindingResult bindingResult){
        productDTOUpdateValidation.validate(productDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField())
                        .append("-").append(error.getDefaultMessage()).append("; ");
            }
            throw new ProductNotUpdatedException(message.toString());
        }
        productsService.update(id,converToProduct(productDTO));
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

    private Product converToProduct(ProductDTO productDTO){
        return modelMapper.map(productDTO,Product.class);
    }

    private ProductDTO convertToProductDTO(Product product){
        return modelMapper.map(product,ProductDTO.class);
    }

    private ProductDTOResponse convertToProductDTOResponse(Product product){return modelMapper.map(product, ProductDTOResponse.class);}
}