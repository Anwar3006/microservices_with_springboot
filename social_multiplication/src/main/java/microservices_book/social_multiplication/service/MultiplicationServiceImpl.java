package microservices_book.social_multiplication.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;
import microservices_book.social_multiplication.domain.AppUser;
import microservices_book.social_multiplication.domain.Multiplication;
import microservices_book.social_multiplication.domain.MultiplicationAttempt;
import microservices_book.social_multiplication.repository.AppUserRepository;
import microservices_book.social_multiplication.repository.MultiplicationAttemptRepository;
import microservices_book.social_multiplication.utils.RandomFactorGenerator;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

    private final RandomFactorGenerator randomFactorGenerator;
    private final AppUserRepository userRepository;
    private final MultiplicationAttemptRepository attemptRepository;
    
    public MultiplicationServiceImpl(RandomFactorGenerator randomFactorGenerator, AppUserRepository userRepository,
            MultiplicationAttemptRepository attemptRepository) {
        this.randomFactorGenerator = randomFactorGenerator;
        this.userRepository = userRepository;
        this.attemptRepository = attemptRepository;
    }

    @Override
    public Multiplication generateMultiplication() {
        Integer num1 = randomFactorGenerator.generateRandomFactor();
        Integer num2 = randomFactorGenerator.generateRandomFactor();

        return new Multiplication(num1, num2);
    }

    @Transactional
    @Override
    public boolean checkAttempt(final MultiplicationAttempt attempt) {
        //check the user making the attempt to link the attempt to the user if already exists
        Optional<AppUser> userOptional = userRepository.findUserByAlias(attempt.getUser().getAlias());

        //avoid Hack attempts
        Assert.isTrue(!attempt.isCorrect(), "You can't manually set the result of an attempt");
        
        boolean isCorrect = attempt.getResult() == attempt.getMultiplication().getResult();

        MultiplicationAttempt attemptToSave = new MultiplicationAttempt(attempt.getMultiplication(), 
                                                                        userOptional.orElse(attempt.getUser()), 
                                                                        attempt.getResult(), 
                                                                        isCorrect);
        //save the attempt
        attemptRepository.save(attemptToSave);
        return isCorrect;
    }
    
}
