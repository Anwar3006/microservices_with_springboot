package microservices_book.multiplication_service.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import microservices_book.multiplication_service.domain.AppUser;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Long>{
    
    Optional<AppUser> findUserByAlias(final String alias);
}
