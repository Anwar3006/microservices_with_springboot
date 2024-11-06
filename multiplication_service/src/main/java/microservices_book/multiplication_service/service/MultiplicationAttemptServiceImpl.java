package microservices_book.multiplication_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import microservices_book.multiplication_service.domain.MultiplicationAttempt;
import microservices_book.multiplication_service.repository.MultiplicationAttemptRepository;

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

    @Override
    public MultiplicationAttempt getAttemptById(Long attemptId) {
        return attemptRepository.findById(attemptId).get();
    }
    
}
