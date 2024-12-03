package com.shop.main.service;

import com.shop.main.entity.User;
import com.shop.main.repository.UserRepository;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service to handle login logic
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Service
public class LoginService {
   private final UserRepository repos;
   private final PasswordEncoder encoder;
   public LoginService (final UserRepository repository, final PasswordEncoder encoder) {
      this.repos = repository;
      this.encoder = encoder;
   }

   public boolean authenticate (final String username, final String password) throws Exception {
      final Optional<User> user = repos.findByUsername(username);
      if (user.isPresent()) { 
         return encoder.matches(password, user.get().getPassword());
      }
      System.out.println("Repos is failing at its job");
      return false;
   }
}
