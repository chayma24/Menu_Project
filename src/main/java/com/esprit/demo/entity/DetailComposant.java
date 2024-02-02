package com.esprit.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "DetailComposant")
public class DetailComposant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDetailComposant")
    private Long idDetailComposant;
    private Float imc;
    private typeComposant typeComposant;
}
