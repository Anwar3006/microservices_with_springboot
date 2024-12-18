package microservices_book.multiplication_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import microservices_book.multiplication_service.domain.MultiplicationAttempt;
import microservices_book.multiplication_service.service.MultiplicationAttemptService;
import microservices_book.multiplication_service.service.MultiplicationService;

@Slf4j
@RestController
@RequestMapping("/results")
public class MultiplicationAttemptController {
    
    private final MultiplicationService multiplicationService;
    private final MultiplicationAttemptService attemptService;
    private final int serverPort;

    public MultiplicationAttemptController(MultiplicationService multiplicationService,
            MultiplicationAttemptService attemptService, @Value("${server.port}") final int serverPort) {
        this.multiplicationService = multiplicationService;
        this.attemptService = attemptService;
        this.serverPort = serverPort;
    }


    
    @PostMapping(name="", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MultiplicationAttempt> postAttempt(@RequestBody final MultiplicationAttempt attempt){
        MultiplicationAttempt result = new MultiplicationAttempt(attempt.getMultiplication(), 
                                                            attempt.getUser(), 
                                                            attempt.getResult(), 
                                                            multiplicationService.checkAttempt(attempt));
        return ResponseEntity.ok().header("Access-Control-Allow-Origin", "http://localhost:9090").body(result);
    }

    @GetMapping
    public ResponseEntity<List<MultiplicationAttempt>> getHistory(@RequestParam(name="alias") String alias) {
        List<MultiplicationAttempt> history = attemptService.getAttemptsHistory(alias);
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/{attemptId}")
    public ResponseEntity<MultiplicationAttempt> getAttemptById(@PathVariable Long attemptId) {
        log.info("Retreiving result {} from server port {}", attemptId, serverPort);
        MultiplicationAttempt attempt = attemptService.getAttemptById(attemptId);
        return ResponseEntity.ok(attempt);
        // return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); added to test the fallback mechanism for the Gamification-Multiplication interaction.
    }
}
