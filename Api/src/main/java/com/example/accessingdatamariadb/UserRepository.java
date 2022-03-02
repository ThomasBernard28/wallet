package com.example.accessingdatamariadb;

import org.springframework.data.repository.CrudRepository;
import com.example.accessingdatamariadb.User;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<User, Integer> {}
