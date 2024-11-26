package com.shop.main.repository;

import com.shop.main.entity.Order;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Order repository to find user orders
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
   
   @Query("SELECT o FROM Order o WHERE o.user.id = :user_id")
   public List<Order> findUserOrders(final long user_id);
}
