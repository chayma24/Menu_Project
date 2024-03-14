package com.esprit.demo.service;

import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;
import com.esprit.demo.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    ClientRepository clientRepository;

    @Override
    public List<Client> retrieveAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client addClient(Client e) {
        return clientRepository.save(e);
    }

    @Override
    public Client updateClient(Client e) {
        return clientRepository.save(e);
    }

    @Override
    public Client retrieveClient(Long idClient) {
        return clientRepository.findById(idClient)
                .orElse(null);
    }

    @Override
    public void removeClient(Long idClient) {
        clientRepository.deleteById(idClient);
    }

    @Override
    public List<Client> addClients(List<Client> Clients) {
        return clientRepository.saveAll(Clients);
    }

    @Override
    public List<Commande> listeCommandesParClient(Long idClient) {
        Optional<Client> client = clientRepository.findById(idClient);

        if (client.isPresent()) {
            return client.get().getCommandes().stream().toList();
        }
        return new ArrayList<>();
    }
}