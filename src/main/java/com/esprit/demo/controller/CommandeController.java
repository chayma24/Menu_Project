package com.esprit.demo.controller;

import com.esprit.demo.dto.CommandeDatesDTO;
import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;
import com.esprit.demo.service.IClientService;
import com.esprit.demo.service.ICommandeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/commande")
public class CommandeController {
    @Autowired
    ICommandeService commandeService;

    // http://localhost:8089/menu/commande/retrieve-all-by-clients
    @GetMapping("/retrieve-all-by-clients/{client-id}")
    public List<Commande> getByClients(@PathVariable("client-id") Long clientId) {
        List<Commande> listCommandes = commandeService.retrieveAllClients(clientId);
        return listCommandes;
    }

    // http://localhost:8089/menu/commande/retrieve-all-commandes
    @GetMapping("/retrieve-all-commandes")
    public List<Commande> retrieveAllCommandes() {
        List<Commande> listCommandes = commandeService.retrieveAllCommandes();
        return listCommandes;
    }

    // http://localhost:8089/menu/commande/add-commande
    @PostMapping("/add-commande")
    public Commande addCommande( @RequestBody Commande c) {
        Commande commande = commandeService.addCommande(c);
        return commande;
    }

    // http://localhost:8089/menu/commande/update-commande
    @PutMapping("/update-commande")
    public Commande updateCommande( @RequestBody Commande c) {
        Commande commande = commandeService.updateCommande(c);
        return commande;
    }

    // http://localhost:8089/menu/commande/retrieve-commande/
    @GetMapping("/retrieve-commande/{commande-id}")
    public Commande retrieveCommande(@PathVariable("commande-id") Long idCommande) {
        Commande commande = commandeService.retrieveCommande(idCommande);
        return commande;
    }

    // http://localhost:8089/menu/commande/remove-commande/
    @DeleteMapping("/remove-commande/{commande-id}")
    public void removeCommande(@PathVariable("commande-id") Long idCommande) {
        commandeService.removeCommande(idCommande);
    }

    // http://localhost:8089/menu/commande/add-commandes
    @PostMapping("/add-commandes")
    public List<Commande> addCommandes(@RequestBody List<Commande> commandes) {
        List<Commande> listCommandes = commandeService.addCommandes(commandes);
        return listCommandes;
    }

    // http://localhost:8089/menu/commande/retrieve-all-by-clients
    @GetMapping("/retrieve-all-by-client-and-dates/{client-id}")
    public List<Commande> findAllByIdClientAndDateCommandeBetween(@PathVariable("client-id") Long clientId, @RequestBody CommandeDatesDTO dto) {
        List<Commande> listCommandes = commandeService.findAllByIdClientAndDateCommandeBetween(clientId, dto);
        return listCommandes;
    }

    @GetMapping("/retrieve-all-by-dates")
    public List<Commande> findAllByDateCommandeBetweenOrderByNoteAsc(@RequestBody CommandeDatesDTO dto) {
        List<Commande> listCommandes = commandeService.findAllByDateCommandeBetweenOrderByNoteAsc(dto);
        return listCommandes;
    }
}
