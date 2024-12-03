package com.shop.main.controller;

import com.shop.main.service.LoginService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Controller to handle login and default page
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@RestController
@RequestMapping("/api")
public class LoginController {
   private final LoginService loginService;
   private final UserDetailsService userDetailsService;
   public LoginController (final LoginService loginService, UserDetailsService userDetailsService) {
      this.loginService = loginService;
      this.userDetailsService = userDetailsService;
   }

   /**
    * allows a user to login
    * @param username
    * @param password
    * @return
    */
   @PostMapping("/login")
   public ResponseEntity<String> login (final @RequestParam String username, final @RequestParam String password) {
      if (loginService.authenticate(username, password)) {
         UserDetails userDetails = userDetailsService.loadUserByUsername(username);
         
         // Authentication object with proper roles
         UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
               userDetails, null, userDetails.getAuthorities());

         SecurityContextHolder.getContext().setAuthentication(token);
         return ResponseEntity.ok("Login Successful");
      }

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
   }
}
