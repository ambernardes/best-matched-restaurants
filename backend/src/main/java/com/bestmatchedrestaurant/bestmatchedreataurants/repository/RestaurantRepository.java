package com.bestmatchedrestaurant.bestmatchedreataurants.repository;

import com.bestmatchedrestaurant.bestmatchedreataurants.dto.RestaurantFilterDto;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface RestaurantRepository extends CrudRepository<RestaurantEntity, Long>, RestaurantCustomRepository{
    List<RestaurantEntity> filterRestaurants(RestaurantFilterDto filter);
}
