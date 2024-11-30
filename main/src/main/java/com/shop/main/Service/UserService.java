package com.shop.main.Service;

import com.shop.main.entity.Item;
import com.shop.main.entity.Role;
import com.shop.main.entity.User;
import com.shop.main.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

/**
 * Service layer designed to create, find and update users and preform user transactions
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Service
public class UserService {
   private final UserRepository repos;

   /**
    * Constructor should be auto injected by Spring when run with maven
    * @param repository
    */
   public UserService (final UserRepository repository) {
      this.repos = repository;
   }

   /**
    * Adds an item to a user's cart
    * @param item
    * @param user
    */
   public void addToUserCart (final Item item, final User user) {
      user.getCart().addItem(item);
      repos.save(user);
   }

   /**
    * Adds a role to user
    * @param role
    * @param user
    */
   public void addUserRole (final Role role, final User user) {
      user.addRole(role);
      repos.save(user);
   }

   public void clearUserCart (final User user) {
      user.getCart().clear();
      repos.save(user);
   }

   /**
    * 
    * @param user
    */
   public void clearUserRoles (final User user) {
      user.removeAllRoles();
      repos.save(user);
   }

   /**
    * Creates and stores a new User
    * @param userName
    * @param password
    * @param email
    * @param firstName
    * @param lastName
    */
   public void createUser (final String userName, final String password,
         @Email final String email, final String firstName, final String lastName) {
      final User user = new User(userName, password, email, firstName, lastName);
      repos.save(user);
   }

   /**
    * Deletes a user if and only if user id is valid
    * @param id
    */
    public void deleteById (final long id) {
      repos.deleteById(id);
   }

   /**
    *
    * @return list of all users
    */
   public List<User> findAllUsers () {
      return repos.findAll();
   }

   /**
    * finds and returns user by user id
    * @param id
    * @return
    * @throws EntityNotFoundException if user is not found
    */
   public User findById (final Long id) throws EntityNotFoundException {
      final Optional<User> user = repos.findById(id);
      if (user.isPresent()) {
         return user.get();
      }

      throw new EntityNotFoundException(String.format("User with id %d not found", id));
   }

   /**
    * finds and returns user by username
    * @param userName
    * @return
    * @throws EntityNotFoundException if user is not found
    */
   public User findByUsername (final String userName) throws EntityNotFoundException {
      final Optional<User> user = repos.findByUserName(userName);

      if (user.isPresent()) {
         return user.get();
      }

      throw new EntityNotFoundException(String.format("User with username %s not found",
            userName));
   }

   /**
    * 
    * @param user
    * @param item
    */
   public void removeItemFromUserCart (final User user, final Item item) {
      user.getCart().removeItem(item);
      repos.save(user);
   }

   /**
    * removes a role from a user
    * @param role
    * @param user
    */
   public void removeUserRole (final Role role, final User user) {
      user.removeRole(role);
      repos.save(user);
   }

   /**
    * 
    * @param user
    * @param email
    */
   public void updateUserEmail (final User user, @Email final String email) {
      user.setEmail(email);
      repos.save(user);
   }

   /**
    * 
    * @param user
    * @param phoneNumber
    */
   public void updateUserPhoneNumber (final User user, final String phoneNumber) {
      user.setPhoneNumber(phoneNumber);
      repos.save(user);
   }

   /**
    * 
    * @param user
    * @param username
    */
   public void updateUserUsername (final User user, final String username) {
      user.setUserName(username);
      repos.save(user);
   }
}
