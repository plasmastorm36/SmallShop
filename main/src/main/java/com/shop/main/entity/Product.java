package com.shop.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Product class to handle product entity
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Entity
@Table(name = "Products")
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private long id;

   @Column(name = "name", nullable = false)
   private String name;

   @Column(name = "description", nullable = true)
   private String description;

   // big decimal is used to ensure no errors in price due to floating point
   @Column(name = "price", nullable = false)
   private BigDecimal price;

   @Column(name = "quantity", nullable = false)
   private int quantity;

   @Column(name = "createdDate", nullable = false)
   private LocalDate createdDate;

   @Column(name = "lastUpdated", nullable = false)
   private LocalDate lastUpdated;

   // GETTERS

   public long getId () {
      return this.id;
   }

   public String getName () {
      return this.name;
   }

   public String getDescription () {
      return this.description;
   }

   public BigDecimal getPrice () {
      return this.price;
   }

   public int getQuantity () {
      return this.quantity;
   }

   public LocalDate getCreatedDate () {
      return this.createdDate;
   }

   public LocalDate getLastUpdated () {
      return this.lastUpdated;
   }

   // SETTERS

   public void setName (final String name) {
      this.name = name;
   }

   public void setDescription  (final String description) {
      this.description = description;
   }

   public void setPrice (final BigDecimal price) {
      this.price = price;
   }

   public void setQuantity (final int quantity) {
      this.quantity = quantity;
   }

   public void setCreatedDate (final LocalDate createdDate) {
      this.createdDate = createdDate;
   }

   public void setLastUpdated (final LocalDate lastUpdated) {
      this.lastUpdated = lastUpdated;
   }

   // MISC

   public void subtractQuantity(final int amount) {
      this.quantity -= amount;
   }

   @PreUpdate
   public void updateLastUpdated () {
      this.lastUpdated = LocalDate.now();
   }
}
