package com.esprit.demo.controller;

import com.esprit.demo.entity.Menu;
import com.esprit.demo.repository.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/menus")
    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }
}
