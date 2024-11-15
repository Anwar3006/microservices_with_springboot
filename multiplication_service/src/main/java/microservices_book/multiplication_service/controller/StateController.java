package microservices_book.multiplication_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StateController {

    @GetMapping("/state")
    public ResponseEntity<String> state() {
        return ResponseEntity.ok("OK");
    }
}
