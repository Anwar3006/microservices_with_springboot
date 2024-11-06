package microservices_book.gamification_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import microservices_book.gamification_service.domain.LeaderBoardRow;
import microservices_book.gamification_service.repository.ScoreCardRepository;

@Service
public class LeaderBoardServiceImpl implements LeaderBoardService {
    
    private final ScoreCardRepository scoreCardRepository;

    public LeaderBoardServiceImpl(ScoreCardRepository scoreCardRepository) {
        this.scoreCardRepository = scoreCardRepository;
    }

    @Override
    public List<LeaderBoardRow> getLeaderBoardFirst10Rows() {
        return scoreCardRepository.getLeaderBoardFirst10();
    }
}
