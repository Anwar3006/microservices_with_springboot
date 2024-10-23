package microservices.book.multiplication_app.service;

import microservices.book.multiplication_app.domain.Multiplication;
import microservices.book.multiplication_app.domain.MultiplicationAttemptResult;
import microservices.book.multiplication_app.helper.RandomFactorGenerator;

public interface MultiplicationService {
    /** 
     * Generates random {@link Multiplication} for our app, relies on the {@link RandomFactorGenerator} for factor generation
     * @return {@link Multiplication} object
    */
    Multiplication generateMultiplication();


    /**
     * Represents the result of a {@link Multiplication} attempt
     * @param MultiplicationAttemptResult
     * @return boolean
     */
    boolean checkAttempt(final MultiplicationAttemptResult attempt);
}
