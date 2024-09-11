package com.example.shoestore.service;

import com.example.shoestore.model.Order;
import com.example.shoestore.repository.OrderRepository;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.util.Map;

public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    public BigDecimal calculateTotalAmount(Map<Long, Integer> cart) {
        BigDecimal total = BigDecimal.ZERO;
        // Logic to calculate the total amount based on the cart contents
        // Example:
        for (Map.Entry<Long, Integer> entry : cart.entrySet()) {
            Long productId = entry.getKey();
            int quantity = entry.getValue();
            // Assume getProductById method fetches the product from ProductService
            BigDecimal price = new BigDecimal("100.00"); // Example price for the product
            total = total.add(price.multiply(new BigDecimal(quantity)));
        }
        return total;
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void updateOrder(Order order) {
        orderRepository.update(order);
    }
}
