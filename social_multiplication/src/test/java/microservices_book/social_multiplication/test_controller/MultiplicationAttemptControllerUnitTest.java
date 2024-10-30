/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.social_multiplication.test_controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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

import microservices_book.social_multiplication.controller.MultiplicationAttemptController;
import microservices_book.social_multiplication.domain.AppUser;
import microservices_book.social_multiplication.domain.Multiplication;
import microservices_book.social_multiplication.domain.MultiplicationAttempt;
import microservices_book.social_multiplication.service.MultiplicationService;
 
/**
 *
 * @author anwa
 */
@WebMvcTest(MultiplicationAttemptController.class)
public class MultiplicationAttemptControllerUnitTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @Autowired
    private MockMvc mvc;

    JacksonTester<MultiplicationAttempt> jsonRequest;
    JacksonTester<MultiplicationAttemptController.AttemptResponseBody> jsonResponse;

    @BeforeEach
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void postAttemptTestForRightAttempt() throws Exception{
        //given
        Multiplication multiplication = new Multiplication(20, 45);
        AppUser user = new AppUser("john_snow");
        MultiplicationAttempt attempt = new MultiplicationAttempt(multiplication, user, 900, false);
        given(multiplicationService.checkAttempt(any(MultiplicationAttempt.class))).willReturn(false);

        //when
        MockHttpServletResponse response = mvc.perform(post("/results").accept(MediaType.APPLICATION_JSON)
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(jsonRequest.write(attempt).getJson()))
                                                .andReturn()
                                                .getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new MultiplicationAttemptController.AttemptResponseBody(false)).getJson());
    }

}