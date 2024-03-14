package com.esprit.demo.service;

import com.esprit.demo.entity.ChefCuisinier;

public interface IChefCuisinierService {
    ChefCuisinier affecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu);
    ChefCuisinier desaffecterChefCuisinierAMenu(Long idChefCuisinier, Long idMenu);
}
