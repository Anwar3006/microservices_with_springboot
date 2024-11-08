package microservices_book.gamification_service.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.Setter;
import microservices_book.gamification_service.client.MultiplicationAttemptDeserializer;

@Getter
@Setter
@JsonDeserialize(using = MultiplicationAttemptDeserializer.class)
public class MultiplicationAttempt {
    
    private final String userAlias;

    private final int MultiplicationFactorA;
    private final int MultiplicationFactorB;

    private final int result;
    private final boolean correct;


    public MultiplicationAttempt(String userAlias, int multiplicationFactorA, int multiplicationFactorB, int result,
            boolean correct) {
        this.userAlias = userAlias;
        MultiplicationFactorA = multiplicationFactorA;
        MultiplicationFactorB = multiplicationFactorB;
        this.result = result;
        this.correct = correct;
    }

}
