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
        Integer num1 = randomFactorGenerator.generateRandomFactor();
        Integer num2 = randomFactorGenerator.generateRandomFactor();

        return new Multiplication(num1, num2);
    }

    @Override
    public boolean checkAttempt(final MultiplicationAttempt attempt) {
        System.out.println("Checked Attempt: " + attempt.getMultiplication().getResult() + "--" + attempt.getResult());
        return attempt.getResult() == attempt.getMultiplication().getResult();
    }
    
}
