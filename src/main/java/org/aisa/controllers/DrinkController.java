package org.aisa.controllers;

import org.aisa.dtos.CreateDrinkDTO;
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

    @PostMapping("/create")
    public ResponseEntity<Drink> createDrink(@RequestBody CreateDrinkDTO drinkDTO) {
        Drink drink = new Drink(drinkDTO.getName(), drinkDTO.getWaterAmount(), drinkDTO.getCoffeeAmount(),drinkDTO.getMilkAmount());
        Drink createdDrink = drinkService.createDrink(drink);
        return ResponseEntity.ok(createdDrink);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrink(@PathVariable Long id) {
        return new ResponseEntity<>(drinkService.getDrink(id), HttpStatus.OK);
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
