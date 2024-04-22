package org.librabbimq.comsumerstock.consumer;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.librabbimq.constants.RabbitMQConstant;
import org.librabbimq.dto.StockDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class StockConsumer {


    @RabbitListener(queues = RabbitMQConstant.QUEUE_STOCK )
    private void consumer( String mensagem)
        throws
        JsonProcessingException {
        StockDto stockDto = new ObjectMapper().readValue( mensagem, StockDto.class);
        System.out.println(stockDto.codeProduct);
        System.out.println(stockDto.amount);
        System.out.println("------------------------------------");

    }

}
