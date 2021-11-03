package com.kieran;

import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final ShoppingPlatform ONLINE_SHOP = new ShoppingPlatform("online shop");
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Welcome to the online shop");

        User currentUser = switchUser();

        while (!allCheckedOut()) {

            if (currentUser.isAdmin()) {

                System.out.println("You are logged in as admin and would like to add items to the shopping platform\n");

                addItemToStockList();

                currentUser.setCheckedOutStatus(true);

                currentUser = switchUser();

            } else {

                System.out.println("Hello " + currentUser.getName() + " what would you like to do?\n");

                String userChoice = showUserMenu();

                switch (userChoice) {

                    case "1":
                        addItemToBasket(currentUser);
                        break;

                    case "2":
                        removeItemFromBasket(currentUser);
                        break;

                    case "3":
                        System.out.println(currentUser.getBasket());
                        break;

                    case "4":
                        checkOutBasket(currentUser);
                        break;

                    default:
                        currentUser = switchUser();
                        break;

                }

            }

        }

        SCANNER.close();

    }

    private static void checkOutBasket(User currentUser) {
        ONLINE_SHOP.checkOutBasket(currentUser);
    }

    private static void removeItemFromBasket(User currentUser) {

        System.out.println(currentUser.getBasket());

        System.out.println("\nEnter the item to remove: ");
        String desiredItem = SCANNER.nextLine();

        System.out.println("\nEnter the quantity to remove: ");
        int desiredQuantity = SCANNER.nextInt();
        SCANNER.nextLine();

        ONLINE_SHOP.removeItemFromBasket(currentUser, desiredItem, desiredQuantity);

    }

    private static void addItemToBasket(User currentUser) {

        showStockItems();

        System.out.println("\nEnter the item you want: ");
        String desiredItem = SCANNER.nextLine();

        System.out.println("\nEnter the quantity you want: ");
        int desiredQuantity = SCANNER.nextInt();
        SCANNER.nextLine();

        ONLINE_SHOP.addItemToBasket(currentUser, desiredItem, desiredQuantity);

    }

    private static String showUserMenu() {

        System.out.println("1. Add Item to Basket");
        System.out.println("2. Remove Item from Basket");
        System.out.println("3. See Basket");
        System.out.println("4. Check out Basket");
        System.out.println("5. Switch User\n");

        System.out.println("Enter your choice: ");

        return SCANNER.nextLine();

    }

    private static void showStockItems() {
        System.out.println("Available items are: \n");
        ONLINE_SHOP.printStockList();
    }

    private static void addItemToStockList() {

        String done;

        do {

            System.out.println("What would item would you like to add?\n");
            String itemName = SCANNER.nextLine();
            SCANNER.nextLine();

            System.out.println("What is the price of that item?\n");
            double itemPrice = SCANNER.nextDouble();
            SCANNER.nextLine();

            System.out.println("What quantity of that item would you like to add?\n");
            int itemQuantity = SCANNER.nextInt();
            SCANNER.nextLine();

            ONLINE_SHOP.addToStockList(itemName, itemPrice, itemQuantity);

            System.out.println("Continue adding items? (y/n)");
            done = SCANNER.nextLine();

        } while (done.equalsIgnoreCase("y"));

    }

    private static boolean allCheckedOut() {

        for (Map.Entry<String, User> user : ONLINE_SHOP.getUsers().entrySet()) {

            if (!user.getValue().isCheckedOut()) {
                return false;
            }

        }

        return true;

    }

    private static User switchUser() {

        System.out.println("Enter your name: ");

        String name = SCANNER.nextLine();

        return ONLINE_SHOP.getOrCreateUser(name);

    }

}
