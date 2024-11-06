package microservices_book.gamification_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservices_book.gamification_service.domain.GameStats;
import microservices_book.gamification_service.service.GameService;

@RestController
@RequestMapping("/stats")
public class UserStatsController {

    private final GameService gameService;
    
    public UserStatsController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping
    public GameStats gameStatsForUser(@RequestParam("userId") final Long userId){
        return gameService.getStatsForUser(userId);
    }
}
