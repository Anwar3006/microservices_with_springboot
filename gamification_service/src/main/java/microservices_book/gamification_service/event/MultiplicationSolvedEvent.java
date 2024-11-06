package microservices_book.gamification_service.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultiplicationSolvedEvent {
    
    private final Long attemptId;
    private final Long userId;
    private final boolean correct;


    public MultiplicationSolvedEvent(Long attemptId, Long userId, boolean correct) {
        this.attemptId = attemptId;
        this.userId = userId;
        this.correct = correct;
    }
}
