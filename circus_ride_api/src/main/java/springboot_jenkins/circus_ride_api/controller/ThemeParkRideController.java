package springboot_jenkins.circus_ride_api.controller;

import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot_jenkins.circus_ride_api.domain.ThemeParkRide;
import springboot_jenkins.circus_ride_api.repository.ThemeParkRideRepository;



@RestController
@RequestMapping("ride")
public class ThemeParkRideController {
    
    private final ThemeParkRideRepository themeParkRideRepository;

    public ThemeParkRideController(ThemeParkRideRepository themeParkRideRepository) {
        this.themeParkRideRepository = themeParkRideRepository;
    }

    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<ThemeParkRide>> getRides() {
        return ResponseEntity.ok(themeParkRideRepository.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThemeParkRide> getRide(@PathVariable Long id) {
        Optional<ThemeParkRide> ride = themeParkRideRepository.findById(id);
        if(ride.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ride.get());
    }
    
    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ThemeParkRide> postRide(@RequestBody ThemeParkRide body) {
        return ResponseEntity.ok(themeParkRideRepository.save(body));
    }
    
}
