package com.shop.main.service;

import com.shop.main.entity.Product;
import com.shop.main.repository.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

/**
 * Product service to handle logic details
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Service
public class ProductService {
   private final ProductRepository repos;
   public ProductService (final ProductRepository productRepository) {
      this.repos = productRepository;
   }

   public void addProduct (final Product product) {
      product.setCreatedDate(LocalDate.now());
      product.setLastUpdated(LocalDate.now());
      repos.save(product);
   }

   public void deleteProduct (final Product product) {
      repos.delete(product);
   }

   public List<Product> findAllProducts () {
      return repos.findAll();
   }

   public Product findProductById (final long id) {
      final Optional<Product> product = repos.findById(id);
      if (product.isPresent()) {
         return product.get();
      }

      throw new EntityNotFoundException(String.format("Product with id %d not found", id));
   }

   public List<Product> findProductsByName (final String name) {
      return repos.findByName(name);
   }

   public void updateProductPrice (final Product product, final BigDecimal price) {
      product.setPrice(price);
      product.setLastUpdated(LocalDate.now());
      repos.save(product);
   }

   public void updateProductQuantity (final Product product, final int quantity) {
      product.setQuantity(quantity);
      product.setLastUpdated(LocalDate.now());
      repos.save(product);
   }
}
