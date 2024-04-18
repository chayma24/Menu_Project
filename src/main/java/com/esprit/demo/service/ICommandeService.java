package com.esprit.demo.service;

import com.esprit.demo.dto.CommandeDatesDTO;
import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;

import java.util.Date;
import java.util.List;

public interface ICommandeService {
    List<Commande> retrieveAllClients(Long id);

    List<Commande> retrieveAllCommandes();

    Commande addCommande(Commande e);

    Commande updateCommande(Commande e);

    Commande retrieveCommande(Long idCommande);

    void removeCommande(Long idCommande);

    List<Commande> addCommandes(List<Commande> Commandes);

    List<Commande> findAllByIdClientAndDateCommandeBetween(Long idClient, CommandeDatesDTO dto);

    List<Commande> findAllByDateCommandeBetweenOrderByNoteAsc(CommandeDatesDTO dto);

    void ajouterCommandeEtAffecterAClientEtMenu(Commande commande,
                                                String identifiant,
                                                String libelleMenu);
}
