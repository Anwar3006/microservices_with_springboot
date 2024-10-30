package microservices_book.social_multiplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservices_book.social_multiplication.domain.AppUser;
import microservices_book.social_multiplication.domain.Multiplication;
import microservices_book.social_multiplication.domain.MultiplicationAttempt;

@SpringBootTest(classes = SocialMultiplicationApplication.class)
@AutoConfigureMockMvc
class SocialMultiplicationApplicationTests {

	@Autowired
	MockMvc mvc;

	JacksonTester<MultiplicationAttempt> attemptJson;

	@BeforeEach
	public void setUp(){
		JacksonTester.initFields(this, new ObjectMapper());
	}

	@Test
	public void getMultiplicationIntTest() throws Exception{
		mvc.perform(get("/multiplications/random").accept(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.factorA").isNotEmpty())
			.andExpect(MockMvcResultMatchers.jsonPath("$.factorB").isNotEmpty());
	}

	@Test
	public void postMultiplicationIntTest() throws Exception{
		Multiplication multiplication = new Multiplication(50, 60);
		AppUser user = new AppUser("john_snow");
		MultiplicationAttempt attempt = new MultiplicationAttempt(multiplication, user, 900, false);

		mvc.perform(post("/results")
						.accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(attemptJson.write(attempt).getJson()))
		   .andExpect(MockMvcResultMatchers.status().isOk());
	}
}
