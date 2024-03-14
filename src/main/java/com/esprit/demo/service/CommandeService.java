package com.esprit.demo.service;

import com.esprit.demo.dto.CommandeDatesDTO;
import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;
import com.esprit.demo.repository.ClientRepository;
import com.esprit.demo.repository.CommandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CommandeService implements ICommandeService {
    @Autowired
    CommandeRepository commandeRepository;

    public List<Commande> retrieveAllClients(Long id) {
        return commandeRepository.findAllByClientIdClient(id);
    }

    @Override
    public List<Commande> retrieveAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande addCommande(Commande e) {
        return commandeRepository.save(e);
    }

    @Override
    public Commande updateCommande(Commande e) {
        return commandeRepository.save(e);
    }

    @Override
    public Commande retrieveCommande(Long idCommande) {
        return commandeRepository.findById(idCommande)
                .orElse(null);
    }

    @Override
    public void removeCommande(Long idCommande) {
        commandeRepository.deleteById(idCommande);
    }

    @Override
    public List<Commande> addCommandes(List<Commande> Commandes) {
        return commandeRepository.saveAll(Commandes);
    }

    @Override
    public List<Commande> findAllByIdClientAndDateCommandeBetween(Long idClient, CommandeDatesDTO dto) {
        return commandeRepository.findAllByClientIdClientAndDateCommandeBetween(idClient, dto.getDate1(), dto.getDate2());
    }

    @Override
    public List<Commande> findAllByDateCommandeBetweenOrderByNoteAsc(CommandeDatesDTO dto) {
        return commandeRepository.findAllByDateCommandeBetweenOrderByNoteAsc(dto.getDate1(), dto.getDate2());
    }
}
