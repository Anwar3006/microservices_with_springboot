package microservices_book.social_multiplication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices_book.social_multiplication.domain.Multiplication;
import microservices_book.social_multiplication.service.MultiplicationService;


@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {
    
    MultiplicationService multiplicationService;

    public MultiplicationController(MultiplicationService multiplicationService) {
        this.multiplicationService = multiplicationService;
    }

    @GetMapping("/random")
    public ResponseEntity<Multiplication> getMultiplication() {
        return ResponseEntity.ok(multiplicationService.generateMultiplication());
    }
    
}
