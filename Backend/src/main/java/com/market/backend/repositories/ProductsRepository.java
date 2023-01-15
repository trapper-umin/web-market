package com.market.backend.repositories;

import com.market.backend.models.Person;
import com.market.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository  extends JpaRepository<Product,Integer> {
    Optional<Product> findByName(String name);
    List<Product> findByOwner(Person person);
}
