package com.example.rucafeandriod;

public abstract class MenuItem {
    /**
     * Abstract method that returns the price of an item as a double
     * @return price as a formatted double
     */
    public abstract double itemPrice();

    /**
     * Abstract method that returns menu item as a string
     * @return String format of item
     */
    @Override
    public abstract String toString();
}
