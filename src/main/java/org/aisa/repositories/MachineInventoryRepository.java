package org.aisa.repositories;

import org.aisa.entities.MachineInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Database repository for MachineInventory entity
 */
@Repository
public interface MachineInventoryRepository extends JpaRepository<MachineInventory, Long> {
}
