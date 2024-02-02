package com.esprit.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Commande")
public class Commande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCommande")
    private Long idCommande;
    private LocalDate dateCommande;
    private int pourcentageRemise;
    private Float totalRemise;
    private Float totalCommande;
    private Long note;
}
