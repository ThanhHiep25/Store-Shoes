package com.example.shoestore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.shoestore.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
