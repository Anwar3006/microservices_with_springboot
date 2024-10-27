package microservices.book.multiplication_app.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import microservices.book.multiplication_app.domain.Multiplication;
import microservices.book.multiplication_app.domain.MultiplicationAttemptResult;
import microservices.book.multiplication_app.helper.RandomFactorGenerator;

@Service
@AllArgsConstructor
public class MultiplicationServiceImpl implements MultiplicationService{

    private final RandomFactorGenerator randomFactorGenerator;

    @Override
    public Multiplication generateMultiplication() {
        int factorA = randomFactorGenerator.generateRandomFactor();
        int factorB = randomFactorGenerator.generateRandomFactor();

        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(final MultiplicationAttemptResult attempt) {
        System.out.println("Checking attempt: " + attempt.toString());
        boolean correct = attempt.getResult() == attempt.getMultiplication().getFactorA() * attempt.getMultiplication().getFactorB();
        return correct;
    }
}
