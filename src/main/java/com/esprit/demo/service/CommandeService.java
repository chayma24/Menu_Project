package com.esprit.demo.service;

import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;
import com.esprit.demo.repository.ClientRepository;
import com.esprit.demo.repository.CommandeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommandeService implements ICommandeService {

    CommandeRepository commandeRepository;

    public List<Commande> retrieveAllClients(Long id) {
        return commandeRepository.findAllByClientIdClient(id);
    }

}
