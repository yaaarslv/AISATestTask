package org.aisa.controllers;

import org.aisa.dtos.CreateOrUpdateDrinkDTO;
import org.aisa.entities.Drink;
import org.aisa.entities.DrinkStatistics;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.aisa.services.DrinkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        return ResponseEntity.ok(drinkService.getAllDrinks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrink(@PathVariable("id") Long id) throws CoffeeException {
        return ResponseEntity.ok(drinkService.getDrinkById(id));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<DrinkStatistics>> getMostPopularDrink() throws CoffeeException {
        return ResponseEntity.ok(drinkService.getMostPopularDrinks());
    }

    @PostMapping("/create")
    public ResponseEntity<Drink> createDrink(@RequestBody CreateOrUpdateDrinkDTO drinkDTO) throws CoffeeException {
        Drink drink = new Drink(drinkDTO.getName(), drinkDTO.getWaterAmount(), drinkDTO.getCoffeeAmount(), drinkDTO.getMilkAmount());
        return ResponseEntity.ok(drinkService.createDrink(drink));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Drink> updateDrink(@PathVariable("id") Long id, @RequestBody CreateOrUpdateDrinkDTO drinkDTO) throws CoffeeException {
        Drink drink = new Drink(drinkDTO.getName(), drinkDTO.getWaterAmount(), drinkDTO.getCoffeeAmount(), drinkDTO.getMilkAmount());
        return ResponseEntity.ok(drinkService.updateDrink(id, drink));
    }
}
