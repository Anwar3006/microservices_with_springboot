package microservices_book.gamification_service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Links a Badge_Enum to a User.
 * Used to keep track of which badges are awarded to which users and at which timestamp.
 */
@Data
@Entity
public class BadgeCard {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="BADGE_ID")
    private Long badgeId;
    private final Long userId;
    private final long badgeTimestamp;
    private final Badge_Enum badge;

    public BadgeCard(){
        this.badgeId = null;
        this.userId = null;
        this.badgeTimestamp = 0;
        this.badge = null;
    }

    public BadgeCard(final Long userId, final Badge_Enum badge) {
        this.userId = userId;
        this.badge = badge;
        this.badgeTimestamp = System.currentTimeMillis();
    }

    
}
