package com.shop.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


/**
 * Order class to order a single product
 * @author Noah Rouse
 * @email: noahrouse36@gmail.com
 */
@Entity
@Table(name = "Orders")
public class Order {

   public Order () {}

   public Order (final List<Item> items) {
      BigDecimal price = BigDecimal.ZERO;
      for (final Item i: items) {
         price = price.add(i.getPrice());
      }
      this.price = price;
      this.orderDate = LocalDate.now();
   }
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="id", nullable = false)
   private long id;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "order_id")
   private List<Item> items;

   @Column(name = "price", nullable = false)
   private BigDecimal price;

   @Column(name = "orderDate", nullable = false)
   private LocalDate orderDate;

   // GETTERS

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

   // SETTERS

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
