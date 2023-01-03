package com.market.backend.util.Validation;

import com.market.backend.models.Product;
import com.market.backend.services.ProductsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductNameValidation implements Validator {

    private final ProductsService productsService;

    public ProductNameValidation(ProductsService productsService){
        this.productsService=productsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Product.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product=(Product) target;
        if(productsService.findByProductName(product.getName()).isPresent()){
            errors.rejectValue("name","","This product already exists");
        }
    }
}
