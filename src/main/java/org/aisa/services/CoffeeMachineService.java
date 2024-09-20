package org.aisa.services;

import org.aisa.entities.MachineInventory;
import org.aisa.repositories.MachineInventoryRepository;
import org.aisa.tools.exceptions.CoffeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeMachineService {
    private final MachineInventoryRepository machineInventoryRepository;

    @Autowired
    public CoffeeMachineService(MachineInventoryRepository machineInventoryRepository) {
        this.machineInventoryRepository = machineInventoryRepository;
    }

    public MachineInventory getInventory() throws CoffeeException {
        return machineInventoryRepository.findById(1L)
                .orElseThrow(CoffeeException::coffeeMachineInventoryNotInitializedException);
    }

    public MachineInventory createInventory(MachineInventory inventory) throws CoffeeException {
        if (inventory.getWater() == null || inventory.getCoffee() == null || inventory.getMilk() == null) {
            throw CoffeeException.recipeIngredientIsNullException();
        }

        if (inventory.getWater() < 0 || inventory.getCoffee() < 0 || inventory.getMilk() < 0) {
            throw CoffeeException.recipeIngredientIsNegativeException();
        }

        machineInventoryRepository.save(inventory);
        return inventory;
    }

    public MachineInventory updateInventory(Long id, MachineInventory newInventory) throws CoffeeException {
        MachineInventory existingInventory = machineInventoryRepository.findById(id)
                .orElseThrow(CoffeeException::coffeeMachineInventoryNotInitializedException);

        if (newInventory.getWater() != null) {
            if (newInventory.getWater() < 0) {
                throw CoffeeException.coffeeMachineIngredientIsNegativeException();
            }

            existingInventory.setWater(newInventory.getWater());
        }

        if (newInventory.getCoffee() != null) {
            if (newInventory.getCoffee() < 0) {
                throw CoffeeException.coffeeMachineIngredientIsNegativeException();
            }

            existingInventory.setCoffee(newInventory.getCoffee());
        }

        if (newInventory.getMilk() != null) {
            if (newInventory.getMilk() < 0) {
                throw CoffeeException.coffeeMachineIngredientIsNegativeException();
            }

            existingInventory.setMilk(newInventory.getMilk());
        }

        return machineInventoryRepository.save(existingInventory);
    }

    public void updateInventoryAfterOrdering(MachineInventory inventory) {
        machineInventoryRepository.save(inventory);
    }
}
