package com.example.demo.user;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import user.user;


@Component
public class consumerUser {
    
    @KafkaListener(topics = "avroTestingT", groupId="user-servicse")
    public void consumerResponse(user User){
        System.out.println(User.getName());
    }
}
