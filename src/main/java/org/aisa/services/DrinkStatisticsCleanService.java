package org.aisa.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.aisa.repositories.DrinkStatisticsRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DrinkStatisticsCleanService {
    private final DrinkStatisticsRepository drinkStatisticsRepository;

    public DrinkStatisticsCleanService(DrinkStatisticsRepository drinkStatisticsRepository) {
        this.drinkStatisticsRepository = drinkStatisticsRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void cleanupOldStatistics() {
        LocalDateTime cutoffDate = LocalDateTime.now().minusYears(5);
        System.out.println("Статистика старше 5 лет удалена");
        drinkStatisticsRepository.deleteAllByCreatedAtBefore(cutoffDate);
    }
}
