package microservices.book.multiplication_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices.book.multiplication_app.domain.Multiplication;

@Repository
public interface MultiplicationRepository extends CrudRepository<Multiplication, Long>{
    
}
