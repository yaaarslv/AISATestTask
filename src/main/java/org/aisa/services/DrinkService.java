package org.aisa.services;

import org.aisa.entities.Drink;
import org.aisa.entities.DrinkStatistics;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.aisa.repositories.DrinkRepository;
import org.aisa.repositories.DrinkStatisticsRepository;

import java.util.List;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;
    private final DrinkStatisticsRepository drinkStatisticsRepository;

    @Autowired
    public DrinkService(DrinkRepository drinkRepository, DrinkStatisticsRepository drinkStatisticsRepository) {
        this.drinkRepository = drinkRepository;
        this.drinkStatisticsRepository = drinkStatisticsRepository;
    }

    public Drink createDrink(Drink drink) {
        System.out.println("попало сюда");
        drinkRepository.save(drink);
        DrinkStatistics stats = new DrinkStatistics(drink, 0);
        drinkStatisticsRepository.save(stats);
        return drink;
    }

    public Drink getDrinkById(Long id) throws CoffeeException {
        return drinkRepository.findById(id).orElseThrow(CoffeeException::coffeeTypeNotFoundException);
    }

    public Drink getDrinkByName(String name) throws CoffeeException {
        return drinkRepository.findByName(name).orElseThrow(CoffeeException::coffeeTypeNotFoundException);
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public List<DrinkStatistics> getMostPopularDrinks() throws CoffeeException {
        Integer maxOrdersCount = drinkStatisticsRepository.findMaxOrdersCount();
        if (maxOrdersCount == null) {
            throw CoffeeException.coffeeStatisticIsEmptyException();
        }

        return drinkStatisticsRepository.findAllWithMaxOrdersCount(maxOrdersCount);
    }

    public void increaseCoffeeStatistics(Drink drink) throws CoffeeException {
        if (drink == null) {
            throw CoffeeException.coffeeIsNullException();
        }

        DrinkStatistics stats = drinkStatisticsRepository.findByDrinkId(drink.getId())
                .orElseThrow(CoffeeException::coffeeStatisticNotFoundException);
        stats.setOrdersCount(stats.getOrdersCount() + 1);
        drinkStatisticsRepository.save(stats);
    }
}
