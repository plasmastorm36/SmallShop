package com.shop.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Orders")
/**
 * Order class to order a single product
 * @author Noah Rouse
 * @email: noahrouse36@gmail.com
 */
public class Order {
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   @Column(name="id", nullable = false)
   private long id;

   @Column(name = "items", nullable = false)
   private List<Item> items;

   @Column(name = "price", nullable = false)
   private BigDecimal price;

   @Column(name = "orderDate", nullable = false)
   private LocalDate orderDate;

   public long getId () {
      return this.id;
   }

   public List<Item> getItems () {
      return this.items;
   }

   public BigDecimal getPrice () {
      return this.price;
   }

   public LocalDate getOrderDate() {
      return this.orderDate;
   }

   public void setItems (final List<Item> items) {
      this.items = items;
   }

   public void setPrice (final BigDecimal price) {
      this.price = price;
   }

   public void setOrderDate (final LocalDate orderDate) {
      this.orderDate = orderDate;
   }
}