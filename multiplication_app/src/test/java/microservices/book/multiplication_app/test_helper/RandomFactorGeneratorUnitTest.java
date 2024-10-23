/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices.book.multiplication_app.test_helper;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import microservices.book.multiplication_app.helper.RandomFactorGeneratorImpl;
 
/**
 * Unit Test for the {@link RandomFactorGeneratorUnitTest} Service
 * Since we can't test an interface, we test the implementation instead, through {@link RandomFactorGeneratorImpl}
 * @author Anwar3006
 */
public class RandomFactorGeneratorUnitTest {

    @InjectMocks
    private RandomFactorGeneratorImpl randomFactorGenerator;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void generatedRandomFactor() throws Exception{
        // when
         List<Integer> randomList = IntStream.range(0, 1000)
                                            .map(num -> randomFactorGenerator.generateRandomFactor())
                                            .boxed()
                                            .collect(Collectors.toList());


        // then
        // Assuming the generateRandomFactor method generates a number between 11 and 99
        assertThat(randomList).hasSameElementsAs(IntStream.range(11, 100).boxed().collect(Collectors.toList()));
    
    }
}