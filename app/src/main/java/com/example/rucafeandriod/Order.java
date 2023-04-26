package com.example.rucafeandriod;

import java.util.ArrayList;

/**
 * Class that represents a finalized order as a number and the items within it
 * @author Victoria Chen, Bridget Zhang
 */
public class Order {
    /**
     * Number assigned to the order that identifies it
     */
    private int orderNum;
    /**
     * List of items in the order
     */
    private ArrayList<MenuItem> items;

    /**
     * Constructor for Order class
     * @param num unique number assigned to identify order
     * @param content list of MenuItems in the order
     */
    public Order(int num, ArrayList<MenuItem> content) {
        this.orderNum = num;
        this.items = content;
    }

    /**
     * Method that returns the order's identifying number
     * @return unique order number
     */
    public int getNum() {
        return orderNum;
    }

    /**
     * Method that returns list of MenuItems in the order
     * @return ObservableList of items
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }
}