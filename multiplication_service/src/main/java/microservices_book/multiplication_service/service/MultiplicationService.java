package microservices_book.multiplication_service.service;

import microservices_book.multiplication_service.domain.Multiplication;
import microservices_book.multiplication_service.domain.MultiplicationAttempt;

public interface MultiplicationService {
    /**
     * Generates {@link Multiplication} for this application, using the {@link RandomFactorGenerator}
     * @return {@link Multiplication}
     */
    Multiplication generateMultiplication();

    /**
     * Check the {@link MultiplicationAttempt} for correctness
     * @return true/false
     */
    boolean checkAttempt(final MultiplicationAttempt attempt);
}
