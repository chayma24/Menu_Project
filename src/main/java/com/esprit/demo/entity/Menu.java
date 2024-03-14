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
@Table(name = "Menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMenu")
    private Long idMenu;

    private String libelleMenu;

    @Enumerated(EnumType.STRING)
    private typeMenu typeMenu;

    private Float prixTotal;

    @ManyToMany(mappedBy = "menus")
    private Set<ChefCuisinier> chefCuisiniers;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private Set<Composant> composants;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private Set<Commande> commandes;

    @ManyToOne
    Restaurant restaurant ;

}
