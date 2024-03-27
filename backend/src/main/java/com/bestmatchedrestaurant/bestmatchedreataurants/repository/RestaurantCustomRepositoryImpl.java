package com.bestmatchedrestaurant.bestmatchedreataurants.repository;

import com.bestmatchedrestaurant.bestmatchedreataurants.dto.RestaurantFilterDto;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RestaurantCustomRepositoryImpl implements RestaurantCustomRepository {

    @Autowired
    private EntityManager entityManager;
    public List<RestaurantEntity> filterRestaurants(RestaurantFilterDto filter){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RestaurantEntity> criteriaQuery = criteriaBuilder.createQuery(RestaurantEntity.class);
        Root<RestaurantEntity> root = criteriaQuery.from(RestaurantEntity.class);
        Join<RestaurantEntity, CuisineEntity> cuisineJoin = root.join("cuisine");

        var whereClause = new ArrayList<Predicate>();

        var orderBy = new ArrayList<Order>();

        orderBy.add(criteriaBuilder.asc(root.get("distance")));
        orderBy.add(criteriaBuilder.desc(root.get("customerRating")));
        orderBy.add(criteriaBuilder.asc(root.get("price")));

        // Add predicates based on conditions
        if (filter.name != null && !filter.name.isEmpty()) {
            var stringLower = criteriaBuilder.lower(root.get("name"));
            whereClause.add(criteriaBuilder.like(stringLower, "%" + filter.name.toLowerCase() + "%"));
        }

        if (filter.rating != null)
            whereClause.add(criteriaBuilder.greaterThanOrEqualTo(root.get("customerRating"), filter.rating));

        if (filter.distance != null)
            whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.get("distance"), filter.distance));

        if (filter.price != null)
            whereClause.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.price));

        if(filter.cuisine != null && !filter.cuisine.isEmpty()) {
            var stringLower = criteriaBuilder.lower(cuisineJoin.get("name"));
            whereClause.add(criteriaBuilder.like(stringLower, "%" + filter.cuisine.toLowerCase() + "%"));
        }
        criteriaQuery
            .where(criteriaBuilder.and(whereClause.toArray(new Predicate[0])))
            .orderBy(orderBy);

        return entityManager
            .createQuery(criteriaQuery)
            .setMaxResults(5)
            .getResultList();
    };
}
