package spiel.inventory;

import spiel.inventory.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;


    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void printInventory() {
        if (items.isEmpty()) {
            System.out.println("Das Inventar ist leer.");
        } else {
            System.out.println("Inventar:");
            for (Item item : items) {
                System.out.println("- " + item.getName() + ": " + item.getBeschreibung());
            }
        }
    }
}
