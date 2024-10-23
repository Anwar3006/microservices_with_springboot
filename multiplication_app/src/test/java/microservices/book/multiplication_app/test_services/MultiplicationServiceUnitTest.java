/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices.book.multiplication_app.test_services;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices.book.multiplication_app.domain.AppUser;
import microservices.book.multiplication_app.domain.Multiplication;
import microservices.book.multiplication_app.domain.MultiplicationAttemptResult;
import microservices.book.multiplication_app.helper.RandomFactorGenerator;
import microservices.book.multiplication_app.service.MultiplicationServiceImpl;
 
/**
 * Unit Tests for the {@link MultiplicationServiceUnitTest}
 * @author Anwar3006
 */
public class MultiplicationServiceUnitTest {

    @InjectMocks
    private MultiplicationServiceImpl multiplicationService;

    @Mock
    private RandomFactorGenerator randomFactorGenerator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generatedRandomNumbersWithinExpectedRange() throws Exception{
        //given
        given(randomFactorGenerator.generateRandomFactor()).willReturn(20, 45);
       
        //when
        Multiplication multiplication = multiplicationService.generateMultiplication();

        //then
        assertThat(multiplication.getFactorA()).isEqualTo(20);
        assertThat(multiplication.getFactorB()).isEqualTo(45);
        assertThat(multiplication.getResult()).isEqualTo(900);
    }

    @Test
    public void checkCorrectAttempt(){
        //given
        Multiplication multiplication = new Multiplication(20, 45);
        AppUser user = new AppUser("john_snow");
        MultiplicationAttemptResult attemptResult = new MultiplicationAttemptResult(multiplication, user, 900);

        //when
        boolean isCorrect = multiplicationService.checkAttempt(attemptResult);

        //then
        assertThat(isCorrect).isTrue();
    }

    @Test
    public void checkWrongAttempt(){
        //given
        Multiplication multiplication = new Multiplication(20, 45);
        AppUser user = new AppUser("john_snow");
        MultiplicationAttemptResult attemptResult = new MultiplicationAttemptResult(multiplication, user, 910);

        //when
        boolean isCorrect = multiplicationService.checkAttempt(attemptResult);

        //then
        assertThat(isCorrect).isFalse();
    }
}