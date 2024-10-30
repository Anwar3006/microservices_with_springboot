/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.social_multiplication.test_service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices_book.social_multiplication.domain.AppUser;
import microservices_book.social_multiplication.domain.Multiplication;
import microservices_book.social_multiplication.domain.MultiplicationAttempt;
import microservices_book.social_multiplication.repository.MultiplicationAttemptRepository;
 
/**
 *
 * @author anwa
 */
public class MultiplicationAttemptServiceImplUnitTest {

    @Mock
    private MultiplicationAttemptRepository attemptRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAttemptsHistoryTest() throws Exception{
        //given
        Multiplication m1 = new Multiplication(10, 20);
        Multiplication m2 = new Multiplication(30, 40);
        AppUser user = new AppUser("silas");
        MultiplicationAttempt attempt1 = new MultiplicationAttempt(m1, user, 200, false);
        MultiplicationAttempt attempt2 = new MultiplicationAttempt(m2, user, 500, false);
        given(attemptRepository.findByUserAliasOrderByIdDesc(user.getAlias())).willReturn(
                                                                            Lists.newArrayList(attempt1, attempt2));

        //when
        List<MultiplicationAttempt> attempts = attemptRepository.findByUserAliasOrderByIdDesc(user.getAlias());
        
        //then
        assertThat(attempts).hasSameElementsAs(Lists.newArrayList(attempt1, attempt2));
        assertThat(attempts).isEqualTo(Lists.list(attempt1, attempt2));
    }
}