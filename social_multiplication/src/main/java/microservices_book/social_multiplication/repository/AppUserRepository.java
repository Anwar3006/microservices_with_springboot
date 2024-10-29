package microservices_book.social_multiplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices_book.social_multiplication.domain.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long>{
    
}
