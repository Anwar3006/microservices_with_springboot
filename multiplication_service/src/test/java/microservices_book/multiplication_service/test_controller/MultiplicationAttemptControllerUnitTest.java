/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.multiplication_service.test_controller;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.util.Lists;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservices_book.multiplication_service.controller.MultiplicationAttemptController;
import microservices_book.multiplication_service.domain.AppUser;
import microservices_book.multiplication_service.domain.Multiplication;
import microservices_book.multiplication_service.domain.MultiplicationAttempt;
import microservices_book.multiplication_service.service.MultiplicationAttemptService;
import microservices_book.multiplication_service.service.MultiplicationService;
 
/**
 *
 * @author anwa
 */
@WebMvcTest(MultiplicationAttemptController.class)
public class MultiplicationAttemptControllerUnitTest {

    @MockBean
    private MultiplicationService multiplicationService;

    @MockBean
    private MultiplicationAttemptService attemptService;

    @Autowired
    private MockMvc mvc;

    JacksonTester<MultiplicationAttempt> jsonRequest;
    JacksonTester<MultiplicationAttempt> jsonResponse;
    JacksonTester<List<MultiplicationAttempt>> jsonListResponse;

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
        assertThat(response.getContentAsString()).isEqualTo(jsonResponse.write(new MultiplicationAttempt(multiplication, user, 900, false)).getJson());
    }

    @Test
    public void getHistoryTest() throws Exception{
        //given
        Multiplication multiplication = new Multiplication(20, 45);
        AppUser user = new AppUser("john_snow");
        MultiplicationAttempt attempt1 = new MultiplicationAttempt(multiplication, user, 900, true);
        MultiplicationAttempt attempt2 = new MultiplicationAttempt(multiplication, user, 910, true);
        List<MultiplicationAttempt> history = Lists.newArrayList(attempt1, attempt2);
        given(attemptService.getAttemptsHistory("bob")).willReturn(history);

        //when
        MockHttpServletResponse response = mvc.perform(get("/results?alias=bob")
                                                        .accept(MediaType.APPLICATION_JSON))
                                               .andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonListResponse.write(history).getJson());
    }
}