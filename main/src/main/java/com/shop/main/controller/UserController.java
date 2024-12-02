package com.shop.main.controller;

import com.shop.main.dto.PurchaseRequest;
import com.shop.main.entity.Item;
import com.shop.main.entity.User;
import com.shop.main.exception.InsufficientStockException;
import com.shop.main.service.UserService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for user entity
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@RestController
@RequestMapping("/users")
public class UserController {
   private final UserService service;
   public UserController (final UserService userService) {
      this.service = userService;
   }

   @PostMapping("/{userid}/add-to-cart/")
   public ResponseEntity<String> addToCart (final @PathVariable long userId,
         final @RequestBody Item item) {
      try {
         final User user = service.findById(userId);
         service.addToUserCart(item, user);
         return ResponseEntity.ok("Item successfully added to cart");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }

   @PostMapping("/{userid}/purchase-cart")
   public ResponseEntity<String> purchaseCart (final @PathVariable long userId,
         final @RequestBody boolean isVerified ) {
      
      if (!isVerified) {
         return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Purchase not verified");
      }
      try {
         final User user = service.findById(userId);
         service.purchaseCart(user);
         return ResponseEntity.ok("Purchase completed successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      } catch (final InsufficientStockException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
      }
   }

   @PostMapping("/{userid}/purchase-item")
   public ResponseEntity<String> purchaseItem (final @PathVariable long userId,
         final @RequestBody PurchaseRequest request) {
      if (!request.isVerified()) {
         return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Purchase not verified");
      }
      try {
         final User user = service.findById(userId);
         service.purchaseItem(request.getItem(), user);
         return ResponseEntity.ok("Purchase completed successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }

   @PostMapping("/{userid}/removeFromCart")
   public ResponseEntity<String> removeFromCart (final @PathVariable long userId,
         final @RequestBody Item item) {
      try {
         final User user = service.findById(userId);
         service.removeItemFromUserCart(user, item);
         // NOTE: if the item is not in the cart nothing will be done
         return ResponseEntity.ok("Item removed successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }

   @PutMapping("/{userid}/update-email")
   public ResponseEntity<String> updateEmail(final @PathVariable long userId,
         final @RequestBody String email) {
      try {
         final User user = service.findById(userId);
         service.updateUserEmail(user, email);
         return ResponseEntity.ok("Email updated successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }

   @PutMapping("/{userid}/update-phone-number")
   public ResponseEntity<String> updatePhoneNumber(final @PathVariable long userId,
         final @RequestBody String phoneNumber) {
      try {
         final User user = service.findById(userId);
         service.updateUserPhoneNumber(user, phoneNumber);
         return ResponseEntity.ok("Phone number updated successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }

   @PutMapping("/{userid}/update-username")
   public ResponseEntity<String> updateUsername (final @PathVariable long userId,
         final @RequestBody String username) {
      try {
         final User user = service.findById(userId);
         service.updateUserUsername(user, username);
         return ResponseEntity.ok("Username updated successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }
}
