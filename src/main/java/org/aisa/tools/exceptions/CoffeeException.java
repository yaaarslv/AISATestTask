package org.aisa.tools.exceptions;

public class CoffeeException extends Exception {
    private CoffeeException(String message) {
        super(message);
    }

    public static CoffeeException coffeeTypeNotSelectedException() {
        return new CoffeeException("Не выбран кофе для приготовления");
    }

    public static CoffeeException coffeeIsNullException() {
        return new CoffeeException("Передан пустой объект кофе");
    }

    public static CoffeeException coffeeTypeNotFoundException() {
        return new CoffeeException("Данный тип кофе не найден");
    }

    public static CoffeeException notEnoughWater(String coffeeName, Long inventoryWater, Long waterAmount) {
        return new CoffeeException("Недостаточно воды для приготовления " + coffeeName + ". В наличии: " + inventoryWater + ", требуется: " + waterAmount);
    }

    public static CoffeeException notEnoughCoffee(String coffeeName, Long inventoryCoffee, Long coffeeAmount) {
        return new CoffeeException("Недостаточно кофе для приготовления " + coffeeName + ". В наличии: " + inventoryCoffee + ", требуется: " + coffeeAmount);
    }

    public static CoffeeException notEnoughMilk(String coffeeName, Long inventoryMilk, Long milkAmount) {
        return new CoffeeException("Недостаточно молока для приготовления " + coffeeName + ". В наличии: " + inventoryMilk + ", требуется: " + milkAmount);
    }

    public static CoffeeException coffeeStatisticNotFoundException() {
        return new CoffeeException("Статистика не найдена");
    }

    public static CoffeeException coffeeStatisticIsEmptyException() {
        return new CoffeeException("Статистика пустая");
    }

    public static CoffeeException coffeeMachineInventoryNotInitializedException() {
        return new CoffeeException("Запасы кофемашины не инициализированы");
    }
}