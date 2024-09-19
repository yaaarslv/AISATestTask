package org.aisa.controllers;

import org.aisa.entities.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.aisa.services.DrinkService;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrink(@PathVariable("id") Long id) {
        Drink drink = drinkService.getDrink(id);
        return ResponseEntity.ok(drink);
    }

    @GetMapping
    public ResponseEntity<List<Drink>> getAllDrinks() {
        return new ResponseEntity<>(drinkService.getAllDrinks(), HttpStatus.OK);
    }

    @PostMapping("/order/{id}")
    public ResponseEntity<Drink> orderDrink(@PathVariable Long id) {
        return new ResponseEntity<>(drinkService.orderDrink(id), HttpStatus.OK);
    }

    @GetMapping("/popular")
    public ResponseEntity<Drink> getMostPopularDrink() {
        return new ResponseEntity<>(drinkService.getMostPopularDrink(), HttpStatus.OK);
    }
}
