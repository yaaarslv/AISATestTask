package org.aisa.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "drink_statistics")
public class DrinkStatistics {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @OneToOne
    private Drink drink;

    @Setter
    @Column(name = "orders_count")
    private int ordersCount;

    public DrinkStatistics() {
    }

    public DrinkStatistics(Drink drink, int ordersCount) {
        this.drink = drink;
        this.ordersCount = ordersCount;
    }
}

