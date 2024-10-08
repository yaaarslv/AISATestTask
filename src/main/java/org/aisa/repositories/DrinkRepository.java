package org.aisa.repositories;

import org.aisa.entities.Drink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Database repository for Drink entity
 */
@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {
    Optional<Drink> findByName(String name);
}