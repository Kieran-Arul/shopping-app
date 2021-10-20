package com.kieran;

import java.util.HashMap;
import java.util.Map;

public class ShoppingPlatform {

    private static final Inventory INVENTORY = new Inventory();
    private final String platformName;
    private final HashMap<String, ShoppingBasket> baskets;

    public ShoppingPlatform(String platformName) {

        this.platformName = platformName;
        this.baskets = new HashMap<>();

    }

    public String getPlatformName() {
        return this.platformName;
    }

    public void addToInventory(Item itemToAdd, int quantity) {

        INVENTORY.addItem(itemToAdd, quantity);

    }

    public void createBasket(String basketName) {

        ShoppingBasket newBasket = new ShoppingBasket(basketName);

        this.baskets.put(basketName, newBasket);

    }

    public ShoppingBasket getBasket(String basketName) {

        return this.baskets.get(basketName);

    }

    public void addItemToBasket(String basketName, String itemName, int quantity) {

        ShoppingBasket desiredBasket = this.getBasket(basketName);
        Item desiredItem = INVENTORY.queryItem(itemName);

        // Ensures that the desired basket, desired item and quantity are all valid
        if ((desiredBasket != null) && (desiredItem != null) && (quantity > 0)) {

            // Checks if the given quantity of that item can be reserved
            // (i.e. ensures there is still stock that can be reserved)
            boolean itemsWasReserved = INVENTORY.reserveOrUnReserveItem(desiredItem, quantity, true);

            // If it is possible to reserve the quantity of stock, requested, add that item in the desired quantity
            // to the basket
            if (itemsWasReserved) {

                desiredBasket.addToBasket(desiredItem, quantity);

            } else {

                System.out.println("Insufficient stock. Unable to reserve " + quantity + " of " + itemName);

            }

        } else {

            System.out.println("Invalid basket, item or quantity");

        }

    }

    public void removeItemFromBasket(String basketName, String itemName, int quantity) {

        ShoppingBasket desiredBasket = this.getBasket(basketName);
        Item desiredItem = INVENTORY.queryItem(itemName);

        if ((desiredBasket != null) && (desiredItem != null) && (quantity > 0)) {

            // Checks if to make sure that the user's basket contains at least the amount he wants to remove
            // Only if it is actually possible for the user to remove that amount of items from his basket
            // is the given quantity actually un-reserved
            if (desiredBasket.removeFromBasket(desiredItem, quantity)) {

                INVENTORY.reserveOrUnReserveItem(desiredItem, quantity, false);

            }

        } else {

            System.out.println("Invalid basket, item or quantity");

        }

    }

    public void checkOutBasket(String basketName) {

        ShoppingBasket desiredBasket = this.getBasket(basketName);

        if (desiredBasket != null) {

            System.out.println("Items you have purchased on " + this.platformName);

            // Loops over each entry in the basket (item to quantity purchased mapping)
            for (Map.Entry<Item, Integer> entry: desiredBasket.getBasketItems().entrySet()) {

                // Un-reserves the stockItem that is going to be sold
                // Could be included in the sell stock method actually since
                // when selling stock this must always be called
                INVENTORY.reserveOrUnReserveItem(entry.getKey(), entry.getValue(), false);

                // Decreases the stock quantity of the item that is being sold
                INVENTORY.sellItem(entry.getKey(), entry.getValue());

                System.out.println("You have purchased " + entry.getValue() + " " + entry.getKey().getName());

            }

        } else {

            System.out.println("Invalid basket");

        }

    }

    public void printInventory() {

        System.out.println(INVENTORY);

    }

}
