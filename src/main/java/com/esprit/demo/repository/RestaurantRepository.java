package com.esprit.demo.repository;

import com.esprit.demo.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
List<Restaurant> findAllByNbPlacesMaxGreaterThanAndChaineRestauration_DateCreation_YearBefore(Long capacite, Integer year);
}
