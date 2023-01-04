package com.market.backend.util.Validation;

import com.market.backend.dto.ProductDTO;
import com.market.backend.models.Product;
import com.market.backend.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProductDTOUpdateValidation implements Validator {

    private final ProductsService productsService;

    @Autowired
    public ProductDTOUpdateValidation(ProductsService productsService) {
        this.productsService = productsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ProductDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductDTO productDTO=(ProductDTO) target;
        Optional<Product> product=productsService.findByProductName(productDTO.getName());
        if(product.isPresent() && product.get().getQuantity()==(productDTO.getQuantity())
                && product.get().getDescription().equals(productDTO.getDescription())) {
            errors.rejectValue("name", "", "Not changed");
            errors.rejectValue("quantity","","Not changed");
            errors.rejectValue("description","","Not change");
        }
    }
}
