/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.social_multiplication.test_service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices_book.social_multiplication.domain.AppUser;
import microservices_book.social_multiplication.domain.Multiplication;
import microservices_book.social_multiplication.domain.MultiplicationAttempt;
import microservices_book.social_multiplication.service.MultiplicationServiceImpl;
import microservices_book.social_multiplication.utils.RandomFactorGenerator;
 
/**
 *
 * @author anwa
 */
public class MultiplicationServiceImplUnitTest {

    @InjectMocks
    private MultiplicationServiceImpl multiplicationService;

    @Mock
    private RandomFactorGenerator randomFactorGenerator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void generatesMultiplication() throws Exception {
        //given
        given(randomFactorGenerator.generateRandomFactor()).willReturn(20, 45);

        //when
        Multiplication makeCall = multiplicationService.generateMultiplication();

        //then
        assertThat(makeCall.getFactorA()).isEqualTo(20);
        assertThat(makeCall.getFactorB()).isEqualTo(45);
        assertThat(makeCall.getResult()).isEqualTo(900);
    }

    @Test
    public void checkWrongAttempt() throws Exception{
        parameterizedAttemptChecker(false);
    }

    @Test
    public void checkRightAttempt() throws Exception{
        parameterizedAttemptChecker(true);
    }

    public void parameterizedAttemptChecker(boolean correct) throws Exception{
        //given
        Multiplication multiplication = new Multiplication(20, 45);
        AppUser user = new AppUser("john_snow");
        MultiplicationAttempt attempt = new MultiplicationAttempt(multiplication, user, correct);

        //when
        boolean result = multiplicationService.checkAttempt(attempt);

        //then
        assertThat(result).isEqualTo(correct);
    }
}