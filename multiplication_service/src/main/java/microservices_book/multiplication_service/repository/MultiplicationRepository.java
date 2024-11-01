package microservices_book.multiplication_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices_book.multiplication_service.domain.Multiplication;

@Repository
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long>{
    
}
