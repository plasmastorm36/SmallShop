package com.shop.repository;

import com.shop.main.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository to handle queries about users
 * @author Noah Rouse
 * @email noahrouse36@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   @Query("SELECT u FROM Users WHERE u.userName = :userName")
   public Optional<User> findByUserName (final String userName);

   @Query("SELECT u from Users WHERE u.email = :email")
   public List<User> findByEmail (final String email);
}
