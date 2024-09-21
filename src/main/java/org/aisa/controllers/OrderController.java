package org.aisa.controllers;

import org.aisa.entities.Drink;
import org.aisa.services.OrderService;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping()
    public ResponseEntity<Drink> orderDrink(@RequestParam(value = "id", required = false) Long id, @RequestParam(value = "name", required = false) String name) throws CoffeeException {
        return ResponseEntity.ok(orderService.orderDrink(id, name));
    }
}
