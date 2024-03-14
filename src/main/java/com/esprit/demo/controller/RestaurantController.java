package com.esprit.demo.controller;

import com.esprit.demo.entity.Restaurant;
import com.esprit.demo.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;

    @PutMapping("/add-restaurant-to-chaine/{nomRestaurant}/{libelleChaine}")
    public Restaurant addRestaurantToChaine(@PathVariable("nomRestaurant") String nomRestaurant,@PathVariable("libelleChaine") String libelleChaine){
        Restaurant restaurant=restaurantService.affecterRestaurantAChaineRestauration(nomRestaurant,libelleChaine);
        return restaurant;
    }

    @PostMapping("/add-resto-with-menus")
    public Restaurant addRestoWithMenus(@RequestBody Restaurant r){
        return restaurantService.ajoutRestaurantEtMenuAssocies(r);
    }
}
