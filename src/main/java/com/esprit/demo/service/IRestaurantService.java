package com.esprit.demo.service;

import com.esprit.demo.entity.Restaurant;
import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IRestaurantService {
     Restaurant affecterRestaurantAChaineRestauration(String nomRestaurant, String libelleChaine );
    Restaurant ajoutRestaurantEtMenuAssocies(Restaurant restaurant);
}
