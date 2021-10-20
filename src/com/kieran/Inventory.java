package com.kieran;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Inventory {

    private final Map<String, Item> availableItems;

    public Inventory() {

        this.availableItems = new LinkedHashMap<>();

    }

    public Item queryItem(String itemName) {

        return this.availableItems.get(itemName);

    }

    public void addItem(Item item, int quantity) {

        if ((item != null) && (quantity >= 0)) {

            Item desiredStockItem = queryItem(item.getName());

            if (desiredStockItem != null) {

                desiredStockItem.adjustStock(quantity);

            } else {

                item.adjustStock(quantity);
                this.availableItems.put(item.getName(), item);

            }

        }

    }

    public boolean reserveOrUnReserveItem(Item itemToReserve, int quantity, boolean reserve) {

        if ((itemToReserve != null) && (quantity > 0)) {

            if (reserve) {

                return itemToReserve.adjustQuantityReserved(quantity);

            } else {

                return itemToReserve.adjustQuantityReserved(-quantity);

            }

        }

        return false;

    }

    public void sellItem(Item itemToSell, int quantity) {

        if ((itemToSell != null) && (quantity > 0)) {

            itemToSell.adjustStock(-quantity);

        }

    }

    public void updateItemPrice(String itemName, double newPrice) {

        Item desiredStockItem = this.queryItem(itemName);

        if (desiredStockItem != null) {

            desiredStockItem.setPrice(newPrice);

        }

    }

    public Map<String, Item> getAvailableItems() {

        return Collections.unmodifiableMap(this.availableItems);

    }

    @Override
    public String toString() {

        StringBuilder message = new StringBuilder("\nINVENTORY\n");
        double totalCost = 0.0;

        for (Map.Entry<String, Item> itemMapping: this.availableItems.entrySet()) {

            Item stockItem = itemMapping.getValue();
            double stockItemValue = stockItem.getPrice() * stockItem.getQuantityInStock();

            message.append("There are ").append(stockItem.getQuantityInStock()).append(" of ").append(stockItem.getName()).append(" in stock\n");
            message.append("There are ").append(stockItem.getQuantityReserved()).append(" of ").append(stockItem.getName()).append(" under reservation\n");
            totalCost += stockItemValue;

        }

        return message + "Total Inventory Value = $" + totalCost;

    }

}

