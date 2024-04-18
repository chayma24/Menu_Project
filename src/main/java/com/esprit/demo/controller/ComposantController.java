package com.esprit.demo.controller;

import com.esprit.demo.entity.Composant;
import com.esprit.demo.entity.Menu;
import com.esprit.demo.service.IComposantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/composant")
public class ComposantController {
    @Autowired
    IComposantService composantService;

    @Operation(summary = "Add components and update menu price")
    @PostMapping("/add-composants-et-mise-a-jour-prix-menu/{idMenu}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Composants successfully added and menu price updated"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Menu not found")
    })
    public ResponseEntity<?> addComposantsEtMiseAjourPrixMenu(
            @Parameter(description = "Set of components", required = true)
            @RequestBody Set<Composant> composants,
            @Parameter(description = "ID of the menu", required = true)
            @PathVariable("idMenu") Long idMenu) {
        try {
            Menu menu = composantService.ajoutComposantsEtMiseAjourPrixMenu(composants, idMenu);
            return ResponseEntity.status(HttpStatus.CREATED).body(menu);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
