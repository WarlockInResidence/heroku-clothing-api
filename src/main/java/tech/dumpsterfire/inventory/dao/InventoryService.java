package tech.dumpsterfire.inventory.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.dumpsterfire.inventory.model.InventoryItems;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService {

    InventoryItemsRepository inventoryItemsRepository;

    public InventoryItems addItem(String inventoryItems) {
        return inventoryItemsRepository.save(new InventoryItems(inventoryItems, "","","", "","", "", 0));
    }

    public InventoryItems addItem(InventoryItems inventoryItems) {
        return inventoryItemsRepository.save( inventoryItems );
    }

    public List<InventoryItems> getItems() { return inventoryItemsRepository.findAll();
    }

    public Optional<InventoryItems> find(Long id) {
        return inventoryItemsRepository.findById(id);
    }

    public void updateInventoryItems(InventoryItems inventoryItems) {
        inventoryItemsRepository.save(inventoryItems);
    }
}

