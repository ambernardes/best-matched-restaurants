package com.bestmatchedrestaurant.bestmatchedreataurants.controller;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.CuisineEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.entity.RestaurantEntity;
import com.bestmatchedrestaurant.bestmatchedreataurants.service.CuisineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cuisine")
public class CuisineController {

    private final CuisineService cuisineService;

    @Autowired
    public CuisineController(CuisineService restaurantService) {
        this.cuisineService = restaurantService;
    }

    @Operation(summary = "Gets all the cuisines")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns an array containing all the cuisines",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = CuisineEntity.class)))),
            @ApiResponse(responseCode = "400", description = "Returns the list of errors",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = String.class))))
    })
    @GetMapping("/getAll")
    public ResponseEntity<List<CuisineEntity>> getAll() {
        List<CuisineEntity> cuisines = cuisineService.getAll();
        return new ResponseEntity<>(cuisines, HttpStatus.OK);
    }
}
