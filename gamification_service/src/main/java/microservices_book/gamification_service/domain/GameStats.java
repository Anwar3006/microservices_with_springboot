package microservices_book.gamification_service.domain;

import java.util.Collections;
import java.util.List;

import lombok.Data;

/**
 * Represents one or many iterations of the game.
 * Can contain any combination of {@link ScoreCard} and {@link BadgeCard} objects.
 * Can be used to track the stats for a single game of a User or multiple games of that User.
 */
@Data
public class GameStats {
    private final Long userId;
    private final int score;
    private final List<Badge_Enum> badges;

    /**
     * Builds an empty GameStats instance. For starting a new game.
     * @param userId
     * @return a {@link GameStats} object withj zero score and empty list of badges
     */
    public static GameStats emptyStats(final Long userId){
        return new GameStats(userId, 0, Collections.emptyList());
    }

    /**
     * @return Immutable view of the list of badges
     */
    public List<Badge_Enum> getBadges() {
        return Collections.unmodifiableList(badges);
    }
}
