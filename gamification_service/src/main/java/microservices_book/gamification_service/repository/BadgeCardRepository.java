package microservices_book.gamification_service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices_book.gamification_service.domain.BadgeCard;

@Repository
public interface BadgeCardRepository extends CrudRepository<BadgeCard, Long>{
    
    /**
     * Retrieves all BadgeCards for a given user.
     * @param userId
     * @return the list of BadgeCards, sorted by most recently awarded
     */
    List<BadgeCard> findByUserIdOrderByBadgeTimestampDesc(final Long userId);
}
