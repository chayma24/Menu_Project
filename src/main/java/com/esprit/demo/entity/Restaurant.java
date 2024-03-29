package com.esprit.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Restaurant")
public class Restaurant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idRestaurant")
    private Long idRestaurant;
    private String nom;
    private Long nbPlacesMax;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    // @JsonIgnore
    private Set<Menu> menus;

    @ManyToOne
    @JoinColumn(name = "chaine_id")
    @JsonIgnore
    private ChaineRestauration chaineRestauration;
}
