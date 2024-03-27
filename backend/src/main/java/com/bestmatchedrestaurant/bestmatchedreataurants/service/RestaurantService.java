package com.bestmatchedrestaurant.bestmatchedreataurants.service;

import com.bestmatchedrestaurant.bestmatchedreataurants.dto.RestaurantFilterDto;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository userRepository) {
        this.restaurantRepository = userRepository;
    }

    public List<RestaurantEntity> filterRestaurants(RestaurantFilterDto filter) {
        return restaurantRepository.filterRestaurants(filter);
    }

    public RestaurantEntity save(RestaurantEntity entity) {
        return restaurantRepository.save(entity);
    }
}
