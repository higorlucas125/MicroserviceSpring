package com.microservice.learning.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void  sendMessage(String queue, Object message) {

        try {
            String mensagemJson = this.objectMapper.writeValueAsString(message);
            System.out.println("Message sent to queue " + queue + ": " + message);
            this.rabbitTemplate.convertAndSend(queue, mensagemJson);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
