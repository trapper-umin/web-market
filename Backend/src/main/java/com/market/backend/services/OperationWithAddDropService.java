package com.market.backend.services;

import com.market.backend.models.Person;
import com.market.backend.models.Product;
import com.market.backend.util.Exception.Person.PersonOperationNotDoneException;
import com.market.backend.util.Exception.Product.ProductAlreadyExistException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OperationWithAddDropService {

    private final PeopleService peopleService;
    private final ProductsService productsService;

    public OperationWithAddDropService(PeopleService peopleService, ProductsService productsService) {
        this.peopleService = peopleService;
        this.productsService = productsService;
    }

    @Transactional
    public void operationWithProductAndPerson(int id,int productId,String operation){
        Product product=productsService.findById(productId);
        if(product.getOwner()!=null){
            throw new ProductAlreadyExistException("This product already has owner");
        }
        Person person=peopleService.findById(id);

        if(operation.equals("add")){
            product.setOwner(person);
        }else if(operation.equals("drop")){
            List<Product> products=productsService.findByOwner(person);
            for(Product pr : products) {
                System.out.println("Collection product id: "+pr.getId()+"\n"+"Product id for dropping: "+product.getId());
                if (pr.getId() == product.getId()){
                    dropPersonForProduct(pr);
                    break;
                }
            }
        }else throw new PersonOperationNotDoneException(operation+"-there is no such attribute");
    }

    public void saveProductsForPerson(int id,Person person, List<Product> products){
        person.setProducts(products);
        peopleService.update(id,person);
    }

    public void dropPersonForProduct(Product product){
        productsService.updateOwnerOfProduct(product);
    }
}
