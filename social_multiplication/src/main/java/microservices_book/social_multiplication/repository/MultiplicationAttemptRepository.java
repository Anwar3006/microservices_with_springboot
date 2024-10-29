package microservices_book.social_multiplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices_book.social_multiplication.domain.MultiplicationAttempt;

@Repository
public interface MultiplicationAttemptRepository extends CrudRepository<MultiplicationAttempt, Long>{
    
}