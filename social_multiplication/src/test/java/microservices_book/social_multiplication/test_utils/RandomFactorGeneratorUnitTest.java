/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.social_multiplication.test_utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import microservices_book.social_multiplication.utils.RandomFactorGeneratorImpl;
 
/**
 *
 * @author anwa
 */
public class RandomFactorGeneratorUnitTest {

    @InjectMocks
    private RandomFactorGeneratorImpl randomFactorGenerator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generatedRandomFactorIsBetween11And99() throws Exception {
        //given
        List<Integer> randomIntList = IntStream.range(0, 1000)
                                               .map(num -> randomFactorGenerator.generateRandomFactor())
                                               .boxed()
                                               .collect(Collectors.toList());

        //when & then
        assertThat(randomIntList).hasSameElementsAs(IntStream.range(11, 100)
                                                            .boxed().collect(Collectors.toList())
                                                    );
    }
}