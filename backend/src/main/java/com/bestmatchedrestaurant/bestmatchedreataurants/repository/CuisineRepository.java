package com.bestmatchedrestaurant.bestmatchedreataurants.repository;

import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineRepository extends JpaRepository<CuisineEntity, Long> {
}
