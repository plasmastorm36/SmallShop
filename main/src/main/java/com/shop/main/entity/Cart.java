package com.shop.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.List;
/**
 * Cart entity class to handle multiple purchases
 * @author Noah Ruse
 * @email noahrouse36@gmail.com
 */
@Entity
@Table(name = "Carts")
public class Cart {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", nullable = false)
   private long id;

   @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "cart_id")
   private List<Item> items;

   @Column(name = "totalPrice")
   private BigDecimal totalPrice;

   // GETTERS

   public long getId () {
      return this.id;
   }

   public List<Item> getItems () {
      return this.items;
   }

   public BigDecimal getTotalPrice () {
      return this.totalPrice;
   }

   // SETTERS

   public void setItems (final List<Item> items) {
      this.items = items;
   }

   public void setTotalPrice (final BigDecimal totalPrice) {
      this.totalPrice = totalPrice;
   }

   // MISC

   public void addItem (final Item item) {
      this.items.add(item);
      this.totalPrice.add(item.getPrice());
   }

   public void calculateTotalPrice () {
      final BigDecimal total = new BigDecimal(0);
      for (final Item item: items) {
         total.add(item.getPrice());
      }

      this.totalPrice = total;
   }
}
