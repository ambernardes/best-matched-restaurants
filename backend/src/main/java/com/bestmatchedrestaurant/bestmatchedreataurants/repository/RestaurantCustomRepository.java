package com.bestmatchedrestaurant.bestmatchedreataurants.repository;

import com.bestmatchedrestaurant.bestmatchedreataurants.dto.RestaurantFilterDto;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;

import java.util.List;

public interface RestaurantCustomRepository {
    List<RestaurantEntity> filterRestaurants(RestaurantFilterDto filter);
}
