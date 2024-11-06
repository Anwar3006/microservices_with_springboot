package microservices_book.gamification_service.service;

import java.util.List;

import microservices_book.gamification_service.domain.LeaderBoardRow;

public interface LeaderBoardService {
    
    /**
     * Get the leader board, sorted by highest score first.
     * @return the leader board with first 10 rows
     */
    List<LeaderBoardRow> getLeaderBoardFirst10Rows();
}
