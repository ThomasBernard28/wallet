package com.example.Api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT s FROM USERS s WHERE s.userID = ?1")
    Optional<User> findUserByUserID(String userID);



}
