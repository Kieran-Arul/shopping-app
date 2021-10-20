package com.kieran;

public class Main {

    private static final ShoppingPlatform myOnlineShop = new ShoppingPlatform("My Online Shop");

    public static void main(String[] args) {

        Item apple = new Item("Apple", 0.50);
        Item orange = new Item("Orange", 1.85);
        Item carrot = new Item("Carrot", 2.56);
        Item zombie = new Item("Zombie", 2.86);

        myOnlineShop.addToInventory(apple, 50);
        myOnlineShop.addToInventory(orange, 50);
        myOnlineShop.addToInventory(carrot, 50);
        myOnlineShop.addToInventory(zombie, 50);

        myOnlineShop.createBasket("My Basket");

        myOnlineShop.addItemToBasket("My Basket", "Apple", 40);

        myOnlineShop.printInventory();

        System.out.println("============================================================");

        myOnlineShop.checkOutBasket("My Basket");

        System.out.println("============================================================");

        myOnlineShop.printInventory();

    }

}
