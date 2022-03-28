package tech.dumpsterfire.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String occasion;

    private String style;

    private String size;

    private String color;

    private String location;

    private String container;

    private Integer quantity;


    public InventoryItems(String type, String occasion, String style, String size, String color, String location, String container, Integer quantity) {
        this.type = type;
        this.occasion = occasion;
        this.style = style;
        this.size = size;
        this.color = color;
        this.location = location;
        this.container = container;
        this.quantity = quantity;
    }

}