/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.multiplication_service.test_controller;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import microservices_book.multiplication_service.controller.MultiplicationController;
import microservices_book.multiplication_service.domain.Multiplication;
import microservices_book.multiplication_service.service.MultiplicationServiceImpl;
 
/**
 *
 * @author anwa
 */

@WebMvcTest(MultiplicationController.class)
public class MultiplicationControllerUnitTest {

    @MockBean
    private MultiplicationServiceImpl multiplicationService;

    @Autowired
    private MockMvc mvc;

    JacksonTester<Multiplication> jsonMultiplication;

    @BeforeEach
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getMultiplicationTest() throws Exception{
        //given
        Multiplication multiplication = new Multiplication(20, 45);
        // AppUser user = new AppUser("john_snow");
        // MultiplicationAttempt attempt = new MultiplicationAttempt(multiplication, user, false);
        given(multiplicationService.generateMultiplication()).willReturn(multiplication);

        //when
        MockHttpServletResponse response = mvc.perform(get("/multiplications/random")
                                                        .accept(MediaType.APPLICATION_JSON))
                                               .andReturn()
                                               .getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonMultiplication.write(multiplication).getJson());
    }

}