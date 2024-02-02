package com.esprit.demo.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Menu")
public class Menu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idMenu")
    private Long idMenu;
    private String libelleMenu;
    @Enumerated(EnumType.STRING)
    private typeMenu typeMenu;
}
