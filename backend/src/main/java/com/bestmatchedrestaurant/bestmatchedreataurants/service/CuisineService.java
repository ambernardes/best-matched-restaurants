package com.bestmatchedrestaurant.bestmatchedreataurants.service;

import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.repository.CuisineRepository;
import com.bestmatchedrestaurant.bestmatchedreataurants.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CuisineService {
    private final CuisineRepository cuisineRepository;

    @Autowired
    public CuisineService(CuisineRepository cuisineRepository) {
        this.cuisineRepository = cuisineRepository;
    }

    public List<CuisineEntity> getAll() {
        return cuisineRepository.findAll();
    }

    public Optional<CuisineEntity> getById(Long id) {
        return cuisineRepository.findById(id);
    }

    public CuisineEntity save(CuisineEntity cuisine) {
        return cuisineRepository.save(cuisine);
    }

    public void delete(Long id) {
        cuisineRepository.deleteById(id);
    }
}
