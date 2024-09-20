package org.aisa.controllers;

import org.aisa.entities.Drink;
import org.aisa.entities.DrinkStatistics;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.aisa.services.DrinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public ResponseEntity<List<Drink>> getAllDrinks() {
        return new ResponseEntity<>(drinkService.getAllDrinks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrink(@PathVariable("id") Long id) throws CoffeeException {
        Drink drink = drinkService.getDrinkById(id);
        return ResponseEntity.ok(drink);
    }

    @GetMapping("/popular")
    public ResponseEntity<List<DrinkStatistics>> getMostPopularDrink() throws CoffeeException {
        return new ResponseEntity<>(drinkService.getMostPopularDrinks(), HttpStatus.OK);
    }
}
