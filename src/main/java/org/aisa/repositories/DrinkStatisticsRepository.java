package org.aisa.repositories;

import org.aisa.entities.Drink;
import org.aisa.entities.DrinkStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DrinkStatisticsRepository extends JpaRepository<DrinkStatistics, Long> {
    Optional<DrinkStatistics> findByDrinkId(Long drinkId);

    @Query("SELECT MAX(ds.ordersCount) FROM DrinkStatistics ds")
    Integer findMaxOrdersCount();

    @Query("SELECT ds FROM DrinkStatistics ds WHERE ds.ordersCount = :maxOrdersCount")
    List<DrinkStatistics> findAllWithMaxOrdersCount(@Param("maxOrdersCount") Integer maxOrdersCount);
}