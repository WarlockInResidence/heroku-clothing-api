package tech.dumpsterfire.inventory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.dumpsterfire.inventory.dao.*;
import tech.dumpsterfire.inventory.model.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/items")
    public @ResponseBody
    List<InventoryItems> getInventoryItems() {
        return inventoryService.getItems();
    }

    @PostMapping("/items")
    public ResponseEntity<InventoryItems> addItem(@RequestBody InventoryItems inventoryItems) {
        var savedInventoryItem = inventoryService.addItem(inventoryItems);
        URI location = createResourceLocation("/items", savedInventoryItem.getId());
        return ResponseEntity.created(location).body(savedInventoryItem);
    }

    private URI createResourceLocation(String path, Long resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().port("8080").path(path)
                .buildAndExpand(resourceId).toUri();
    }

    // Added new stuff below. make sure notes explain each of them

    // Make get single item by id
    // 1. in Repo -> Product findById(Product id);
    // 2. in service ->  public Optional<Product> find(Long id) { return productRepository.findById(id); }
    @GetMapping("/items/{id}")
    public Optional<InventoryItems> getById(@PathVariable Long id) {
        return inventoryService.find(id);
    }

    // Make patch
    // in service -> public void updateProduct(Product product) { productRepository.save(product); }
    @PatchMapping("/items/{id}")
    public ResponseEntity<InventoryItems> updateInventory(@PathVariable Long id, @RequestBody InventoryItems inventoryItems) {
        InventoryItems patchItem = inventoryService.find(id).orElse(null);

        if (inventoryItems.getType() != null) patchItem.setType((inventoryItems.getType()));
        if (inventoryItems.getOccasion() != null) patchItem.setOccasion((inventoryItems.getOccasion()));
        if (inventoryItems.getStyle() != null) patchItem.setStyle((inventoryItems.getStyle()));
        if (inventoryItems.getSize() != null) patchItem.setSize((inventoryItems.getSize()));
        if (inventoryItems.getColor() != null) patchItem.setColor((inventoryItems.getColor()));
        if (inventoryItems.getLocation() != null) patchItem.setLocation((inventoryItems.getLocation()));
        if (inventoryItems.getContainer() != null) patchItem.setContainer((inventoryItems.getContainer()));
        if (inventoryItems.getQuantity() != null) patchItem.setQuantity((inventoryItems.getQuantity()));
        inventoryService.updateInventoryItems(patchItem);

        return new ResponseEntity<InventoryItems>(inventoryItems, HttpStatus.OK);
    }

//    // Delete - Delete
//    @DeleteMapping("/items/{id}")
//    public String deleteItem(@PathVariable Long id) {
//        InventoryItems deleteItem = this.inventoryItemsRepository.findById(id).get();
//        if (deleteItem != null) {
//            this.inventoryItemsRepository.deleteById(id);
//            return this.inventoryItemsRepository.count() + " users remaining.";
//        } else
//            return "Item not found, try another user Id.";
//    }
}
