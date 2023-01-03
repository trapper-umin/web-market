package com.market.backend.util.Validation;

import com.market.backend.dto.ProductDTO;
import com.market.backend.services.ProductsService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductDTONameValidation implements Validator {

    private final ProductsService productsService;

    public ProductDTONameValidation(ProductsService productsService){
        this.productsService=productsService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ProductDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO=(ProductDTO) target;
        if(productsService.findByProductName(productDTO.getName()).isPresent()){
            errors.rejectValue("name","","Product with this ID already exist");
        }
    }
}
