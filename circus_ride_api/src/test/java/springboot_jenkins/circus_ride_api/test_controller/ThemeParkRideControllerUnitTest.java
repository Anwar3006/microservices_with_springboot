/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package springboot_jenkins.circus_ride_api.test_controller;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.MockitoAnnotations;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.fasterxml.jackson.databind.ObjectMapper;

import springboot_jenkins.circus_ride_api.controller.ThemeParkRideController;
import springboot_jenkins.circus_ride_api.domain.ThemeParkRide;
import springboot_jenkins.circus_ride_api.repository.ThemeParkRideRepository;

/**
 *
 * @author anwa
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ThemeParkRideController.class)
public class ThemeParkRideControllerUnitTest {

    @MockBean
    private ThemeParkRideRepository themeParkRideRepository;

    @Autowired
    private MockMvc mvc;

    public JacksonTester<List<ThemeParkRide>> jsonListThemeParkRide;
    public JacksonTester<ThemeParkRide> jsonThemeParkRide;

    @BeforeEach
    public void setUp(){
        JacksonTester.initFields(this, new ObjectMapper());
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getRidesTest() throws Exception{
        //given
        ThemeParkRide ride = new ThemeParkRide("Ride_name", "Ride_description", 2, 5);
        given(themeParkRideRepository.findAll()).willReturn(Lists.newArrayList(ride));
        
        //when
        MockHttpServletResponse response = mvc.perform(get("/ride").accept(MediaType.APPLICATION_JSON))
                                                                                .andReturn()
                                                                                .getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonListThemeParkRide.write(Lists.newArrayList(ride)).getJson());
    }

    @Test
    public void getRideTest() throws Exception {
        //given
        ThemeParkRide ride = new ThemeParkRide("Ride_name", "Ride_description", 2, 5);
        given(themeParkRideRepository.findById(1L)).willReturn(Optional.of(ride));
        
        //when
        MockHttpServletResponse response = mvc.perform(get("/ride/1").accept(MediaType.APPLICATION_JSON))
                                                                                .andReturn()
                                                                                .getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonThemeParkRide.write(ride).getJson());

    }

    @Test
    public void postRide() throws Exception {
        //given
        ThemeParkRide ride = new ThemeParkRide("Ride_name", "Ride_description", 2, 5);
        given(themeParkRideRepository.save(any(ThemeParkRide.class))).willReturn(ride);

        // When
        MockHttpServletResponse response = mvc.perform(post("/ride")
                                    .contentType(MediaType.APPLICATION_JSON) // Specify content type
                                    .accept(MediaType.APPLICATION_JSON)
                                    .content(jsonThemeParkRide.write(ride).getJson())) // Add serialized JSON content
                                .andReturn()
                                .getResponse();
        
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(jsonThemeParkRide.write(ride).getJson());
    }
}