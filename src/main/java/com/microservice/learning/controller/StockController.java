package com.microservice.learning.controller;


import com.microservice.learning.config.enums.RabbitMQConstants;
import com.microservice.learning.dto.StockDto;
import com.microservice.learning.service.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity updateStock(@RequestBody StockDto stockDto) {
        this.rabbitMQService.sendMessage( RabbitMQConstants.QUEUE_STOCK.getValue(),stockDto );
        return ResponseEntity.ok().build();
    }
}
