package org.aisa.controllers;

import org.aisa.entities.MachineInventory;
import org.aisa.services.CoffeeMachineService;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/machine")
public class CoffeeMachineController {
    private final CoffeeMachineService coffeeMachineService;

    @Autowired
    public CoffeeMachineController(CoffeeMachineService coffeeMachineService) {
        this.coffeeMachineService = coffeeMachineService;
    }

    @GetMapping("/inventory")
    public ResponseEntity<MachineInventory> getInventory() throws CoffeeException {
        return new ResponseEntity<>(coffeeMachineService.getInventory(), HttpStatus.OK);
    }
}
