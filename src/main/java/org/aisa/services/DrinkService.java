package org.aisa.services;

import org.aisa.entities.Drink;
import org.aisa.entities.DrinkStatistics;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.stereotype.Service;
import org.aisa.repositories.DrinkRepository;
import org.aisa.repositories.DrinkStatisticsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final DrinkStatisticsRepository drinkStatisticsRepository;

    public DrinkService(DrinkRepository drinkRepository, DrinkStatisticsRepository drinkStatisticsRepository) {
        this.drinkRepository = drinkRepository;
        this.drinkStatisticsRepository = drinkStatisticsRepository;
    }

    public Drink createDrink(Drink drink) throws CoffeeException {
        if (drink.getName() == null || drink.getName().isEmpty() || drink.getName().isBlank()) {
            throw CoffeeException.recipeNameIsNullException();
        }

        if (drink.getWaterAmount() == null || drink.getCoffeeAmount() == null || drink.getMilkAmount() == null) {
            throw CoffeeException.recipeIngredientIsNullException();
        }

        if (drink.getWaterAmount() + drink.getCoffeeAmount() + drink.getMilkAmount() == 0) {
            throw CoffeeException.recipeAmountsAreZeroException();
        }

        if (drink.getWaterAmount() < 0 || drink.getCoffeeAmount() < 0 || drink.getMilkAmount() < 0) {
            throw CoffeeException.recipeIngredientIsNegativeException();
        }

        drinkRepository.save(drink);
        return drink;
    }

    public Drink updateDrink(Long id, Drink newDrink) throws CoffeeException {
        Drink existingDrink = drinkRepository.findById(id)
                .orElseThrow(CoffeeException::recipeNotFoundException);

        if (newDrink.getName() != null && !newDrink.getName().isEmpty() && !newDrink.getName().isBlank()) {
            existingDrink.setName(newDrink.getName());
        }

        if (newDrink.getWaterAmount() != null) {
            if (newDrink.getWaterAmount() < 0) {
                throw CoffeeException.recipeIngredientIsNegativeException();
            }

            existingDrink.setWaterAmount(newDrink.getWaterAmount());
        }

        if (newDrink.getCoffeeAmount() != null) {
            if (newDrink.getCoffeeAmount() < 0) {
                throw CoffeeException.recipeIngredientIsNegativeException();
            }

            existingDrink.setCoffeeAmount(newDrink.getCoffeeAmount());
        }

        if (newDrink.getMilkAmount() != null) {
            if (newDrink.getMilkAmount() < 0) {
                throw CoffeeException.recipeIngredientIsNegativeException();
            }

            existingDrink.setMilkAmount(newDrink.getMilkAmount());
        }

        return drinkRepository.save(existingDrink);
    }

    public Drink getDrinkById(Long id) throws CoffeeException {
        return drinkRepository.findById(id).orElseThrow(CoffeeException::recipeNotFoundException);
    }

    public Drink getDrinkByName(String name) throws CoffeeException {
        return drinkRepository.findByName(name).orElseThrow(CoffeeException::recipeNotFoundException);
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public List<Drink> getMostPopularDrinks() throws CoffeeException {
        List<Object[]> results = drinkStatisticsRepository.findDrinkOrdersCount();

        if (results.isEmpty()) {
            throw CoffeeException.coffeeStatisticIsEmptyException();
        }

        Long maxOrdersCount = (Long) results.get(0)[1];

        return results.stream()
                .filter(result -> result[1].equals(maxOrdersCount))
                .map(result -> (Drink) result[0])
                .collect(Collectors.toList());
    }

    public void addCoffeeStatistics(Drink drink) throws CoffeeException {
        if (drink == null) {
            throw CoffeeException.recipeIsNullException();
        }

        DrinkStatistics stats = new DrinkStatistics(drink);
        drinkStatisticsRepository.save(stats);
    }
}
