package com.market.backend.repositories;

import com.market.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository  extends JpaRepository<Product,Integer> {
}
