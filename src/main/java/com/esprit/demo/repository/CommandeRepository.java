package com.esprit.demo.repository;

import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findAllByClientIdClient(Long idClient);
    List<Commande> findAllByClientIdClientAndDateCommandeBetween(Long idClient, LocalDate date1, LocalDate date2);
    List<Commande> findAllByDateCommandeBetweenOrderByNoteAsc(LocalDate date1, LocalDate date2);
}
