package microservices_book.multiplication_service.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices_book.multiplication_service.domain.MultiplicationAttempt;

@Repository
public interface MultiplicationAttemptRepository extends CrudRepository<MultiplicationAttempt, Long>{
    
    List<MultiplicationAttempt> findByUserAliasOrderByIdDesc(String alias);
}
