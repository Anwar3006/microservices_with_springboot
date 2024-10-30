package microservices_book.social_multiplication.service;

import java.util.List;

import microservices_book.social_multiplication.domain.MultiplicationAttempt;

public interface MultiplicationAttemptService {
    /**
     * Get a history of {@link MultiplicationAttempt} for this specific user.
     * @param {@link AppUser$alias}
     * @return List of {@link MultiplicationAttempt}
     */
    List<MultiplicationAttempt> getAttemptsHistory(final String alias);
}
