package microservices_book.multiplication_service.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import microservices_book.multiplication_service.domain.Multiplication;
import microservices_book.multiplication_service.service.MultiplicationService;

@Slf4j
@RestController
@RequestMapping("/multiplications")
public class MultiplicationController {
    
    MultiplicationService multiplicationService;
    private final int serverPort;

    public MultiplicationController(MultiplicationService multiplicationService, @Value("${server.port}") final int serverPort) {
        this.multiplicationService = multiplicationService;
        this.serverPort = serverPort;
    }

    @GetMapping("/random")
    public ResponseEntity<Multiplication> getMultiplication() {
        log.info("Generating a random multiplication from server port: {}", serverPort);
        return ResponseEntity.ok(multiplicationService.generateMultiplication());
    }
    
}
