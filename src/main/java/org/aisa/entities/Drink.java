package org.aisa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "drink")
public class Drink {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "water_amount")
    private int waterAmount;

    @Setter
    @Column(name = "coffee_amount")
    private int coffeeAmount;

    @Setter
    @Column(name = "milk_amount")
    private int milkAmount;

    public Drink() {
    }

    public Drink(String name, int waterAmount, int coffeeAmount, int milkAmount) {
        this.name = name;
        this.waterAmount = waterAmount;
        this.coffeeAmount = coffeeAmount;
        this.milkAmount = milkAmount;
    }
}
