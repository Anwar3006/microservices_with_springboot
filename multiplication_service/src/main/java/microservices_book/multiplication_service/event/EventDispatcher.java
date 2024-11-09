package microservices_book.multiplication_service.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import microservices_book.multiplication_service.domain.MultiplicationSolvedEvent;

@Component
public class EventDispatcher {

    private final RabbitTemplate rabbitTemplate;
    
    //the exchange to use
    private final String multiplicationExchange;

    //the routing key to use
    private final String multiplicationSolvedRoutingKey;

    public EventDispatcher(RabbitTemplate rabbitTemplate, 
                           @Value("${multiplication.exchange}")final String multiplicationExchange,
                           @Value("${multiplication.solved.key}")final String multiplicationSolvedRoutingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.multiplicationExchange = multiplicationExchange;
        this.multiplicationSolvedRoutingKey = multiplicationSolvedRoutingKey;
    }

    public void send(final MultiplicationSolvedEvent solvedEvent){
        rabbitTemplate.convertAndSend(multiplicationExchange, multiplicationSolvedRoutingKey, solvedEvent);
        System.out.println("Sending event: " + solvedEvent.toString());
    }
    
}
