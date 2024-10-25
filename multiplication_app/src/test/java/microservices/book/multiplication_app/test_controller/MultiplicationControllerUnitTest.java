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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservices.book.multiplication_app.controller.MultiplicationController;
import microservices.book.multiplication_app.domain.Multiplication;
import microservices.book.multiplication_app.service.MultiplicationService;

/**
 * 
 * @author Anwar3006
 */
@ExtendWith(MockitoExtension.class) // Needed for enabling Mockito support in the test class. This extension initializes the @Mock and @InjectMocks annotations in JUnit 5 tests.
@WebMvcTest(MultiplicationController.class) // Needed for configuring Spring MVC infrastructure, allowing testing of only the web layer (controller) without loading the full application context.
public class MultiplicationControllerUnitTest {

   @MockBean
   private MultiplicationService multiplicationService; // The service to mock. Spring injects this mock into the controller, allowing isolation of the controller layer for testing.

   @Autowired
   private MockMvc mvc; // Needed for performing HTTP requests in the test, emulating requests to the controller and verifying the response.
   
   JacksonTester<Multiplication> jsonMultiplication; // A helper for serializing/deserializing JSON to/from the Multiplication object.
   
   @BeforeEach
   public void setUp(){
       // Initialize JacksonTester fields with an ObjectMapper to handle JSON data.
       JacksonTester.initFields(this, new ObjectMapper());
   }

   @Test
   public void getMultiplication() throws Exception {
        //given
       Multiplication multiplication = new Multiplication(70, 20);
       given(multiplicationService.generateMultiplication()).willReturn(multiplication);

       //when
       MockHttpServletResponse response = mvc.perform(get("/multiplications/random").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

       //then
       assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
       assertThat(response.getContentAsString()).isEqualTo(jsonMultiplication.write(multiplication).getJson());   
   }

}