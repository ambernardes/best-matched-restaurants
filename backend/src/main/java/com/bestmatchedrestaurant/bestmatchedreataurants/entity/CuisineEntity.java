package com.bestmatchedrestaurant.bestmatchedreataurants.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class CuisineEntity implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    public CuisineEntity(){}
    public CuisineEntity(Long id, String name ){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
