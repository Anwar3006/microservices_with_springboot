/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices.book.multiplication_app.test_controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.BDDMockito.given;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservices.book.multiplication_app.controller.MultiplicationAttemptResultController;
import microservices.book.multiplication_app.domain.AppUser;
import microservices.book.multiplication_app.domain.Multiplication;
import microservices.book.multiplication_app.domain.MultiplicationAttemptResult;
import microservices.book.multiplication_app.service.MultiplicationService;
 
/**
 *
 * @author anwa
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(MultiplicationAttemptResultController.class)
public class MultiplicationAttemptResultControllerUnitTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    // JacksonTester<Multiplication> requestJson;
    JacksonTester<MultiplicationAttemptResult> requestJson;

    @BeforeEach
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
    }


       @Test
   public void getAttemptResultForWrongAnswer() throws Exception{
    parameterizedTest(false);
   }

   @Test
   public void getAttemptResultForCorrectAnswer() throws Exception{
    parameterizedTest(true);
   }

   private void parameterizedTest(boolean isCorrect) throws Exception{
    //given
    Multiplication multiplication = new Multiplication(40, 30);
    AppUser user = new AppUser("john_snow");
    MultiplicationAttemptResult attempt = new MultiplicationAttemptResult(multiplication, user, 1200);
    given(multiplicationService.checkAttempt(attempt)).willReturn(isCorrect);

    //when
    MockHttpServletResponse response = mvc.perform(post("/results")
                                                    .accept(MediaType.APPLICATION_JSON)
                                                    .contentType(MediaType.APPLICATION_JSON)
                                                    .content(requestJson.write(attempt).getJson()))
                                                  .andReturn().getResponse();
    System.out.println("isCorrect=>>>>>"+isCorrect);
    //then
    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(String.valueOf(isCorrect));
   }
}