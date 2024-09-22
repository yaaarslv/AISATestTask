package org.aisa.services;

import org.aisa.entities.Drink;
import org.aisa.entities.MachineInventory;
import org.aisa.tools.ConsoleLogger;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.stereotype.Service;

/**
 * Service for ordering drink
 */
@Service
public class OrderService {
    private final DrinkService drinkService;
    private final CoffeeMachineService coffeeMachineService;

    public OrderService(DrinkService drinkService, CoffeeMachineService coffeeMachineService) {
        this.drinkService = drinkService;
        this.coffeeMachineService = coffeeMachineService;
    }

    /**
     * Method of ordering drink
     * @param id identifier of drink to be ordered
     * @param name name of drink to be ordered
     * @return ordered drink
     * @throws CoffeeException if drink with this is or name doesn't exist or not enough ingredients in coffee machine
     */
    public Drink orderDrink(Long id, String name) throws CoffeeException {
        ConsoleLogger.log("Получен новый заказ. id = " + id + ", name = " + name, ConsoleLogger.LogLevel.INFO);

        Drink drink;
        if (id != null) {
            drink = drinkService.getDrinkById(id);
        } else if (name != null) {
            drink = drinkService.getDrinkByName(name);
        } else {
            ConsoleLogger.log("Не переданы id или название напитка", ConsoleLogger.LogLevel.ERROR);
            throw CoffeeException.recipeTypeNotSelectedException();
        }

        MachineInventory inventory = coffeeMachineService.getInventory(1L);
        if (inventory.getWater() < drink.getWaterAmount()) {
            ConsoleLogger.log("Не хватает воды.", ConsoleLogger.LogLevel.ERROR);
            throw CoffeeException.notEnoughWater(drink.getName(), inventory.getWater(), drink.getWaterAmount());
        }
        if (inventory.getCoffee() < drink.getCoffeeAmount()) {
            ConsoleLogger.log("Не хватает кофе.", ConsoleLogger.LogLevel.ERROR);
            throw CoffeeException.notEnoughCoffee(drink.getName(), inventory.getCoffee(), drink.getCoffeeAmount());
        }
        if (inventory.getMilk() < drink.getMilkAmount()) {
            ConsoleLogger.log("Не хватает молока.", ConsoleLogger.LogLevel.ERROR);
            throw CoffeeException.notEnoughMilk(drink.getName(), inventory.getMilk(), drink.getMilkAmount());
        }

        inventory.setWater(inventory.getWater() - drink.getWaterAmount());
        inventory.setCoffee(inventory.getCoffee() - drink.getCoffeeAmount());
        inventory.setMilk(inventory.getMilk() - drink.getMilkAmount());
        coffeeMachineService.updateInventoryAfterOrdering(inventory);

        drinkService.addCoffeeStatistics(drink);

        ConsoleLogger.log(drink.getName() + " готов", ConsoleLogger.LogLevel.INFO);

        return drink;
    }
}
