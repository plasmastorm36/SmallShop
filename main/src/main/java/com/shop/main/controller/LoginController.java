package com.shop.main.controller;

import com.shop.main.dto.RegisterRequest;
import com.shop.main.service.LoginService;
import com.shop.main.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Controller to handle login and default page
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@RestController
public class LoginController {
   private final LoginService loginService;
   private final UserDetailsService userDetailsService;
   private final UserService userService;
   public LoginController (final LoginService loginService,
         final UserDetailsService userDetailsService, final UserService userService) {
      this.loginService = loginService;
      this.userDetailsService = userDetailsService;
      this.userService = userService;
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

   @PostMapping("/register")
   public ResponseEntity<String> register (@RequestBody RegisterRequest request) {
      userService.createUser(request.getUsername(), request.getPassword(), request.getEmail(),
            request.getFirstName(), request.getLastName());
      return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
   }
}
