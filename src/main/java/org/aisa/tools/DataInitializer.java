package org.aisa.tools;

import org.aisa.entities.Drink;
import org.aisa.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final DrinkService drinkService;

    @Autowired
    public DataInitializer(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Override
    public void run(String... args) {
        createDrinkIfNotExists("Эспрессо", 30, 10, 0);
        createDrinkIfNotExists("Американо", 50, 10, 0);
        createDrinkIfNotExists("Капучино", 40, 10, 20);
    }

    private void createDrinkIfNotExists(String name, int waterAmount, int coffeeAmount, int milkAmount) {
        if (drinkService.getAllDrinks().stream().noneMatch(drink -> drink.getName().equals(name))) {
            Drink drink = new Drink(name, waterAmount, coffeeAmount, milkAmount);
            drinkService.createDrink(drink);
        }
    }
}
