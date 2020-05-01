package com.mindtree.springbootapi.dao;

import com.mindtree.springbootapi.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,String> {
    Optional<User> getUserByUserName(String userName);
}
