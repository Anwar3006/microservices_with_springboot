package microservices_book.social_multiplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import microservices_book.social_multiplication.domain.Multiplication;
import microservices_book.social_multiplication.domain.MultiplicationAttempt;
import microservices_book.social_multiplication.utils.RandomFactorGenerator;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

    @Autowired
    private RandomFactorGenerator randomFactorGenerator;
    
    @Override
    public Multiplication generateMultiplication() {
        Integer factorA = randomFactorGenerator.generateRandomFactor();
        Integer factorB = randomFactorGenerator.generateRandomFactor();

        return new Multiplication(factorA, factorB);
    }

    @Override
    public boolean checkAttempt(MultiplicationAttempt attempt) {
        // TODO Auto-generated method stub
        return attempt.isCorrect();
    }
    
}
