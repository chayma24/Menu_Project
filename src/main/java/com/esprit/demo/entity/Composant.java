package com.esprit.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Composant")
public class Composant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idComposant")
    private Long idComposant;

    private String nomComposant;
    private Float prix;

    @OneToOne
    @JoinColumn(name = "DetailComposant_id")
    private DetailComposant detailComposant;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

}
