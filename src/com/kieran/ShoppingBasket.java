package com.kieran;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class ShoppingBasket {

    private final Map<Item, Integer> items;

    public ShoppingBasket() {
        this.items = new TreeMap<>();
    }

    public int getBasketItemQuantity(Item item) {
        return this.items.getOrDefault(item, 0);
    }

    public void addToBasket(Item itemToAdd, int quantity) {

        if ((itemToAdd != null) && (quantity > 0)) {

            int similarItemsAlreadyInBasket = this.items.getOrDefault(itemToAdd, 0);

            this.items.put(itemToAdd, similarItemsAlreadyInBasket + quantity);

            System.out.println(itemToAdd.getName() + " added to basket. Quantity = " + quantity);

        }

    }

    public boolean removeFromBasket(Item itemToRemove, int quantity) {

        if ((itemToRemove != null) && (quantity > 0)) {

            int similarItemsAlreadyInBasket = this.items.getOrDefault(itemToRemove, 0);

            if (similarItemsAlreadyInBasket == quantity) {

                this.items.remove(itemToRemove);

                return true;

            } else if (similarItemsAlreadyInBasket > quantity) {

                this.items.put(itemToRemove, similarItemsAlreadyInBasket - quantity);

                return true;

            } else {

                System.out.println("Unable to remove that quantity from the basket");

            }

        }

        return false;

    }

    public Map<Item, Integer> getBasketItems() {

        return Collections.unmodifiableMap(this.items);

    }

    @Override
    public String toString() {

        StringBuilder message = new StringBuilder("\nShopping Basket contains " + this.items.size() + " items\n");
        double totalCost = 0.0;

        for (Map.Entry<Item, Integer> mapPair: this.items.entrySet()) {

            message.append(mapPair.getKey().getName()).append(". ").append(mapPair.getValue()).append(" purchased\n");
            totalCost += mapPair.getKey().getPrice() * mapPair.getValue();

        }

        return message + "Total Cost: " + String.format("%.2f", totalCost);

    }

}

