package microservices_book.gamification_service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Links a Score to a User's attempt, with an associated User and timestamp.
 */
@Getter
@Setter
@Entity
public class ScoreCard {
    //Default score card
    private static final int DEFAULT_SCORE = 10;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="SCORE_ID")
    private Long scoreId;

    @Column(name="USER_ID")
    private final Long userId;

    @Column(name="ATTEMPT_ID")
    private final Long attemptId;

    @Column(name="SCORE_TS")
    private final long scoreTimestamp;

    @Column(name="SCORE")
    private final int score;

    public ScoreCard() {
        this.scoreId = null;
        this.userId = null;
        this.attemptId = null;
        this.scoreTimestamp = 0;
        this.score = 0;
    }

    public ScoreCard(final Long userId, Long attemptId) {
        this.userId = userId;
        this.attemptId = attemptId;
        this.scoreTimestamp = System.currentTimeMillis();
        this.score = DEFAULT_SCORE;
    }

    
}
