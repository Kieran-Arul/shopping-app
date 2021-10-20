package com.kieran;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ShoppingBasket {

    private final String name;
    private final Map<Item, Integer> addedItems;

    public ShoppingBasket(String name) {

        this.name = name;
        this.addedItems = new TreeMap<>();

    }

    public int getBasketItemQuantity(Item item) {

        return this.addedItems.getOrDefault(item, 0);

    }

    public void addToBasket(Item itemToAdd, int quantity) {

        if ((itemToAdd != null) && (quantity > 0)) {

            int similarItemsAlreadyInBasket = this.addedItems.getOrDefault(itemToAdd, 0);

            this.addedItems.put(itemToAdd, similarItemsAlreadyInBasket + quantity);

            System.out.println(itemToAdd.getName() + " added to basket. Quantity = " + quantity);

        }

    }

    public boolean removeFromBasket(Item itemToRemove, int quantity) {

        if ((itemToRemove != null) && (quantity > 0)) {

            int similarItemsAlreadyInBasket = this.addedItems.getOrDefault(itemToRemove, 0);

            if (similarItemsAlreadyInBasket == quantity) {

                this.addedItems.remove(itemToRemove);

                return true;

            } else if (similarItemsAlreadyInBasket > quantity) {

                this.addedItems.put(itemToRemove, similarItemsAlreadyInBasket - quantity);

                return true;

            } else {

                System.out.println("Unable to remove that quantity from the basket");

            }

        }

        return false;

    }

    public Map<Item, Integer> getBasketItems() {

        return Collections.unmodifiableMap(this.addedItems);

    }

    @Override
    public String toString() {

        StringBuilder message = new StringBuilder("\nShopping Basket " + this.name + " contains " + this.addedItems.size() + " items\n");
        double totalCost = 0.0;

        for (Map.Entry<Item, Integer> mapPair: this.addedItems.entrySet()) {

            message.append(mapPair.getKey().getName()).append(". ").append(mapPair.getValue()).append(" purchased\n");
            totalCost += mapPair.getKey().getPrice() * mapPair.getValue();

        }

        return message + "Total Cost: " + String.format("%.2f", totalCost);

    }

}

