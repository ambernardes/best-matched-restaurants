package com.bestmatchedrestaurant.bestmatchedreataurants.restaurant;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bestmatchedrestaurant.bestmatchedreataurants.controller.RestaurantController;
import com.bestmatchedrestaurant.bestmatchedreataurants.dto.RestaurantFilterDto;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.service.RestaurantService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestaurantService restaurantService; // Mocked dependency

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this); // Initialize mocks

        CuisineEntity sampleCuisine = new CuisineEntity(1L, "cuisine-test");

        when(restaurantService.filterRestaurants(any())).thenReturn(new ArrayList<>() {{
            for (Integer i = 0; i < 5; i++)
                add(new RestaurantEntity(i.longValue(), i.toString(), i, i, i, sampleCuisine));
        }});
    }

    @Test
    public void filterRestaurantsNoFilterTest() throws Exception {
        mockMvc.perform(get("/api/restaurant/filterRestaurants"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(5));
    }

    @Test
    public void filterRestaurantsOneInvalidFilterArgumentTest() throws Exception {

        mockMvc.perform(get("/api/restaurant/filterRestaurants?rating=52"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1));
    }
}