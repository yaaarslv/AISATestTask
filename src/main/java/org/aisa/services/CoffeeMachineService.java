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

    public void updateInventory(MachineInventory inventory) {
        machineInventoryRepository.save(inventory);
    }
}
