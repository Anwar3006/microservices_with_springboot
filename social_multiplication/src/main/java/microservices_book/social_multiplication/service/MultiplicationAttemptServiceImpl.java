package microservices_book.social_multiplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import microservices_book.social_multiplication.domain.MultiplicationAttempt;
import microservices_book.social_multiplication.repository.MultiplicationAttemptRepository;

@Service
public class MultiplicationAttemptServiceImpl implements MultiplicationAttemptService{

    private final MultiplicationAttemptRepository attemptRepository;

    public MultiplicationAttemptServiceImpl(MultiplicationAttemptRepository attemptRepository) {
        this.attemptRepository = attemptRepository;
    }


    @Override
    public List<MultiplicationAttempt> getAttemptsHistory(String alias) {
        return attemptRepository.findByUserAliasOrderByIdDesc(alias);
    }
    
}
