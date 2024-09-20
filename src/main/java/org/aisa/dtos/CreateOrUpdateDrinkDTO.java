package org.aisa.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrUpdateDrinkDTO {
    private String name;
    private Long waterAmount;
    private Long coffeeAmount;
    private Long milkAmount;
}
