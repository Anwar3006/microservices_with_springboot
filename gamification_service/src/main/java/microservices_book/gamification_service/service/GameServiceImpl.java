package microservices_book.gamification_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import microservices_book.gamification_service.domain.BadgeCard;
import microservices_book.gamification_service.domain.Badge_Enum;
import microservices_book.gamification_service.domain.GameStats;
import microservices_book.gamification_service.domain.ScoreCard;
import microservices_book.gamification_service.repository.BadgeCardRepository;
import microservices_book.gamification_service.repository.ScoreCardRepository;

@Service
@Slf4j
public class GameServiceImpl implements GameService{

    private final ScoreCardRepository scoreCardRepository;
    private final BadgeCardRepository badgeCardRepository;

    public GameServiceImpl(ScoreCardRepository scoreCardRepository, BadgeCardRepository badgeCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
        this.badgeCardRepository = badgeCardRepository;
    }


    @Override
    public GameStats processNewAttempt(Long userId, Long attemptId, boolean correct) {
        //give points only if attempt is correct
        if(correct){
            ScoreCard scoreCard = new ScoreCard(userId, attemptId);
            scoreCardRepository.save(scoreCard);
            log.info("User with id: {} scored: {} points for attemptId: {}", userId, scoreCard.getScore(), attemptId);
            List<BadgeCard> badgeCards = processForBadges(userId);
            return new GameStats(userId, 
                                 scoreCard.getScore(), 
                                 badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));
        }
        return GameStats.emptyStats(userId);
    }


    @Override
    public GameStats getStatsForUser(Long userId) {
        int score = scoreCardRepository.getTotalScoreForUser(userId);
        List<BadgeCard> badgeCards = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);
        GameStats userStats = new GameStats(userId, score, badgeCards.stream().map(BadgeCard::getBadge).collect(Collectors.toList()));
        return userStats;
    }


    // --------------------------------------- HELPER Functions
    private List<BadgeCard> processForBadges(final Long userId){
        List<BadgeCard> badgeCards = new ArrayList<>();

        int totalScore = scoreCardRepository.getTotalScoreForUser(userId);
        log.info("User with id: {} has scored a total of {} points", userId, totalScore);
        List<ScoreCard> scoreCardsList = scoreCardRepository.findByUserIdOrderByScoreTimestampDesc(userId);
        List<BadgeCard> badgeCardsList = badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId);

        //check and assign badges
        checkAndAssignBadgesBasedOnScore(totalScore, userId, Badge_Enum.BRONZE_MULTIPLICATOR, 100, badgeCardsList)
                                    .ifPresent(badgeCards::add);
        checkAndAssignBadgesBasedOnScore(totalScore, userId, Badge_Enum.SILVER_MULTIPLICATOR, 500, badgeCardsList)
                                    .ifPresent(badgeCards::add);
        checkAndAssignBadgesBasedOnScore(totalScore, userId, Badge_Enum.GOLD_MULTIPLICATOR, 999, badgeCardsList)
                                            .ifPresent(badgeCards::add);
                
        //conditional to check for first attempt
        if(scoreCardsList.size() == 1 && !containsBadge(badgeCardsList, Badge_Enum.FIRST_WON)){
            BadgeCard firstWonBadge = giveBadgeToUser(userId, Badge_Enum.FIRST_WON);
            badgeCards.add(firstWonBadge);
        }
        return badgeCards;
    }
        
    private Optional<BadgeCard> checkAndAssignBadgesBasedOnScore(int totalScore, Long userId, 
                                                                Badge_Enum badgeType, int scoreThreshold,
                                                                List<BadgeCard> badgeCardsList) {
        //check if totalscore greater than or equal to threshold
        if(totalScore >= scoreThreshold && !containsBadge(badgeCardsList, badgeType)){
            return Optional.of(giveBadgeToUser(userId, badgeType));
        }
        return Optional.empty();
    } 
            
    private BadgeCard giveBadgeToUser(Long userId, Badge_Enum badgeType){
        BadgeCard newBadge = new BadgeCard(userId, badgeType);
        badgeCardRepository.save(newBadge);
        return newBadge;
    }

    private Boolean containsBadge(List<BadgeCard> badgeCardsList, Badge_Enum badgeType){
        return badgeCardsList.stream().anyMatch(b -> b.getBadge().equals(badgeType));
    }
}
