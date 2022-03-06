package com.example.Api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    //Be aware that the table "User" in the query is actually the User class in the package user
    //@Query("SELECT s FROM User s WHERE s.userID = ?1")
    //This is the same query
    Optional<User> findUserByUserID(String userID);
}
