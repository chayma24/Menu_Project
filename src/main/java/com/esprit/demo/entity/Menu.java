package com.esprit.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Menu")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMenu")
    Long idMenu;

    String libelleMenu;

    @Enumerated(EnumType.STRING)
    typeMenu typeMenu;

    Float prixTotal;

    @ManyToMany(mappedBy = "menus")
    Set<ChefCuisinier> chefCuisiniers;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    Set<Composant> composants;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    Set<Commande> commandes;

    @ManyToOne
    Restaurant restaurant ;

}
