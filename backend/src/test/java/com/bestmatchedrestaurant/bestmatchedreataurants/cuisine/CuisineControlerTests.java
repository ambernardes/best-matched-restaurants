package com.bestmatchedrestaurant.bestmatchedreataurants.cuisine;

import com.bestmatchedrestaurant.bestmatchedreataurants.controller.CuisineController;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.service.CuisineService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CuisineController.class)
public class CuisineControlerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CuisineService cuisineService; // Mocked dependency

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        Faker faker = new Faker();

        when(cuisineService.getAll()).thenReturn(new ArrayList<>() {{
            for (Integer i = 0; i < 5; i++)
                add(new CuisineEntity(i.longValue(), i.toString()));
        }});
    }

    @Test
    public void filterRestaurantsNoFilterTest() throws Exception {
        mockMvc.perform(get("/api/cuisine/getAll"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.length()").value(5));
    }
}
