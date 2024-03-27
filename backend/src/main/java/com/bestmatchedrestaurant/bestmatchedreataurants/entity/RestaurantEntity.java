package com.bestmatchedrestaurant.bestmatchedreataurants.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class RestaurantEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "customer_rating")
    private Integer customerRating;
    @Column(name = "distance")
    private Integer distance;
    @Column(name = "price")
    private Integer price;
    @ManyToOne
    private CuisineEntity cuisine;

    public RestaurantEntity(){}

    public RestaurantEntity(Long id, String name, Integer customerRating, Integer distance, Integer price, CuisineEntity cuisine){
        this.id = id;
        this.name = name;
        this.customerRating = customerRating;
        this.distance = distance;
        this.price = price;
        this.cuisine = cuisine;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(Integer customerRating) {
        this.customerRating = customerRating;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public CuisineEntity getCuisine() {
        return cuisine;
    }

    public void setCuisine(CuisineEntity cuisine) {
        this.cuisine = cuisine;
    }
}
