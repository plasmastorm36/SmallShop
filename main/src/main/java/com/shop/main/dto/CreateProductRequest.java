package com.shop.main.dto;

import java.math.BigDecimal;

public class CreateProductRequest {
   private String name;
   private BigDecimal price;
   private int quantity;

   // GETTERS

   public String getName () {
      return this.name;
   }

   public BigDecimal getPrice () {
      return this.price;
   }
   
   public int getQuantity () {
      return this.quantity;
   }

   // SETTERS

   public void setName (final String name) {
      this.name = name;
   }

   public void setPrice (final BigDecimal price) {
      this.price = price;
   }

   public void setQuantity (final int quantity) {
      this.quantity = quantity;
   }
}
