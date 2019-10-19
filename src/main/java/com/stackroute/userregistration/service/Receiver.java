package com.stackroute.userregistration.service;

import com.stackroute.userregistration.domain.User;
import com.stackroute.userregistration.domain.UserDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(UserDTO userDTO) {
//        System.out.println("Received <" + message + ">");
//
//        Logger logger = LoggerFactory.getLogger(Receiver.class);
//        logger.error(message.getFirstName());
//        latch.countDown();
        System.out.println(userDTO.getUsername());
        System.out.println(userDTO.getPassword());
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
