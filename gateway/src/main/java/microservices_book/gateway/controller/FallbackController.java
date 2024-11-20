package microservices_book.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FallbackController {
    
    @GetMapping("/defaultFallback")
    public ResponseEntity<String> defaultFallBack() {
        return ResponseEntity.ok("Default fallback: Service is down");
    }
    
}
