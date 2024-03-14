package com.esprit.demo.service;

import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;

import java.util.List;

public interface IClientService {
    List<Client> retrieveAllClients();

    Client addClient(Client e);

    Client updateClient(Client e);

    Client retrieveClient(Long idClient);

    void removeClient(Long idClient);

    List<Client> addClients (List<Client> Clients);

    List<Commande> listeCommandesParClient(Long idClient);
}
