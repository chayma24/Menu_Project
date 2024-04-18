package com.esprit.demo.controller;

import com.esprit.demo.dto.CommandeDatesDTO;
import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;
import com.esprit.demo.service.IClientService;
import com.esprit.demo.service.ICommandeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Commande addCommande(@RequestBody Commande c) {
        Commande commande = commandeService.addCommande(c);
        return commande;
    }

    // http://localhost:8089/menu/commande/update-commande
    @PutMapping("/update-commande")
    public Commande updateCommande(@RequestBody Commande c) {
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

    @Operation(summary = "Ajouter une commande et l'affecter à un client et un menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Commande ajoutée et affectée à un client et un menu"),
            @ApiResponse(responseCode = "400", description = "Commande non ajoutée ou non affectée à un client ou un menu")
    })
    @PostMapping("/ajouter-commande-et-affecter-a-client-et-menu/{identifiant}/{libelleMenu}")
    public ResponseEntity<?> ajouterCommandeEtAffecterAClientEtMenu(
            @Parameter(description = "Commande à ajouter et affecter à un client et un menu", required = true)
            @RequestBody Commande commande,
            @Parameter(description = "Identifiant du client à affecter à la commande")
            @PathVariable("identifiant") String identifiant,
            @Parameter(description = "Libellé du menu à affecter à la commande")
            @PathVariable("libelleMenu") String libelleMenu) {
        try {
            commandeService.ajouterCommandeEtAffecterAClientEtMenu(commande, identifiant, libelleMenu);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
