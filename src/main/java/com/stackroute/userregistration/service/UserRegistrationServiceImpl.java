package com.stackroute.userregistration.service;

import com.stackroute.userregistration.domain.User;
import com.stackroute.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.userregistration.repository.UserRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties("application.properties")
public class UserRegistrationServiceImpl implements UserRegistrationService{

    private UserRegistrationRepository userRegistrationRepository;

    @Autowired
    public UserRegistrationServiceImpl(UserRegistrationRepository userRegistrationRepository){this.userRegistrationRepository=userRegistrationRepository;}



    @Override
    public User saveUser(User user)throws UserAlreadyExistsException
    {
        if(userRegistrationRepository.findByUsername(user.getUsername()).size()!=0)
        {
            throw new UserAlreadyExistsException("You have already registered yourself!");
        }
        User savedUser = userRegistrationRepository.save(user);
        return savedUser;
    }

}
