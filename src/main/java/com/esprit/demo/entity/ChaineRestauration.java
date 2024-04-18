package com.esprit.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ChaineRestauration")
public class ChaineRestauration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idChaineRestauration")
    private Long idChaineRestauration;
    private String libelle;
    private LocalDate dateCreation;

    @OneToMany(mappedBy = "chaineRestauration", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Restaurant> restaurants;
}
