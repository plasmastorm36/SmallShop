package com.shop.main.dto;

public class LoginRequest {
   private String username;
   private String password;

   // GETTERS

   public String getUsername () {
      return this.username;
   }

   public String getPassword () {
      return this.password;
   }

   // SETTERS

   public void setUsername (final String name) {
      this.username = name;
   }

   public void setPassword (final String password) {
      this.password = password;
   }
}
