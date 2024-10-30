package microservices_book.social_multiplication.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices_book.social_multiplication.domain.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long>{
    
    Optional<AppUser> findUserByAlias(final String alias);
}
