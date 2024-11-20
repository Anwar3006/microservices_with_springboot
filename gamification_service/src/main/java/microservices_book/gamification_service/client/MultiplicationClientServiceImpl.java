package microservices_book.gamification_service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import microservices_book.gamification_service.client.dto.MultiplicationAttempt;


@Component
public class MultiplicationClientServiceImpl implements MultiplicationClientService{

    private final RestTemplate restTemplate;
    private final String multiplicationHost;

    

    public MultiplicationClientServiceImpl(RestTemplate restTemplate, @Value("${multiplicationHost}") final String multiplicationHost) {
        this.restTemplate = restTemplate;
        this.multiplicationHost = multiplicationHost;
    }



    @CircuitBreaker(name = "multiplicationAttempt", fallbackMethod = "getAttemptByIdFallback")
    @Override
    public MultiplicationAttempt getAttemptById(Long attemptId) {
        return restTemplate.getForObject(multiplicationHost + "/results/" + attemptId, MultiplicationAttempt.class);
    }
    

    public MultiplicationAttempt getAttemptByIdFallback(Long attemptId, Throwable throwable) {
        // Log the exception for debugging
        System.err.println("Fallback triggered due to: " + throwable.getMessage());
        // Return a default response
        return new MultiplicationAttempt("NoUser", 0, 0, 0, true);
    }
    
}
