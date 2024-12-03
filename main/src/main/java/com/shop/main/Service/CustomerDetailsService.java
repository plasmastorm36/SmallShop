package com.shop.main.service;

import com.shop.main.repository.UserRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * A detail service made to connect user entity to security user class for login
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Service
public class CustomerDetailsService implements UserDetailsService {
   private final UserRepository repos;
   public CustomerDetailsService(final UserRepository userRepository) {
      this.repos = userRepository;
   }

   public UserDetails loadUserByUsername (final String username) 
         throws UsernameNotFoundException {
      final Optional<com.shop.main.entity.User> user = repos.findByUsername(username);

      if (!user.isPresent()) {
         throw new UsernameNotFoundException(String.format("User with username %s not found",
               username));
      }

      return User.builder().username(user.get().getUsername())
            .password(user.get().getPassword()).roles("USER").build();
   }
}
