package microservices_book.gamification_service.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MultiplicationSolvedEvent {
    
    private Long attemptId;
    private Long userId;
    private boolean correct;

    public MultiplicationSolvedEvent(Long attemptId, Long userId, boolean correct) {
        this.attemptId = attemptId;
        this.userId = userId;
        this.correct = correct;
    }
}
