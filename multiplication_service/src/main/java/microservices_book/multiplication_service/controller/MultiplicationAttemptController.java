package microservices_book.multiplication_service.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import microservices_book.multiplication_service.domain.MultiplicationAttempt;
import microservices_book.multiplication_service.service.MultiplicationAttemptService;
import microservices_book.multiplication_service.service.MultiplicationService;


@RestController
@RequestMapping("/results")
public class MultiplicationAttemptController {
    
    private final MultiplicationService multiplicationService;
    private final MultiplicationAttemptService attemptService;
    
    public MultiplicationAttemptController(MultiplicationService multiplicationService,
            MultiplicationAttemptService attemptService) {
        this.multiplicationService = multiplicationService;
        this.attemptService = attemptService;
    }

    @PostMapping(name="", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MultiplicationAttempt> postAttempt(@RequestBody final MultiplicationAttempt attempt){
        MultiplicationAttempt result = new MultiplicationAttempt(attempt.getMultiplication(), 
                                                            attempt.getUser(), 
                                                            attempt.getResult(), 
                                                            multiplicationService.checkAttempt(attempt));
        return ResponseEntity.ok(result);
    }

    @GetMapping
    public ResponseEntity<List<MultiplicationAttempt>> getHistory(@RequestParam(name="alias") String alias) {
        List<MultiplicationAttempt> history = attemptService.getAttemptsHistory(alias);
        return ResponseEntity.ok(history);
    }
    
    @GetMapping
    public ResponseEntity<MultiplicationAttempt> getAttemptById(@PathVariable Long attemptId) {
        MultiplicationAttempt attempt = attemptService.getAttemptById(attemptId);
        return ResponseEntity.ok(attempt);
    }
}
