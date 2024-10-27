package springboot_jenkins.circus_ride_api.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import springboot_jenkins.circus_ride_api.domain.ThemeParkRide;

@Repository
public interface ThemeParkRideRepository extends CrudRepository<ThemeParkRide, Long>{
    
    List<ThemeParkRide> findByName(String name);
}
