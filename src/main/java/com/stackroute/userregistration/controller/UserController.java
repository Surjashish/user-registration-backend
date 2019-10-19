package com.stackroute.userregistration.controller;

import com.stackroute.userregistration.domain.User;
import com.stackroute.userregistration.domain.UserDTO;
import com.stackroute.userregistration.exception.UserAlreadyExistsException;
import com.stackroute.userregistration.service.Receiver;
import com.stackroute.userregistration.service.UserRegistrationService;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Date;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = "api/v1")
public class UserController {

    UserRegistrationService userRegistrationService;
    private final RabbitTemplate rabbitTemplate;
    private final Receiver receiver;

    private PasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();

    @Autowired
    private Queue queue;

    @Autowired
    public UserController(UserRegistrationService userRegistrationService, RabbitTemplate rabbitTemplate, Receiver receiver){this.userRegistrationService=userRegistrationService;
        this.rabbitTemplate = rabbitTemplate;
        this.receiver = receiver;
    }

//    @PostMapping("user")
//    public ResponseEntity<?> saveUser(@RequestBody User user)
//    {
//
//        ResponseEntity responseEntity;
//        try{
//            User savedUser = userRegistrationService.saveUser(user);
//            UserDTO userDTO = new UserDTO(user.getUsername(), bcryptEncoder.encode(user.getPassword()));
//            rabbitTemplate.convertAndSend(queue.getName(),userDTO);
//            responseEntity = new ResponseEntity<User>( savedUser,HttpStatus.CREATED);
//        }
//        catch (UserAlreadyExistsException e)
//        {
//            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
//
//        }
//        return responseEntity;
//    }


    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestParam("name") String name,
                                      @RequestParam("email") String email,
                                      @RequestParam("userName") String userName,
                                      @RequestParam("dateOfBirth") Date dateOfBirth,
                                      @RequestParam("newsPreferences") String[] newsPreferences,
                                      @RequestParam("password") String password) {
        User user = new User(name,email,userName,dateOfBirth,newsPreferences);
        ResponseEntity responseEntity;
        try{
            User savedUser = userRegistrationService.saveUser(user);
            UserDTO userDTO = new UserDTO(user.getUsername(), bcryptEncoder.encode(password));
            rabbitTemplate.convertAndSend(queue.getName(),userDTO);
            responseEntity = new ResponseEntity<User>( savedUser,HttpStatus.CREATED);
        }
        catch (UserAlreadyExistsException e)
        {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

        }
        return responseEntity;

    }

}
