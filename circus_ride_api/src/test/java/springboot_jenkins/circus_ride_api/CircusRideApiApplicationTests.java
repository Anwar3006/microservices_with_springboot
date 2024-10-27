package springboot_jenkins.circus_ride_api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import springboot_jenkins.circus_ride_api.domain.ThemeParkRide;


@SpringBootTest
@AutoConfigureMockMvc
class CircusRideApiApplicationTests {

	@Autowired
	MockMvc mvc;

	JacksonTester<ThemeParkRide> json; //

	@BeforeEach
	public void setUp() {
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void getsAllRides()throws Exception {
		mvc.perform(get("/ride").accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
	}

	@Test
	public void getRideById()throws Exception {
		mvc.perform(get("/ride/1").accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
	}

	@Test
	public void returns404WhenRideNotFound()throws Exception {
		mvc.perform(get("/ride/5").accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isNotFound())
			.andReturn();
	}

	@Test
	public void postRide()throws Exception {
		mvc.perform(get("/ride")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(json.write(new ThemeParkRide("Ride_name", "Ride_description", 2, 5)).getJson()))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn();
	}
}
