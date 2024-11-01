package microservices_book.multiplication_service.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * Represents the event that is published when an attempt is solved.
 * Implements {@link Serializable} so this class can be serialized to JSON before publishing.
 */
@Data
public class MultiplicationSolvedEvent implements Serializable{
    
    private final Long attemptId;
    private final Long userId;
    private final boolean correct;
}
