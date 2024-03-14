package com.esprit.demo.service;

import com.esprit.demo.entity.ChaineRestauration;
import com.esprit.demo.entity.Restaurant;
import com.esprit.demo.repository.ChaineRestaurationRepository;
import com.esprit.demo.repository.MenuRepository;
import com.esprit.demo.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantService implements IRestaurantService{
    @Autowired
    RestaurantRepository restaurantRepository;
    ChaineRestaurationRepository chaineRestaurationRepository;
    MenuRepository menuRepository;
    @Override
    public Restaurant affecterRestaurantAChaineRestauration(String nomRestaurant, String libelleChaine) {
        Restaurant restaurant=restaurantRepository.findByNom(nomRestaurant);
        ChaineRestauration chaine=chaineRestaurationRepository.findByLibelle(libelleChaine);
        restaurant.setChaineRestauration(chaine);
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    @Override
    public Restaurant ajoutRestaurantEtMenuAssocies(Restaurant r) {
        r.getMenus().forEach(menu ->{
            menu.setPrixTotal(0.0f);
        });

        return restaurantRepository.save(r);
    }
}
