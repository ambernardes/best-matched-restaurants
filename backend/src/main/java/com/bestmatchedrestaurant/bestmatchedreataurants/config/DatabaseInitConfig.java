package com.bestmatchedrestaurant.bestmatchedreataurants.config;

import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.service.CuisineService;
import com.bestmatchedrestaurant.bestmatchedreataurants.service.RestaurantService;
import com.bestmatchedrestaurant.bestmatchedreataurants.util.Util;
import com.sun.tools.javac.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class DatabaseInitConfig implements CommandLineRunner {

    private final RestaurantService restaurantService;
    private final CuisineService cuisineService;
    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    public DatabaseInitConfig(RestaurantService restaurantService, CuisineService cuisineService) {
        this.restaurantService = restaurantService;
        this.cuisineService = cuisineService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadCuisines();
        loadRestaurants();
    }

    public void loadCuisines() throws IOException {
        var resource = Util.readResource("static/cuisines.csv");
        var cuisineCSV = Util.readCsv(resource);
        cuisineCSV.forEach(c ->
        {
            var cuisine = new CuisineEntity(
                Long.parseLong(c.get("id")),
                c.get("name") != null ? c.get("name") : ""
            );
            cuisineService.save(cuisine);
        });
    }

    public void loadRestaurants() throws IOException {
        var resource = Util.readResource("static/restaurants.csv");
        var restaurantCSV = Util.readCsv(resource);
        AtomicLong index = new AtomicLong(1l);
        restaurantCSV.forEach(r ->
        {
            var restaurant = new RestaurantEntity(
                index.get(),
                r.get("name") != null ? r.get("name") : "",
                Integer.parseInt(r.get("customer_rating")),
                Integer.parseInt(r.get("distance")),
                Integer.parseInt(r.get("price")),
                cuisineService.getById(Long.parseLong(r.get("cuisine_id"))).get()
            );
            restaurantService.save(restaurant);

            index.getAndIncrement();
        });
    }
}
