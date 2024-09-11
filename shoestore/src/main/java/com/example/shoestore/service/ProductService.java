package com.example.shoestore.service;

import org.springframework.stereotype.Service;
import com.example.shoestore.model.Product;
import com.example.shoestore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ProductService {
	
	 @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            productRepository.save(product);
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
