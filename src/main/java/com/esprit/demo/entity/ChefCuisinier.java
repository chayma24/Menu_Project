package com.esprit.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;

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
}
