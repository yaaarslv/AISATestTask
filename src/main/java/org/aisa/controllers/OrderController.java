package org.aisa.controllers;

import org.aisa.entities.Drink;
import org.aisa.services.OrderService;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<Drink> orderDrink(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "name", required = false) String name) throws CoffeeException {
        Drink drink = orderService.orderDrink(id, name);
        return ResponseEntity.ok(drink);
    }
}
