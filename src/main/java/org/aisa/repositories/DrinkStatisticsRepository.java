package org.aisa.repositories;

import org.aisa.entities.DrinkStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DrinkStatisticsRepository extends JpaRepository<DrinkStatistics, Long> {
    @Query("SELECT ds.drink, COUNT(ds) AS ordersCount FROM DrinkStatistics ds GROUP BY ds.drink ORDER BY ordersCount DESC")
    List<Object[]> findDrinkOrdersCount();
    void deleteAllByCreatedAtBefore(LocalDateTime createdAt);
}