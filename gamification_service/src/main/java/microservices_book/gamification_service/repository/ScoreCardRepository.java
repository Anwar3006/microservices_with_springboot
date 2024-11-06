package microservices_book.gamification_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import microservices_book.gamification_service.domain.LeaderBoardRow;
import microservices_book.gamification_service.domain.ScoreCard;

@Repository
public interface ScoreCardRepository extends CrudRepository<ScoreCard, Long>{
    /**
     * Gets the total score for a given user, being the sum of all scores in his {@link ScoreCard}s.
     * @param userId
     * @return the total score for a given user
     */
    @Query("SELECT SUM(s.score) FROM ScoreCard s WHERE s.userId = :userId GROUP BY s.userId")
    int getTotalScoreForUser(@Param("userId") final Long userId);


    /**
     * Retrieves a list of {@link LeaderBoardRow}s representing the LeaderBoard of users & their total scores.
     * @return the leader board, sorted by highest score first.
     */
    @Query("SELECT new microservices_book.gamification_service.domain.LeaderBoardRow(s.userId, SUM(s.score)) FROM ScoreCard s GROUP BY s.userId ORDER BY SUM(s.score) DESC")
    List<LeaderBoardRow> getLeaderBoardFirst10();


    /**
     * Retrieves all ScoreCards for a given user.
     * @param userId
     * @return the list of ScoreCards, sorted by most recent attempt
     */
    List<ScoreCard> findByUserIdOrderByScoreTimestampDesc(final Long userId);
}
