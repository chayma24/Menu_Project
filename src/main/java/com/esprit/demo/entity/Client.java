package com.esprit.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Client")
public class Client implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idClient")
    private Long idClient;
    private String identifiant;
    @Temporal(TemporalType.DATE)
    private Date datePremiereVisite;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Commande> commandes;

}