package com.shop.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

/**
 * Item entity class to hold products for order
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Entity
@Table(name = "Items")
public class Item {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private long id;

   @ManyToOne(optional = false)
   @JoinColumn(name = "product_id", nullable = false)
   private Product product;

   @Column(name = "amount", nullable = false)
   private int amount;

   @Column(name = "price", nullable = false)
   private BigDecimal price;

   // GETTERS

   public long getId () {
      return this.id;
   }

   public Product getProduct () {
      return this.product;
   }

   public int getAmount () {
      return this.amount;
   }

   public BigDecimal getPrice () {
      return this.price;
   }

   // SETTERS

   public void setProduct (final Product product) {
      this.product = product;
   }

   public void setAmount (final int amount) {
      this.amount = amount;
   }

   public void setPrice (final BigDecimal price) {
      this.price = price;
   }

   // MISC

   public void calculatePrice () {
      this.price = product.getPrice().multiply(new BigDecimal(amount));
   }

   public boolean equals (final Item item) {
      return this.id == item.getId();
   }
}
