package com.shop.main.repository;

import com.shop.main.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository for products
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   @Query("SELECT p FROM Product p WHERE p.name = :name")
   List<Product> findByName(final String name);
}
