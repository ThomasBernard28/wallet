package com.example.accessingdatamariadb.repositories;

import com.example.accessingdatamariadb.data.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {}
