package microservices_book.multiplication_service.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import jakarta.transaction.Transactional;
import microservices_book.multiplication_service.domain.AppUser;
import microservices_book.multiplication_service.domain.Multiplication;
import microservices_book.multiplication_service.domain.MultiplicationAttempt;
import microservices_book.multiplication_service.domain.MultiplicationSolvedEvent;
import microservices_book.multiplication_service.event.EventDispatcher;
import microservices_book.multiplication_service.repository.AppUserRepository;
import microservices_book.multiplication_service.repository.MultiplicationAttemptRepository;
import microservices_book.multiplication_service.utils.RandomFactorGenerator;

@Service
public class MultiplicationServiceImpl implements MultiplicationService{

    private final RandomFactorGenerator randomFactorGenerator;
    private final AppUserRepository userRepository;
    private final MultiplicationAttemptRepository attemptRepository;
    private final EventDispatcher eventDispatcher;
    
    public MultiplicationServiceImpl(RandomFactorGenerator randomFactorGenerator, AppUserRepository userRepository,
            MultiplicationAttemptRepository attemptRepository, final EventDispatcher eventDispatcher) {
        this.randomFactorGenerator = randomFactorGenerator;
        this.userRepository = userRepository;
        this.attemptRepository = attemptRepository;
        this.eventDispatcher = eventDispatcher;
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

        //communicate the result via Event
        eventDispatcher.send(new MultiplicationSolvedEvent(attemptToSave.getId(), 
                                                            attemptToSave.getUser().getId(),
                                                            attemptToSave.isCorrect()));
        return isCorrect;
    }
    
}
