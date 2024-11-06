package microservices_book.gamification_service.service;

import microservices_book.gamification_service.domain.GameStats;

public interface GameService {
    /**
     * Processes a new attempt for a given user.
     * 
     * @param userId
     * @param attemptId
     * @param correct
     * 
     * @return a {@link GameStats} object
     */
    GameStats processNewAttempt(final Long userId, final Long attemptId, final boolean correct);


    /**
     * Retrieves GameStats for a given user.
     * 
     * @param userId
     * 
     * @return a {@link GameStats} object
     */
    GameStats getStatsForUser(final Long userId);
}
