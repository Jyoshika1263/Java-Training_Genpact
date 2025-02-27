package com.onlinebookshop.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;
 
import com.onlinebookshop.shop.model.Order;
import com.onlinebookshop.shop.repository.OrderRepository;
 
@Service
public class OrderService {
    private final OrderRepository orderRepository;
 
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
 
    public void addOrder(Order order) {
        orderRepository.save(order);
    }
 
    public List<Order> getAllOrders() {
        return orderRepository.getAll();
    }
 
    public Order getOrderById(int id) {
        return orderRepository.findById(id);
    }
 
    public void updateOrder(Order order) {
        orderRepository.update(order);
    }
 
    public void deleteById(int id) {
        orderRepository.delete(id);
    }
}