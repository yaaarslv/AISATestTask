package org.aisa.repositories;

import org.aisa.entities.Drink;
import org.aisa.entities.DrinkStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrinkStatisticsRepository extends JpaRepository<DrinkStatistics, Long> {
    Optional<DrinkStatistics> findByDrink(Drink drink);
}