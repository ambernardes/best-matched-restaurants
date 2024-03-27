package com.bestmatchedrestaurant.bestmatchedreataurants.controller;
import com.bestmatchedrestaurant.bestmatchedreataurants.dto.RestaurantFilterDto;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(summary = "Gets at most 5 best matched restaurants")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Returns an array at most 5 best matched restaurants",
                content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = RestaurantEntity.class)))),
        @ApiResponse(responseCode = "400", description = "Returns the list of errors",
                content = @Content(mediaType = "application/json",
                array = @ArraySchema(schema = @Schema(implementation = String.class)))),
        @ApiResponse(responseCode = "500", description = "Server Internal Error",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/filterRestaurants")
    public ResponseEntity<List<RestaurantEntity>> filterRestaurants(@ParameterObject @Valid RestaurantFilterDto filter) {

        List<RestaurantEntity> restaurants = restaurantService.filterRestaurants(filter);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }
}
