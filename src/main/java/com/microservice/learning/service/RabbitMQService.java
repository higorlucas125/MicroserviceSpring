package com.microservice.learning.service;


import com.microservice.learning.dto.StockDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void  sendMessage(String queue, Object message) {
        this.rabbitTemplate.convertAndSend( queue, message);
        System.out.println("Message sent to queue " + queue + ": " + message);
    }
}
