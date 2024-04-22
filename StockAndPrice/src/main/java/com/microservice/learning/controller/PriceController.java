package com.microservice.learning.controller;


import com.microservice.learning.service.RabbitMQService;
import org.librabbimq.dto.PriceDto;
import org.librabbimq.enums.RabbitMQConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    public ResponseEntity updatePrice(@RequestBody PriceDto priceDto) {
        this.rabbitMQService.sendMessage( RabbitMQConstants.QUEUE_PRICE.getValue(), priceDto );
        return ResponseEntity.ok().build();
    }
}
