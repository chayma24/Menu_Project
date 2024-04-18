package com.esprit.demo.service;

import com.esprit.demo.entity.*;
import com.esprit.demo.repository.ChefCuisinierRepository;
import com.esprit.demo.repository.MenuRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class MenuService implements IMenuService{
    private final MenuRepository menuRepository;
    private final ChefCuisinierRepository chefCuisinierRepository;
    @Override
    public List<String> getMenuLabelByMenuTypeOrderedByPrice(typeMenu typeMenu) {
        return menuRepository.selectNomMenuOrderedByPrixTotal(typeMenu);
    }

    @Override
    public List<Menu> getMenuByTypeComposant(typeComposant typeComposant) {
        return menuRepository.selectListMenuByTypeComposant(typeComposant);
    }

    @Override
    public List<Menu> retrieveMenusByTypeAndPrice(typeMenu typeMenu, Float PrixTotal) {
        return menuRepository.findAllByTypeMenuAndComposants_PrixGreaterThan(typeMenu,PrixTotal);
    }

    @Override
    public Menu retrieveMenu(Long idMenu) {
        return menuRepository.findById(idMenu).orElse(null);
    }

    @Override
    public List<Menu> retrieveAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu addMenu(Menu e) {
        return menuRepository.save(e);
    }

    @Override
    public List<Menu> addMenus(List<Menu> Menus) {
        return menuRepository.saveAll(Menus);
    }

    @Override
    public Menu updateMenu(Menu e) {
        return menuRepository.save(e);
    }

    @Override
    public void removeMenu(Long idMenu) {
        menuRepository.deleteById(idMenu);
    }

    @Override
    public ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu) {
        Menu menu = menuRepository.findById(idMenu).orElse(null);
        ChefCuisinier chefCuisinier = chefCuisinierRepository.findById(idChefCuisinier).orElse(null);
        if (menu == null || chefCuisinier == null) {
            return null;
        }
        menu.getChefCuisiniers().add(chefCuisinier);
        menuRepository.save(menu);
        return chefCuisinier;
    }

    @Override
    public ChefCuisinier desaffecterChefCuisinierDuMenu(Long idMenu, Long idChef) {
        Menu menu = menuRepository.findById(idMenu).orElse(null);
        if (menu == null
                || menu.getChefCuisiniers() == null
                || menu.getChefCuisiniers().isEmpty()) {
            return null;
        }

        ChefCuisinier chefCuisinier = chefCuisinierRepository.findById(idChef).orElse(null);
        if (chefCuisinier == null) {
            return null;
        }

        menu.getChefCuisiniers().remove(chefCuisinier);
        menuRepository.save(menu);

        return chefCuisinier;
    }

    @Override
    public Menu ajoutComposantsEtMiseAjourPrixMenu(Set<Composant> composants, Long idMenu) {
        Menu menu = menuRepository.findById(idMenu).orElse(null);
        if (menu == null) {
            return null;
        }

        menu.getComposants().addAll(composants);

        menu.setPrixTotal(menu.getComposants().stream()
                .map(Composant::getPrix)
                .reduce(0f, Float::sum));

        if (menu.getPrixTotal() > 20) {
            log.error("Le prix total du menu ne doit pas dépasser 20 dinars");
            return null;
        }

        return menuRepository.save(menu);
    }
}
