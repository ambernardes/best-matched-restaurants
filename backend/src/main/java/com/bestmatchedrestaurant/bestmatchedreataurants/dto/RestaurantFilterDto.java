package com.bestmatchedrestaurant.bestmatchedreataurants.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;


public class RestaurantFilterDto {

    public String name;
    @Nullable
    @Min(value = 1, message = "Rating must be equal to or greater than 1")
    @Max(value = 5, message = "Rating must be equal to or less than 5")
    public Integer rating;
    @Nullable
    @Min(value = 1, message = "Distance must be equal to or greater than 1")
    @Max(value = 10, message = "Distance must be equal to or less than 5")
    public Integer distance;
    @Nullable
    @Min(value = 10, message = "Price must be equal to or greater than 10")
    @Max(value = 50, message = "Price must be equal to or less than 50")
    public Integer price;
    @Nullable
    public String cuisine;

    public RestaurantFilterDto(){

    }
    public RestaurantFilterDto(@Nullable String name, @Nullable Integer rating, @Nullable Integer distance, @Nullable Integer price, @Nullable String cuisine) {
        this.name = name;
        this.rating = rating;
        this.distance = distance;
        this.price = price;
        this.cuisine = cuisine;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public void setRating(@Nullable Integer rating) {
        this.rating = rating;
    }

    public void setDistance(@Nullable Integer distance) {
        this.distance = distance;
    }

    public void setPrice(@Nullable Integer price) {
        this.price = price;
    }

    public void setCuisine(@Nullable String cuisine) {
        this.cuisine = cuisine;
    }

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public Integer getRating() {
        return rating;
    }

    @Nullable
    public Integer getDistance() {
        return distance;
    }

    @Nullable
    public Integer getPrice() {
        return price;
    }

    @Nullable
    public String getCuisine() {
        return cuisine;
    }
}
