package com.microservice.learning.config;

import jakarta.annotation.PostConstruct;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.stereotype.Component;


import static org.librabbimq.enums.Exchanges.EXCHANGE_DIRECT;
import static org.librabbimq.enums.RabbitMQConstants.QUEUE_PRICE;
import static org.librabbimq.enums.RabbitMQConstants.QUEUE_STOCK;


@Component
public class RabbitMQConnection {

    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection( AmqpAdmin amqpAdmin ) {
        this.amqpAdmin = amqpAdmin;
    }

    // Criando as filas
    private Queue queue ( String queueName ) {
        return new Queue( queueName, true , false, false );
    }

    // Criando a excenge
    private DirectExchange exchange (  ) {
        return new DirectExchange( EXCHANGE_DIRECT.getValue() );
    }

    // Criando o binding
    private Binding binding ( Queue queue, DirectExchange exchange ) {
        return new Binding( queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(), null);
    }

    // Criando o metodo que vai executar a criação da fila, exchange e binding
    @PostConstruct
    private void add(){
        Queue queueStock = this.queue( QUEUE_STOCK.getValue() );
        Queue queuePrice = this.queue( QUEUE_PRICE.getValue() );

        DirectExchange exchange = this.exchange();

        Binding connectionStock = this.binding( queueStock, exchange );
        Binding connectionPrice = this.binding( queuePrice, exchange );

        // CRAIANDO as filas do RabbitMQ
        this.amqpAdmin.declareQueue( queueStock );
        this.amqpAdmin.declareQueue( queuePrice );

        this.amqpAdmin.declareExchange( exchange );
        this.amqpAdmin.declareBinding( connectionStock );
        this.amqpAdmin.declareBinding( connectionPrice );

    }
}
