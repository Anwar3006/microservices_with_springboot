package microservices.book.multiplication_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservices.book.multiplication_app.domain.Multiplication;
import microservices.book.multiplication_app.service.MultiplicationService;




@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {

    @Autowired
    private MultiplicationService multiplicationService;
    
    @GetMapping("/random")
    public ResponseEntity<Multiplication> getMultiplication() {
        return ResponseEntity.ok(multiplicationService.generateMultiplication());
    }
    
    @GetMapping("/result")
    public ResponseEntity<Boolean> getAttemptResult() {
        return ResponseEntity.ok(false);
    }
    
}
