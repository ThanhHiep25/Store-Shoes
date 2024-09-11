package com.example.shoestore.repository;

import com.example.shoestore.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Order> findAll() {
        return entityManager.createQuery("SELECT o FROM Order o", Order.class).getResultList();
    }

    public Order findById(Long id) {
        return entityManager.find(Order.class, id);
    }

    @Transactional
    public void save(Order order) {
        entityManager.persist(order);
    }

    @Transactional
    public void update(Order order) {
        entityManager.merge(order);
    }
}
