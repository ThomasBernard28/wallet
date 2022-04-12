package com.example.Api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * The repository is the deepest layer of the API. It's objective is to communicate
 * directly with the Data Base. There is a method that is called from the UserService and
 * which is associated with a SQL Request taking parameters of the method.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT s FROM USERS s WHERE s.userID = ?1")
    Optional<User> findUserByUserID(String userID);



}
