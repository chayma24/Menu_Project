package com.esprit.demo.controller;

import com.esprit.demo.entity.ChefCuisinier;
import com.esprit.demo.entity.Menu;
import com.esprit.demo.service.ChefCuisinierService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/chefcuisinier")
public class ChefCuisinierController {
    ChefCuisinierService chefCuisinierService;
    @PutMapping("/affecter-chef-a-menu/{idChefCuisinier}/{idMenu}")
    @ResponseBody
    public ChefCuisinier affecterChefAuMenu(@PathVariable("idChefCuisinier") Long idChefCuisinier,
                                            @PathVariable("idMenu") Long idMenu){
        return chefCuisinierService.affecterChefCuisinierAMenu(idChefCuisinier,idMenu);
    }

    @DeleteMapping("/desaffecter-chef-a-menu/{idChefCuisinier}/{idMenu}")
    @ResponseBody
    public ChefCuisinier desaffecterChefAuMenu(@PathVariable("idChefCuisinier") Long idChefCuisinier,
                                            @PathVariable("idMenu") Long idMenu){
        return chefCuisinierService.desaffecterChefCuisinierAMenu(idChefCuisinier,idMenu);
    }
}
