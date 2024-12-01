package com.shop.main.service;

import com.shop.main.entity.Item;
import com.shop.main.entity.Order;
import com.shop.main.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Order repository handles order operations
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Service
public class OrderService {
   private final OrderRepository repos;

   public OrderService (final OrderRepository orderRepository) {
      this.repos = orderRepository;
   }

   /**
    * creates and returns a new order
    * @param items
    * @return
    */
   public Order createOrder (final List<Item> items) {
      final Order order = new Order(items);
      repos.save(order);
      return order;
   }

   public void saveOrder (final Order order) {
      repos.save(order);
   }
}
