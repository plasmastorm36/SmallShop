package com.shop.main.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User entity class
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Entity
@Table(name = "Users")
public class User {
   public User () {
      this.roles = new HashSet<Role>();
      this.cart = new Cart();
      this.orders = new ArrayList<Order>();
   }

   public User (final String username, final String password, final String email,
         final String firstName, final String lastName) {
      this.username = username;
      this.password = password;
      this.email = email;
      this.firstName = firstName;
      this.lastName = lastName;
      this.createdDate = LocalDate.now();
      this.lastUpdated = createdDate;
      this.roles = new HashSet<Role>();
      this.cart = new Cart();
      this.orders = new ArrayList<Order>();
   }

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "Id", nullable = false)
   private Long id;

   @Column(name = "username", nullable = false, unique = true)
   private String username;

   @Column(name = "password", nullable = false)
   private String password;

   @Email
   @Column(name = "email", nullable = false)
   private String email;

   @Column(name = "firstName", nullable = false)
   private String firstName;

   @Column(name = "lastName", nullable = false)
   private String lastName;

   @Column(name = "phoneNumber", nullable = true)
   private String phoneNumber;

   @Column(name = "address", nullable = true)
   private String address;

   @Column(name = "createdDate", nullable = false)
   private LocalDate createdDate;

   @Column(name = "lastUpdated", nullable = false)
   private LocalDate lastUpdated;

   @Enumerated(EnumType.STRING)
   @ElementCollection(targetClass = Role.class)
   @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
   @Column(name = "roles", nullable = true)
   private Set<Role> roles;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "cart_id")
   private Cart cart;

   @OneToMany(cascade = CascadeType.ALL)
   @JoinColumn(name = "order_id")
   private List<Order> orders;

   // GETTERS

   public long getId () {
      return this.id;
   }

   public String getUsername () {
      return this.username;
   }

   public String getPassword () {
      return this.password;
   }

   public String getEmail () {
      return this.email;
   }

   public String getFirstName () {
      return this.firstName;
   }

   public String getLastName () {
      return this.lastName;
   }

   public String getPhoneNumber () {
      return this.phoneNumber;
   }

   public LocalDate getCreatedDate () {
      return this.createdDate;
   }

   public LocalDate getLastUpdated () {
      return this.lastUpdated;
   }

   public Set<Role> getRoles () {
      return this.roles;
   }

   public Cart getCart () {
      return this.cart;
   }

   public List<Order> getOrders () {
      return this.orders;
   }

   // SETTERS

   public void setUsername (final String userName) {
      this.username = userName;
   }

   public void setPassword (final String password) {
      this.password = password;
   }

   public void setEmail (final String email) {
      this.email = email;
   }

   public void setFirstName (final String firstName) {
      this.firstName = firstName;
   }

   public void setLastName (final String lastName) {
      this.lastName = lastName;
   }

   public void setPhoneNumber (final String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public void setCreatedDate (final LocalDate createdDate) {
      this.createdDate = createdDate;
   }

   public void setLastUpdated (final LocalDate lastUpdated) {
      this.lastUpdated = lastUpdated;
   }

   public void setRoles (final HashSet<Role> roles) {
      this.roles = roles;
   }

   public void setCart (final Cart cart) {
      this.cart = cart;
   }

   public void setOrders (final ArrayList<Order> orders) {
      this.orders = orders;
   }
 
   // UPDATE

   @PreUpdate
   public void PreUpdate() {
      this.lastUpdated = LocalDate.now();
   }

   // ROLE HANDLING

   public void addRole(final Role role) {
      if (roles == null) {
         this.roles = new HashSet<Role>();
      }  
      
      this.roles.add(role);
   }

   public void addRoles (final Collection<Role> roles) {
      this.roles.addAll(roles);
   }

   public void removeRole (final Role role) {
      this.roles.remove(role);
   }

   public void removeRole (final Collection<Role> roles) {
      this.roles.removeAll(roles);
   }

   public void removeAllRoles () {
      this.roles.removeAll(roles);
   }

   public void addOrder (final Order order) {
      this.orders.add(order);
   }
}
