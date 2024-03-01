package com.esprit.demo.controller;

import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;
import com.esprit.demo.service.IClientService;
import com.esprit.demo.service.ICommandeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/commande")
public class CommandeController {
    ICommandeService commandeService;

    // http://localhost:8089/menu/commande/retrieve-all-by-clients
    @GetMapping("/retrieve-all-by-clients/{client-id}")
    public List<Commande> getByClients(@PathVariable("client-id") Long clientId) {
        List<Commande> listCommandes = commandeService.retrieveAllClients(clientId);
        return listCommandes;
    }
}
