package com.esprit.demo.service;

import com.esprit.demo.entity.Client;
import com.esprit.demo.entity.Commande;

import java.util.List;

public interface ICommandeService {
  List<Commande> retrieveAllClients(Long id);
 }
