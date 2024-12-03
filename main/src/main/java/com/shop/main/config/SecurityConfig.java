package com.shop.main.config;

import com.shop.main.service.CustomerDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * config class to handle spring boot security details
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
   private final CustomerDetailsService service;
   public SecurityConfig (final CustomerDetailsService customerDetailsService) {
      this.service = customerDetailsService;
   }

   /**
    * adds permissions for different domains
    * @param http
    * @return a security filter which selects with paths need authorization
    * @throws Exception
    */
   @Bean
   public SecurityFilterChain securityFilterChain (final HttpSecurity http) throws Exception {
      http.csrf(crsf -> crsf.disable())
            .authorizeHttpRequests(authorize -> authorize.requestMatchers("/**")
            .permitAll().requestMatchers("/users/**").permitAll()
            .requestMatchers("/products/all", "/products/id-search/**",
            "/products/find-by-name/**").permitAll()
            .requestMatchers("/register").permitAll()
            .requestMatchers("/login").permitAll())
            .httpBasic(Customizer.withDefaults())
            .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"));

      return http.build();
   }

   /**
    * Selects the encoder wanted for the password encoder
    * @return password encoder type
    */
   @Bean
   public PasswordEncoder passwordEncoder () {
      return new BCryptPasswordEncoder();
   }

   /**
    * sets up the Authentication manager with the config details
    * @param http
    * @return the set up Authentication manager
    * @throws Exception
    */
   @Bean
   public AuthenticationManager authenticationManager (final HttpSecurity http)
         throws Exception {
      http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(service)
            .passwordEncoder(passwordEncoder());

      final AuthenticationManagerBuilder builder = http
            .getSharedObject(AuthenticationManagerBuilder.class);

      builder.userDetailsService(service).passwordEncoder(passwordEncoder());
      return builder.build();
   }
}
