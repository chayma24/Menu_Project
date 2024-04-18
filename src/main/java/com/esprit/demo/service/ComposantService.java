package com.esprit.demo.service;

import com.esprit.demo.entity.Composant;
import com.esprit.demo.entity.Menu;
import com.esprit.demo.repository.ComposantRepository;
import com.esprit.demo.repository.MenuRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ComposantService implements IComposantService {
    @Autowired
    ComposantRepository composantRepository;

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu) {
        Menu menu = menuRepository.findById(idMenu)
                .orElseThrow(() -> new IllegalArgumentException("Menu not found with id: " + idMenu));

        composants.forEach(c -> c.setMenu(menu));

        Float prixTotal = composants.stream()
                .map(Composant::getPrix)
                .reduce(0f, Float::sum);

        if (prixTotal > 20f) {
            String errorMessage = "Le prix total du menu ne doit pas dépasser 20 dinars";
            log.error(errorMessage);
            throw new RuntimeException(errorMessage);
        }

        menu.setPrixTotal(prixTotal);

        composantRepository.saveAll(composants);
        menuRepository.save(menu);

        return menu;
    }
}
