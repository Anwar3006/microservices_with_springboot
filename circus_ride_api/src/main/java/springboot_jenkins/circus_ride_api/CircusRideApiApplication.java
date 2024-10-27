package springboot_jenkins.circus_ride_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springboot_jenkins.circus_ride_api.domain.ThemeParkRide;
import springboot_jenkins.circus_ride_api.repository.ThemeParkRideRepository;

@SpringBootApplication
public class CircusRideApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircusRideApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleData(ThemeParkRideRepository themeParkRideRepository){
		return (args) -> {
			themeParkRideRepository.save(new ThemeParkRide("RollerCoaster", 
										"Train ride that speeds you along", 5, 3));
			themeParkRideRepository.save(new ThemeParkRide("Log flume", 
										"Boat ride with plenty of splashes", 3, 2));
			themeParkRideRepository.save(new ThemeParkRide("Teacups", 
										"Spinning ride in a giant tea-cup", 2, 4));
		};
	}
}
