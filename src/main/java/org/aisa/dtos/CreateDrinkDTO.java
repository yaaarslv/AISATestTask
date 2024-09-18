package org.aisa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDrinkDTO {
    private String name;
    private int waterAmount;
    private int coffeeAmount;
    private int milkAmount;
}
