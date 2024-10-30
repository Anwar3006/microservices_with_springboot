package microservices_book.social_multiplication.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;
import microservices_book.social_multiplication.domain.MultiplicationAttempt;
import microservices_book.social_multiplication.service.MultiplicationService;

@RestController
@RequestMapping("/results")
public class MultiplicationAttemptController {
    
    private final MultiplicationService multiplicationService;

    public MultiplicationAttemptController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @AllArgsConstructor
    @Data
    public static class AttemptResponseBody{
        private boolean correct;
    }
    
    @PostMapping(name="", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AttemptResponseBody> postAttempt(@RequestBody final MultiplicationAttempt attempt){
        System.out.println("Attempt: " + attempt.getMultiplication().toString() + " " + attempt.getResult());
        return ResponseEntity.ok(new AttemptResponseBody(multiplicationService.checkAttempt(attempt)));
    }
}
