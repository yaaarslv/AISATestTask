package org.aisa.tools;

import org.aisa.entities.Drink;
import org.aisa.entities.MachineInventory;
import org.aisa.repositories.MachineInventoryRepository;
import org.aisa.services.CoffeeMachineService;
import org.aisa.services.DrinkService;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final DrinkService drinkService;
    private final CoffeeMachineService coffeeMachineService;
    private final MachineInventoryRepository machineInventoryRepository;

    public DataInitializer(DrinkService drinkService, CoffeeMachineService coffeeMachineService, MachineInventoryRepository machineInventoryRepository) {
        this.drinkService = drinkService;
        this.coffeeMachineService = coffeeMachineService;
        this.machineInventoryRepository = machineInventoryRepository;
    }

    @Override
    public void run(String... args) throws CoffeeException {
        createDrinkIfNotExists("Эспрессо", 30L, 10L, 0L);
        createDrinkIfNotExists("Американо", 50L, 10L, 0L);
        createDrinkIfNotExists("Капучино", 40L, 10L, 20L);

        if (machineInventoryRepository.findById(1L).isEmpty()) {
            MachineInventory inventory = new MachineInventory(1000L, 500L, 300L);
            coffeeMachineService.createInventory(inventory);
        }
    }

    private void createDrinkIfNotExists(String name, Long waterAmount, Long coffeeAmount, Long milkAmount) throws CoffeeException {
        if (drinkService.getAllDrinks().stream().noneMatch(drink -> drink.getName().equals(name))) {
            Drink drink = new Drink(name, waterAmount, coffeeAmount, milkAmount);
            drinkService.createDrink(drink);
        }
    }
}
