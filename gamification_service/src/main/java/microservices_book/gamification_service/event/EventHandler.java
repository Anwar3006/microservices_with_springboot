package microservices_book.gamification_service.event;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import microservices_book.gamification_service.service.GameService;

@Slf4j
@Component
public class EventHandler {
    
    private final GameService gameService;

    public EventHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @RabbitListener(queues="${multiplication.queue}")
    public void handleMultiplication(final MultiplicationSolvedEvent event){
        log.info("Event received for Multiplication Event: {}", event.getAttemptId());
        try {
           gameService.processNewAttempt(event.getUserId(), event.getAttemptId(), event.isCorrect()); 
        } catch (Exception e) {
            log.error("Error processing event: {}", e);
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
