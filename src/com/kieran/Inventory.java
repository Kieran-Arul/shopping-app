package com.kieran;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Inventory {

    private final Map<String, Item> availableStockItems;

    public Inventory() {
        this.availableStockItems = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    }

    public Item queryStockItem(String stockItemName) {
        return this.availableStockItems.get(stockItemName);
    }

    public void addStock(Item stockItem, int quantity) {

        if ((stockItem != null) && (quantity >= 0)) {

            Item desiredStockItem = queryStockItem(stockItem.getName());

            if (desiredStockItem != null) {

                desiredStockItem.adjustStock(quantity);

            } else {

                stockItem.adjustStock(quantity);
                this.availableStockItems.put(stockItem.getName(), stockItem);

            }

        }

    }

    public boolean reserveOrUnReserveStock(Item stockItemToReserve, int quantity, boolean reserve) {

        if ((stockItemToReserve != null) && (quantity > 0)) {

            if (reserve) {

                return stockItemToReserve.adjustQuantityReserved(quantity);

            } else {

                return stockItemToReserve.adjustQuantityReserved(-quantity);

            }

        }

        return false;

    }

    public void sellStock(Item stockItemToSell, int quantity) {

        if ((stockItemToSell != null) && (quantity > 0)) {
            stockItemToSell.adjustStock(-quantity);
        }

    }

    public void updateStockItemPrice(String stockItemName, double newPrice) {

        Item desiredStockItem = this.queryStockItem(stockItemName);

        if (desiredStockItem != null) {

            desiredStockItem.setPrice(newPrice);

        } else {

            System.out.println("Stock Item does not exist");

        }

    }

    public Map<String, Item> getAvailableStockItems() {
        return Collections.unmodifiableMap(this.availableStockItems);
    }

}

