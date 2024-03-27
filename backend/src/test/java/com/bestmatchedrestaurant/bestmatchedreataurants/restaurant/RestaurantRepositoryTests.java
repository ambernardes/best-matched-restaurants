package com.bestmatchedrestaurant.bestmatchedreataurants.restaurant;

import com.bestmatchedrestaurant.bestmatchedreataurants.dto.RestaurantFilterDto;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.repository.CuisineRepository;
import com.bestmatchedrestaurant.bestmatchedreataurants.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RestaurantRepositoryTests {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @BeforeEach
    public void setUp() {
        Faker faker = new Faker();

        var brazilianCuisine = new CuisineEntity(1L, "Brazilian");
        var mexicanCuisine = new CuisineEntity(2L, "Mexican");
        var americanCuisine = new CuisineEntity(3L, "American");

        cuisineRepository.saveAll(Arrays.asList(
            brazilianCuisine, mexicanCuisine, americanCuisine
        ));

        var restaurantToSave = new ArrayList<RestaurantEntity>();
        var index = 1;
        for (int i = 1; i <= 3; i++) {
            restaurantToSave.add(new RestaurantEntity(
                (long) index,
                "Brazilian",
                i,
                i,
                i,
                brazilianCuisine
            ));
            index++;
        }

        for (int i = 1; i <= 3; i++) {
            restaurantToSave.add(new RestaurantEntity(
                (long) index,
                "Mexican",
                i,
                i,
                i,
                mexicanCuisine
            ));
            index++;
        }

        for (int i = 1; i <= 3; i++) {
            restaurantToSave.add(new RestaurantEntity(
                (long) index,
            "American",
                i,
                i,
                i,
                americanCuisine
            ));
            index++;
        }
        // Save the list of other objects
        restaurantRepository.saveAll(restaurantToSave);
    }

    @Test
    public void filterRestaurantsWithEmptyFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto();

        List<RestaurantEntity> brazilianRestaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(brazilianRestaurants).hasSize(5);
    }

    @Test
    public void filterRestaurantsWithNameFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
            "Brazilian",
            null,
            null,
            null,
            null
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(3);
    }

    @Test
    public void filterRestaurantsWithNameCaseInsensitiveFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                "BrAzIlIaN",
                null,
                null,
                null,
                null
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(3);
    }

    @Test
    public void filterRestaurantsWithDistanceFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                null,
                null,
                1,
                null,
                null
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(3);
    }

    @Test
    public void filterRestaurantsWithRatingFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                null,
                3,
                null,
                null,
                null
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(3);
    }

    @Test
    public void filterRestaurantsWithPriceFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                null,
                null,
                null,
                1,
                null
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(3);
    }

    @Test
    public void filterRestaurantsWithCuisineFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                null,
                null,
                null,
                null,
                "Brazilian"
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(3);
    }

    @Test
    public void filterRestaurantsWithCuisineCaseInsensitiveFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                null,
                null,
                null,
                null,
                "BrAzIl"
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(3);
    }

    @Test
    public void filterRestaurantsNotMatchingFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                null,
                null,
                null,
                null,
                "Scotish"
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(0);
    }

    @Test
    public void filterRestaurantsExactMatchingFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                "Brazilian",
                3,
                1,
                1,
                "Brazilian"
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(0);
    }

    @Test
    public void filterRestaurantsLimitValuesFilterTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
                null,
                1,
                3,
                3,
                null
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(5);
    }

    @Test
    public void filterRestaurantsOrderByDistanceTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
        null,
        null,
        null,
        null,
        null
        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(5);
        assertThat(restaurants.get(0).getDistance()).isEqualTo(1);
        assertThat(restaurants.get(1).getDistance()).isEqualTo(1);
        assertThat(restaurants.get(2).getDistance()).isEqualTo(1);
        assertThat(restaurants.get(3).getDistance()).isEqualTo(2);
        assertThat(restaurants.get(4).getDistance()).isEqualTo(2);
    }

    @Test
    public void filterRestaurantsOrderByPriceTest() {

        RestaurantFilterDto filter = new RestaurantFilterDto(
        null,
        null,
        null,
        null,
        null

        );

        List<RestaurantEntity> restaurants = restaurantRepository.filterRestaurants(filter);
        assertThat(restaurants).hasSize(5);
        assertThat(restaurants.get(0).getPrice()).isEqualTo(1);
        assertThat(restaurants.get(1).getPrice()).isEqualTo(1);
        assertThat(restaurants.get(2).getPrice()).isEqualTo(1);
        assertThat(restaurants.get(3).getPrice()).isEqualTo(2);
        assertThat(restaurants.get(4).getPrice()).isEqualTo(2);
    }

}