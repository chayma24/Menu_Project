package com.esprit.demo.service;

import com.esprit.demo.entity.Composant;
import com.esprit.demo.entity.Menu;

import java.util.Set;

public interface IComposantService {
    Menu ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu);
}
