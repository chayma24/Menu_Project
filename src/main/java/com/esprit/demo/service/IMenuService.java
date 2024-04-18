package com.esprit.demo.service;

import com.esprit.demo.entity.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface IMenuService {
    List<String> getMenuLabelByMenuTypeOrderedByPrice(@Param("typeMenu") typeMenu typeMenu);
    List<Menu> getMenuByTypeComposant(@Param("typeComposant") typeComposant typeComposant);
    List<Menu> retrieveMenusByTypeAndPrice(typeMenu typeMenu, Float PrixTotal);
    Menu retrieveMenu(Long idMenu);
    List<Menu> retrieveAllMenus();
    Menu addMenu(Menu e);
    List<Menu> addMenus(List<Menu> Menus);
    Menu updateMenu(Menu e);
    void removeMenu(Long idMenu);

    ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu);

    ChefCuisinier desaffecterChefCuisinierDuMenu(Long idMenu, Long idChef);

    Menu ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu);
}
