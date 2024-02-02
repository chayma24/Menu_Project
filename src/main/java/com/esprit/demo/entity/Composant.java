package com.esprit.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Composant")
public class Composant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idComposant")
    private Long idComposant;
    private String nomComposant;
    private Float prix;

}
