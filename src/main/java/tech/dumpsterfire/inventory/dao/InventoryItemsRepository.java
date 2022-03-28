package tech.dumpsterfire.inventory.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.dumpsterfire.inventory.model.InventoryItems;

public interface InventoryItemsRepository extends JpaRepository<InventoryItems, Long> {

    InventoryItems findById(InventoryItems id);
}
