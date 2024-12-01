package com.shop.main.controller;

import com.shop.main.entity.User;
import com.shop.main.service.UserService;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
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

   @PostMapping("/{userid}/purchase-cart")
   public ResponseEntity<String> purchaseCart (final @PathVariable long userId,
         final @RequestBody boolean isVerified ) {
      
      if (isVerified == false) {
         return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Purchase not verified");
      }
      try {
         final User user = service.findById(userId);
         service.purchaseCart(user);
         return ResponseEntity.ok("Purchase completed successfully");
      } catch (final EntityNotFoundException e) {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
   }
}
