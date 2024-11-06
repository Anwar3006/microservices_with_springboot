package microservices_book.gamification_service.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class LeaderBoardRow {
    
    private final Long userId;
    private final Long totalScore;
    
    public LeaderBoardRow() {
        this.userId = null;
        this.totalScore = 0L;
    }
}
