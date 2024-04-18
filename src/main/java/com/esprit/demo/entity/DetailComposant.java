package com.esprit.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DetailComposant")
public class DetailComposant implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idDetailComposant")
    private Long idDetailComposant;
    private Float imc;
    private typeComposant typeComposant;


}
