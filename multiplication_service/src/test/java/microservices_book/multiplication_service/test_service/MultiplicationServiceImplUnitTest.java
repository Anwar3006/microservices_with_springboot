/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.multiplication_service.test_service;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import microservices_book.multiplication_service.domain.AppUser;
import microservices_book.multiplication_service.domain.Multiplication;
import microservices_book.multiplication_service.domain.MultiplicationAttempt;
import microservices_book.multiplication_service.domain.MultiplicationSolvedEvent;
import microservices_book.multiplication_service.event.EventDispatcher;
import microservices_book.multiplication_service.repository.AppUserRepository;
import microservices_book.multiplication_service.repository.MultiplicationAttemptRepository;
import microservices_book.multiplication_service.service.MultiplicationServiceImpl;
import microservices_book.multiplication_service.utils.RandomFactorGenerator;
 
/**
 *
 * @author anwa
 */
public class MultiplicationServiceImplUnitTest {

    @InjectMocks
    private MultiplicationServiceImpl multiplicationService;

    @Mock
    private AppUserRepository userRepository;

    @Mock
    private MultiplicationAttemptRepository attemptRepository;

    @Mock
    private RandomFactorGenerator randomFactorGenerator;

    @Mock
    private EventDispatcher eventDispatcher;
    

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
        //given
        Multiplication multiplication = new Multiplication(20, 45);
        AppUser user = new AppUser("john_snow");
        MultiplicationAttempt attempt = new MultiplicationAttempt(multiplication, user, 901, false);
        given(userRepository.findUserByAlias("john_snow")).willReturn(Optional.empty());

        MultiplicationSolvedEvent solvedEvent = new MultiplicationSolvedEvent(attempt.getId(), attempt.getUser().getId(), attempt.isCorrect());

        //when
        boolean result = multiplicationService.checkAttempt(attempt);

        //then
        assertThat(result).isEqualTo(false);
        verify(attemptRepository).save(any(MultiplicationAttempt.class));
        verify(eventDispatcher).send(solvedEvent);
    }

    @Test
    public void checkRightAttempt() throws Exception{
        //given
        Multiplication multiplication = new Multiplication(20, 45);
        AppUser user = new AppUser("john_snow");
        MultiplicationAttempt attempt = new MultiplicationAttempt(multiplication, user, 900, false);
        given(userRepository.findUserByAlias("john_snow")).willReturn(Optional.empty());

        MultiplicationSolvedEvent solvedEvent = new MultiplicationSolvedEvent(attempt.getId(), attempt.getUser().getId(), attempt.isCorrect());

        //when
        boolean result = multiplicationService.checkAttempt(attempt);

        //then
        assertThat(result).isEqualTo(true);
        verify(attemptRepository).save(any(MultiplicationAttempt.class));
        verify(eventDispatcher).send(solvedEvent);
    }
}