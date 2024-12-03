package com.shop.main.repository;

import com.shop.main.entity.Order;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Order repository to find user orders
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
