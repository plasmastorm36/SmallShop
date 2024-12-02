package com.shop.main.service;

import com.shop.main.entity.Item;
import com.shop.main.entity.Order;
import com.shop.main.entity.Role;
import com.shop.main.entity.User;
import com.shop.main.exception.InsufficientStockException;
import com.shop.main.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
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
    * clears cart if purchase can be made and makes receipt
    * @param user
    */
   public void purchaseCart (final User user) {
      final Order order = new Order(user.getCart().getItems());
      user.addOrder(order);
      for (final Item i: order.getItems()) {
         if (i.getAmount() > i.getProduct().getQuantity()) {
            throw new InsufficientStockException(String.format("Not enough stock in %s",
                  i.getProduct().getName()));
         }
         i.getProduct().subtractQuantity(i.getAmount());
      }
      user.getCart().clear();
   }

   /**
    * purchases a single item and create a receipt for it
    * @param item
    * @param user
    */
   public void purchaseItem (final Item item, final User user) {
      final ArrayList<Item> items = new ArrayList<>(1);
      items.add(item);
      final Order order = new Order(items);
      user.addOrder(order);
      repos.save(user);
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
