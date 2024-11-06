package microservices_book.gamification_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices_book.gamification_service.domain.LeaderBoardRow;
import microservices_book.gamification_service.service.LeaderBoardService;

@RestController
@RequestMapping("/leaders")
public class LeaderBoardController {
    
    private final LeaderBoardService leaderBoardService;

    public LeaderBoardController(LeaderBoardService leaderBoardService) {
        this.leaderBoardService = leaderBoardService;
    }


    @GetMapping
    public List<LeaderBoardRow> getLeaderBoard(){
        return leaderBoardService.getLeaderBoardFirst10Rows();
    }
}
