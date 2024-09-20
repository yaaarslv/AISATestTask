package org.aisa.tools;

import org.aisa.entities.Drink;
import org.aisa.entities.MachineInventory;
import org.aisa.repositories.MachineInventoryRepository;
import org.aisa.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final DrinkService drinkService;
    private final MachineInventoryRepository machineInventoryRepository;

    @Autowired
    public DataInitializer(DrinkService drinkService, MachineInventoryRepository machineInventoryRepository) {
        this.drinkService = drinkService;
        this.machineInventoryRepository = machineInventoryRepository;
    }

    @Override
    public void run(String... args) {
        createDrinkIfNotExists("Эспрессо", 30L, 10L, 0L);
        createDrinkIfNotExists("Американо", 50L, 10L, 0L);
        createDrinkIfNotExists("Капучино", 40L, 10L, 20L);

        if (machineInventoryRepository.findById(1L).isEmpty()) {
            MachineInventory inventory = new MachineInventory(1000L, 500L, 300L);  // Пример начальных запасов
            machineInventoryRepository.save(inventory);
        }
    }

    private void createDrinkIfNotExists(String name, Long waterAmount, Long coffeeAmount, Long milkAmount) {
        if (drinkService.getAllDrinks().stream().noneMatch(drink -> drink.getName().equals(name))) {
            Drink drink = new Drink(name, waterAmount, coffeeAmount, milkAmount);
            drinkService.createDrink(drink);
        }
    }
}
