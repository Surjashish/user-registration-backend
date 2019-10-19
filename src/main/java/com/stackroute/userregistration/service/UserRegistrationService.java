package com.stackroute.userregistration.service;

import com.stackroute.userregistration.domain.User;
import com.stackroute.userregistration.exception.UserAlreadyExistsException;

public interface UserRegistrationService {
    public User saveUser(User user)throws UserAlreadyExistsException;


}
