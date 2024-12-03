package com.shop.main.controller;

import com.shop.main.entity.Product;
import com.shop.main.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
   private final ProductService service;
   public ProductController (final ProductService productService) {
      this.service = productService;
   }

   @GetMapping("/all")
   public List<Product> getAllProducts() {
      return service.findAllProducts();
   }
   
   @GetMapping("/id-search/{id}")
   public ResponseEntity<?> getProductById (final @PathVariable long id) {
      try {
         return ResponseEntity.ok(service.findProductById(id));
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }

   @GetMapping("/find-by-name/{name}")
   public List<Product> getProductsByName (final @PathVariable String name) {
      return service.findProductsByName(name);
   }

   // check to see if this will work with the id generator
   @PostMapping("/add")
   public ResponseEntity<String> addProduct (final @RequestBody Product product) {
      service.addProduct(product);
      return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity<String> deleteProduct (final @PathVariable long id) {
      try {
         final Product product = service.findProductById(id);
         service.deleteProduct(product);
         return ResponseEntity.ok("Product deleted successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }

   @PutMapping("/update-price/{id}")
   public ResponseEntity<String> updatePrice (final @PathVariable long id, 
         final @RequestBody BigDecimal price) {
      try {
         final Product product = service.findProductById(id);
         service.updateProductPrice(product, price);
         return ResponseEntity.ok("Price updated successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }

   @PutMapping("/update-quantity/{id}")
   public ResponseEntity<String> updateQuantity (final @PathVariable long id, final @RequestBody int quantity) {
      try {
         final Product product = service.findProductById(id);
         service.updateProductQuantity(product, quantity);
         return ResponseEntity.ok("Quantity updated successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }
}
