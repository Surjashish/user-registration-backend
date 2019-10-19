package com.stackroute.userregistration.repository;

import com.stackroute.userregistration.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRegistrationRepository extends MongoRepository<User, String> {


   public List<User> findByUsername(String username);
}
