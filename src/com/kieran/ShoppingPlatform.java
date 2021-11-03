package com.kieran;

import java.util.HashMap;
import java.util.Map;

public class ShoppingPlatform {

    private final String platformName;
    private final Inventory stockList;
    private final HashMap<String, User> users;

    public ShoppingPlatform(String platformName) {
        this.platformName = platformName;
        this.users = new HashMap<>();
        this.stockList = new Inventory();
    }

    public HashMap<String, User> getUsers() {
        return new HashMap<>(this.users);
    }

    public void addToStockList(String itemName, double price, int quantity) {
        this.stockList.addStock(new Item(itemName, price), quantity);
    }

    public User getOrCreateUser(String name) {

        if (this.users.get(name) == null) {

            User newUser = new User(name);

            this.users.put(newUser.getName(), newUser);

            return newUser;

        }

        return this.users.get(name);

    }

    public void addItemToBasket(User currentUser, String itemName, int quantity) {

        ShoppingBasket desiredBasket = currentUser.getBasket();
        Item desiredItem = this.stockList.queryStockItem(itemName);

        if ((desiredBasket != null) && (desiredItem != null) && (quantity > 0)) {

            boolean itemsWasReserved = stockList.reserveOrUnReserveStock(desiredItem, quantity, true);

            if (itemsWasReserved) {

                desiredBasket.addToBasket(desiredItem, quantity);

            } else {

                System.out.println("Insufficient stock. Unable to reserve " + quantity + " of " + itemName);

            }

        } else {

            System.out.println("Invalid basket, item or quantity");

        }

    }

    public void removeItemFromBasket(User currentUser, String itemName, int quantity) {

        ShoppingBasket desiredBasket = currentUser.getBasket();
        Item desiredItem = stockList.queryStockItem(itemName);

        if ((desiredBasket != null) && (desiredItem != null) && (quantity > 0)) {

            if (desiredBasket.removeFromBasket(desiredItem, quantity)) {
                stockList.reserveOrUnReserveStock(desiredItem, quantity, false);
            }

        } else {

            System.out.println("Invalid basket, item or quantity");

        }

    }

    public void checkOutBasket(User currentUser) {

        ShoppingBasket desiredBasket = currentUser.getBasket();

        if (desiredBasket != null) {

            System.out.println("Items you have purchased on " + this.platformName);

            for (Map.Entry<Item, Integer> entry: desiredBasket.getBasketItems().entrySet()) {

                stockList.reserveOrUnReserveStock(entry.getKey(), entry.getValue(), false);

                stockList.sellStock(entry.getKey(), entry.getValue());

                System.out.println("You have purchased " + entry.getValue() + " " + entry.getKey().getName());

            }

            currentUser.setCheckedOutStatus(true);

        } else {

            System.out.println("Invalid basket");

        }

    }

    public void printStockList() {

        for (Map.Entry<String, Item> item : this.stockList.getAvailableStockItems().entrySet()) {
            System.out.println(item.getKey());
        }

    }

}
