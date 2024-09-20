package org.aisa.services;

import org.aisa.entities.Drink;
import org.aisa.entities.MachineInventory;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final DrinkService drinkService;
    private final CoffeeMachineService coffeeMachineService;

    @Autowired
    public OrderService(DrinkService drinkService, CoffeeMachineService coffeeMachineService) {
        this.drinkService = drinkService;
        this.coffeeMachineService = coffeeMachineService;
    }

    public Drink orderDrink(Long id, String name) throws CoffeeException {
        Drink drink;
        if (id != null) {
            drink = drinkService.getDrinkById(id);
        } else if (name != null) {
            drink = drinkService.getDrinkByName(name);
        } else {
            throw CoffeeException.recipeTypeNotSelectedException();
        }

        MachineInventory inventory = coffeeMachineService.getInventory();
        if (inventory.getWater() < drink.getWaterAmount()) {
            throw CoffeeException.notEnoughWater(drink.getName(), inventory.getWater(), drink.getWaterAmount());
        }
        if (inventory.getCoffee() < drink.getCoffeeAmount()) {
            throw CoffeeException.notEnoughCoffee(drink.getName(), inventory.getCoffee(), drink.getCoffeeAmount());
        }
        if (inventory.getMilk() < drink.getMilkAmount()) {
            throw CoffeeException.notEnoughMilk(drink.getName(), inventory.getMilk(), drink.getMilkAmount());
        }

        inventory.setWater(inventory.getWater() - drink.getWaterAmount());
        inventory.setCoffee(inventory.getCoffee() - drink.getCoffeeAmount());
        inventory.setMilk(inventory.getMilk() - drink.getMilkAmount());
        coffeeMachineService.updateInventoryAfterOrdering(inventory);

        drinkService.increaseCoffeeStatistics(drink);

        return drink;
    }
}
