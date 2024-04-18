package com.esprit.demo.controller;

import com.esprit.demo.entity.Composant;
import com.esprit.demo.entity.Menu;
import com.esprit.demo.entity.typeComposant;
import com.esprit.demo.entity.typeMenu;
import com.esprit.demo.repository.MenuRepository;
import com.esprit.demo.service.IMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/menu")
@Tag(name = "Menu Management Module",
        description = "Operations pertaining to menu management")
public class MenuController {
    private final IMenuService menuService;
    @Autowired
    MenuRepository menuRepository;

    @GetMapping("/menus")
    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }
    @Operation(summary = "Find menu labels by type ordered by price",
            description = "Find menu labels by type ordered by price")
    @GetMapping("/find-menu-labels/{typeMenu}")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved menu labels"),
                @ApiResponse(responseCode = "400", description = "Bad request"),
                @ApiResponse(responseCode = "404", description = "Menu labels not found")
        })
        public List<String> findMenuLabelsByTypeOrderedByPrice(
                @Parameter(description = "Type of menu", required = true)
                @PathVariable("typeMenu") typeMenu typeMenu) {
            List<String> menuLabels = menuService.getMenuLabelByMenuTypeOrderedByPrice(typeMenu);
            return menuLabels;
        }

    @Operation(summary = "Find menus by type composant",
            description = "Find menus by type composant")
    @GetMapping("/find-menus-by-type-composant/{typeComposant}")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved menus by type of component"),
                @ApiResponse(responseCode = "400", description = "Bad request"),
                @ApiResponse(responseCode = "404", description = "Menus not found")
        })
        public List<Menu> findMenusByTypeComposant(
                @Parameter(description = "Type of composant", required = true)
                @PathVariable("typeComposant") typeComposant typeComposant) {
            List<Menu> menus = menuService.getMenuByTypeComposant(typeComposant);
            return menus;
        }

    @Operation(summary = "Find menus by type and price",
            description = "Find menus by type and price greater than a given value")
    @GetMapping("/find-menus/{typeMenu}/{prixTotal}")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved menus by type and price"),
                @ApiResponse(responseCode = "400", description = "Bad request"),
                @ApiResponse(responseCode = "404", description = "Menus not found")
        })
        public List<Menu> findMenusByTypeAndPriceGreaterThan(
                @Parameter(description = "Type of menu", required = true)
                @PathVariable("typeMenu") typeMenu typeMenu,
                @Parameter(description = "Total price", required = true)
                @PathVariable("prixTotal") Float prixTotal) {
            List<Menu> menus = menuService.retrieveMenusByTypeAndPrice(typeMenu, prixTotal);
            return menus;
        }

    @Operation(summary = "Find all menus")
    @GetMapping("/find-all-menus")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all menus"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Menus not found")
    })
    public List<Menu> findAllMenus() {
        List<Menu> menus = menuService.retrieveAllMenus();
        return menus;
    }

    @Operation(summary = "Find a menu by ID")
    @GetMapping("/find-menu/{idMenu}")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved the menu"),
                @ApiResponse(responseCode = "400", description = "Bad request"),
                @ApiResponse(responseCode = "404", description = "Menu not found")
        })
        public Menu findMenu(
                @Parameter(description = "ID of the menu", required = true)
                @PathVariable("idMenu") Long idMenu) {
            Menu menu = menuService.retrieveMenu(idMenu);
            return menu;
        }

    @Operation(summary = "Add a menu")
    @PostMapping("/add-menu")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Menu successfully added"),
                @ApiResponse(responseCode = "400", description = "Bad request")
        })
        public Menu addMenu(
                @Parameter(description = "Menu object to add", required = true)
                @RequestBody Menu m) {
            Menu menu = menuService.addMenu(m);
            return menu;
        }

    @Operation(summary = "Add multiple menus")
    @PostMapping("/add-menus")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "201", description = "Menus successfully added"),
                @ApiResponse(responseCode = "400", description = "Bad request")
        })
        public List<Menu> addMenus(
                @Parameter(description = "List of menu objects to add", required = true)
                @RequestBody List<Menu> menus) {
            List<Menu> listMenus = menuService.addMenus(menus);
            return listMenus;
        }

    @Operation(summary = "Update a menu")
    @PutMapping("/update-menu")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Menu successfully updated"),
                @ApiResponse(responseCode = "400", description = "Bad request"),
                @ApiResponse(responseCode = "404", description = "Menu not found")
        })
        public Menu updateMenu(
                @Parameter(description = "Menu object to update", required = true)
                @RequestBody Menu m) {
            Menu menu = menuService.updateMenu(m);
            return menu;
        }

    @Operation(summary = "Remove a menu by ID")
    @DeleteMapping("/remove-menu/{idMenu}")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Menu successfully removed"),
                @ApiResponse(responseCode = "400", description = "Bad request"),
                @ApiResponse(responseCode = "404", description = "Menu not found")
        })
        public void removeMenu(
                @Parameter(description = "ID of the menu to remove", required = true)
                @PathVariable("idMenu") Long idMenu) {
            menuService.removeMenu(idMenu);
        }

    @Operation(summary = "Assign a chef cuisinier to a menu")
    @GetMapping("/affecter-chef-cuisinier-a-menu/{idMenu}/{idChefCuisinier}/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chef cuisinier successfully assigned to menu"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Menu or chef not found")
    })
    public void affecterChefCuisinierAMenu(
            @Parameter(description = "ID of the chef cuisinier", required = true)
            @PathVariable("idChefCuisinier") Long idChefCuisinier,
            @Parameter(description = "ID of the menu", required = true)
            @PathVariable("idMenu") Long idMenu) {
        menuService.affecterChefCuisinierAMenu(idChefCuisinier, idMenu);
    }

    @Operation(summary = "Remove assignment of a chef cuisinier from a menu")
    @GetMapping("/desaffecter-chef-cuisinier-du-menu/{idMenu}/{idChef}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Chef cuisinier successfully removed from menu"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Menu or chef not found")
    })
    public void desaffecterChefCuisinierDuMenu(
            @Parameter(description = "ID of the menu", required = true)
            @PathVariable("idMenu") Long idMenu,
            @Parameter(description = "ID of the chef cuisinier", required = true)
            @PathVariable("idChef") Long idChef) {
        menuService.desaffecterChefCuisinierDuMenu(idMenu, idChef);
    }

    @Operation(summary = "Find menu labels by type ordered by price",
            description = "Find menu labels by type ordered by price")
    @GetMapping("/find-menu-labels-by-type-menu-ordered-by-price/{typeMenu}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved menu labels"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Menu labels not found")
    })
    public List<String> findMenuLabelsByTypeMenuOrderedByPrice(
            @Parameter(description = "Type of menu", required = true)
            @PathVariable("typeMenu") typeMenu typeMenu) {
        List<String> menuLabels = menuService.getMenuLabelByMenuTypeOrderedByPrice(typeMenu);
        return menuLabels;
    }

    @Operation(summary = "Find menus by type and price",
            description = "Find menus by type and price greater than a given value")
    @GetMapping("/find-menus-by-type-menu-and-price/{typeMenu}/{prixTotal}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved menus by type and price"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Menus not found")
    })
    public List<Menu> findMenusByTypeMenuAndPriceGreaterThan(
            @Parameter(description = "Type of menu", required = true)
            @PathVariable("typeMenu") typeMenu typeMenu,
            @Parameter(description = "Total price", required = true)
            @PathVariable("prixTotal") Float prixTotal) {
        List<Menu> menus = menuService.retrieveMenusByTypeAndPrice(typeMenu, prixTotal);
        return menus;
    }

   @Operation(summary = "Add components and update menu price")
    @GetMapping("/add-composants-et-mise-a-jour-prix-menu")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Components successfully added and menu price updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Menu not found")
    })
    public Menu addComposantsEtMiseAjourPrixMenu(
            @Parameter(description = "Set of components", required = true)
            @RequestBody Set<Composant> composants,
            @Parameter(description = "ID of the menu", required = true)
            @PathVariable("idMenu") Long idMenu) {
        Menu menu = menuService.ajoutComposantsEtMiseAjourPrixMenu(composants, idMenu);
        return menu;
    }
}
