package com.bestmatchedrestaurant.bestmatchedreataurants.cuisine;

import com.bestmatchedrestaurant.bestmatchedreataurants.dto.RestaurantFilterDto;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.repository.CuisineRepository;
import com.bestmatchedrestaurant.bestmatchedreataurants.repository.RestaurantRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CuisineRepositoryTests {

    @Autowired
    private CuisineRepository cuisineRepository;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker();

        var cuisinesToSave = new ArrayList<CuisineEntity>();

        for (long i = 1; i <= 10; i++)
            cuisinesToSave.add(new CuisineEntity(i, faker.funnyName().name()));

        cuisineRepository.saveAll(cuisinesToSave);
    }

    @Test
    public void findAllTest() {

        List<CuisineEntity> brazilianRestaurants = cuisineRepository.findAll();
        assertThat(brazilianRestaurants).hasSize(10);
    }
}