package com.shop.main.dto;

public class RegisterRequest {
   private String username;
   private String password;
   private String email;
   private String firstName;
   private String lastName;

   // GETTERS

   public String getUsername() {
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

   // SETTERS

   public void setUsername (final String username) {
      this.username = username;
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
}
