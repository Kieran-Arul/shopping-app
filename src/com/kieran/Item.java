package com.kieran;

public class Item implements Comparable<Item> {

    private final String name;
    private double price;
    private int quantityStock;
    private int quantityReserved;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
        this.quantityReserved = 0;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantityInStock() {
        return this.quantityStock;
    }

    public int getQuantityReserved() {
        return this.quantityReserved;
    }

    public void setPrice(double price) {

        if (price > 0) {

            this.price = price;

        } else {

            System.out.println("Price cannot be 0 or negative");

        }

    }

    public boolean adjustQuantityReserved(int quantity) {

        int newReservedQuantity = this.quantityReserved + quantity;

        if ((this.quantityStock >= newReservedQuantity) && (newReservedQuantity >= 0)) {

            this.quantityReserved = newReservedQuantity;

            return true;

        }

        System.out.println("Unable to reserve/un-reserve that quantity");

        return false;

    }

    public void adjustStock(int quantity) {
        this.quantityStock += quantity;
    }

    @Override
    public int hashCode() {
        return (this.name.hashCode()) + 57;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if ((obj == null) || (this.getClass() != obj.getClass())) {
            return false;
        }

        return this.name.equals(((Item) obj).getName());

    }

    @Override
    public int compareTo(Item item) {

        if (this == item) {
            return 0;
        }

        if (item != null) {
            return this.name.compareTo(item.getName());
        }

        throw new NullPointerException();

    }

}
