/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.gamification_service.test_service;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;


import microservices_book.gamification_service.client.MultiplicationClientService;
import microservices_book.gamification_service.client.dto.MultiplicationAttempt;
import microservices_book.gamification_service.domain.BadgeCard;
import microservices_book.gamification_service.domain.Badge_Enum;
import microservices_book.gamification_service.domain.GameStats;
import microservices_book.gamification_service.domain.ScoreCard;
import microservices_book.gamification_service.repository.BadgeCardRepository;
import microservices_book.gamification_service.repository.ScoreCardRepository;
import microservices_book.gamification_service.service.GameServiceImpl;
 
/**
 *
 * @author anwa
 */
// @ExtendWith(MockitoExtension.class)
public class GameServiceImplUnitTest {

    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private ScoreCardRepository scoreCardRepository;

    @Mock
    private BadgeCardRepository badgeCardRepository;

    @Mock
    private MultiplicationClientService attemptClientService;

    private final Long userId = 1L;
    private final Long attemptId = 122L;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void processNewAttemptTest_CorrectAttempt() throws Exception{
        //given
        given(scoreCardRepository.getTotalScoreForUser(userId)).willReturn(150);
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId)).willReturn(new ArrayList<>());
        MultiplicationAttempt attempt = new MultiplicationAttempt("john_snow", 20, 40, 800, true);
        given(attemptClientService.getAttemptById(attemptId)).willReturn(attempt);

        //when
        GameStats newStats = gameService.processNewAttempt(userId, attemptId, true);

        //then
        assertThat(newStats.getScore()).isEqualTo(10); //ensure the score assigned for a correct attempt is 10
        assertTrue(newStats.getBadges().contains(Badge_Enum.BRONZE_MULTIPLICATOR));
        verify(scoreCardRepository).save(any(ScoreCard.class));
        verify(badgeCardRepository).save(any(BadgeCard.class));
    }

    @Test
    public void processNewAttemptTest_WrongAttempt() throws Exception{
        //given
        given(scoreCardRepository.getTotalScoreForUser(userId)).willReturn(150);
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId)).willReturn(new ArrayList<>());
        MultiplicationAttempt attempt = new MultiplicationAttempt("john_snow", 20, 40, 810, false);
        given(attemptClientService.getAttemptById(attemptId)).willReturn(attempt);

        //when
        GameStats getStats = gameService.processNewAttempt(userId, attemptId, false);

        //then
        assertThat(getStats.getScore()).isEqualTo(0);
        assertThat(getStats.getBadges()).isEmpty();
        verify(scoreCardRepository, never()).save(any(ScoreCard.class));
        verify(badgeCardRepository, never()).save(any(BadgeCard.class));
    }

    @Test
    public void getStatsForUserTest() throws Exception{
        //given
        List<BadgeCard> badges =  List.of(new BadgeCard(userId, Badge_Enum.FIRST_WON), 
                                            new BadgeCard(userId,Badge_Enum.BRONZE_MULTIPLICATOR));
        given(scoreCardRepository.getTotalScoreForUser(userId)).willReturn(200);
        given(badgeCardRepository.findByUserIdOrderByBadgeTimestampDesc(userId)).willReturn(badges);

        //when - request stats for existing user(getStats) VS non-existing user(getStats_2)
        GameStats getStats = gameService.getStatsForUser(userId);
        System.out.println(getStats.getBadges());
        //then
        assertThat(getStats.getScore()).isEqualTo(200);
        assertThat(getStats.getBadges()).containsExactlyInAnyOrder(Badge_Enum.FIRST_WON, Badge_Enum.BRONZE_MULTIPLICATOR);
    }
}