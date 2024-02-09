package com.esprit.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Restaurant")
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRestaurant")
    private Long idRestaurant;
    private String nom;
    private Long nbPlacesMax;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<Menu> menus;

    @ManyToOne
    @JoinColumn(name = "chaine_id")
    private ChaineRestauration chaineRestauration;
}
