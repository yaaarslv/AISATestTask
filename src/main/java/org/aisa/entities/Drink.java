package org.aisa.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "drink")
public class Drink {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "water_amount")
    private int waterAmount;

    @Column(name = "coffee_amount")
    private int coffeeAmount;

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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(int waterAmount) {
        this.waterAmount = waterAmount;
    }

    public int getCoffeeAmount() {
        return coffeeAmount;
    }

    public void setCoffeeAmount(int coffeeAmount) {
        this.coffeeAmount = coffeeAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public void setMilkAmount(int milkAmount) {
        this.milkAmount = milkAmount;
    }
}
