package com.kieran;

public class User {

    private final String name;
    private final ShoppingBasket basket;
    private boolean checkedOut;
    private boolean isAdmin;

    public User(String name) {
        this.name = name;
        this.basket = new ShoppingBasket();
        this.checkedOut = false;
        this.isAdmin = this.name.equalsIgnoreCase("admin");

    }

    public String getName() {
        return this.name;
    }

    public ShoppingBasket getBasket() {
        return this.basket;
    }

    public boolean isCheckedOut() {
        return this.checkedOut;
    }

    public void setCheckedOutStatus(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdminStatus(boolean admin) {
        isAdmin = admin;
        this.checkedOut = true;
    }

}
