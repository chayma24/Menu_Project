package com.esprit.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ChefCuisinier")
public class ChefCuisinier implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idChefCuisinier")
    private Long idChefCuisinier;
    private String nom;
    private String prenom;
    private typeChef typeChef;

    @ManyToMany
    @JoinTable(
            name = "menu_chef",
            joinColumns = @JoinColumn(name = "chef_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private Set<Menu> menus = new HashSet<>();
}
