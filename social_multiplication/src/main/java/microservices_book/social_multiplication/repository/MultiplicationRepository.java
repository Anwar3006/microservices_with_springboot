package microservices_book.social_multiplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices_book.social_multiplication.domain.Multiplication;

@Repository
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long>{
    
}
