package org.aisa.services;

import org.aisa.entities.Drink;
import org.aisa.entities.DrinkStatistics;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.aisa.repositories.DrinkRepository;
import org.aisa.repositories.DrinkStatisticsRepository;

import java.util.Comparator;
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

    public Drink getDrink(Long id) {
        return drinkRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Напиток не найден"));
    }

    public List<Drink> getAllDrinks() {
        return drinkRepository.findAll();
    }

    public Drink orderDrink(Long id) {
        Drink drink = getDrink(id);
        DrinkStatistics stats = drinkStatisticsRepository.findByDrink(drink).orElseThrow(() -> new RuntimeException("Статистика не найдена"));
        stats.setOrdersCount(stats.getOrdersCount() + 1);
        drinkStatisticsRepository.save(stats);
        return drink;
    }

    public Drink getMostPopularDrink() {
        return drinkStatisticsRepository.findAll().stream()
                .max(Comparator.comparing(DrinkStatistics::getOrdersCount))
                .map(DrinkStatistics::getDrink)
                .orElseThrow(() -> new RuntimeException("Статистика пуста"));
    }
}
