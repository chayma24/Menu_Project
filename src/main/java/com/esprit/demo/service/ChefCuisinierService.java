package com.esprit.demo.service;

import com.esprit.demo.entity.ChefCuisinier;
import com.esprit.demo.entity.Menu;
import com.esprit.demo.repository.ChefCuisinierRepository;
import com.esprit.demo.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChefCuisinierService implements IChefCuisinierService{
    ChefCuisinierRepository chefCuisinierRepository;
    MenuRepository menuRepository;
    @Override
    public ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu) {
        ChefCuisinier chef= chefCuisinierRepository.findById(idChefCuisinier).get();
        Menu menu = menuRepository.findById(idMenu).get();
        Set<Menu> menusMaj = new HashSet<>();
        if (chef.getMenus() != null) {
            menusMaj = chef.getMenus();
        }
        menusMaj.add(menu);
        chef.setMenus(menusMaj);
        chefCuisinierRepository.save(chef);

        return chef;
    }

    @Override
    public ChefCuisinier desaffecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu) {
        ChefCuisinier chef= chefCuisinierRepository.findById(idChefCuisinier).get();
        Menu menu = menuRepository.findById(idMenu).get();
        Set<Menu> menusMaj = new HashSet<>();
        if (chef.getMenus() != null) {
            menusMaj = chef.getMenus();
        }
        menusMaj.remove(menu);
        chef.setMenus(menusMaj);
        chefCuisinierRepository.save(chef);

        return chef;
    }
}
