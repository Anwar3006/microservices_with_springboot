package microservices.book.multiplication_app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices.book.multiplication_app.domain.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long>{
    
}
