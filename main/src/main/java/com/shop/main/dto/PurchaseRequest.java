package com.shop.main.dto;

import com.shop.main.entity.Item;

/**
 * DTO for verifying purchase requests
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
public class PurchaseRequest {
   private Item item;
   private boolean isVerified;

   // GETTERS

   public Item getItem () {
      return this.item;
   }

   public boolean isVerified () {
      return isVerified;
   }

   // SETTERS

   public void setItem (final Item item) {
      this.item = item;
   }

   public void setVerified (final boolean isVerified) {
      this.isVerified = isVerified;
   }
}
