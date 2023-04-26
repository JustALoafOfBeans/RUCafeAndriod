package com.example.rucafeandriod;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Coffee class extends the MenuItem abstract class and is used for orders
 * of coffee from the cafe
 * @author Victoria Chen
 */
public class Coffee extends MenuItem {
    /**
     * Initial size constant
     */
    private static int INIT = 0;
    /**
     * Size of coffee, including short, tall, grande, and venti
     */
    private String cupSize;
    /**
     * List of add-ins and extra seasoning for coffee
     */
    private ArrayList<String> addIns;
    /**
     * Integer that contains number of coffees associated with order
     */
    private int quantity;
    /**
     * Use DF.format(value) to round value to two decimals places. Rounding
     * up or down is by basic convention. Returns a String.
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * Constructor for coffee object
     *
     * @param size   the size (small-venti) of coffee added
     * @param addIns list of add-ins such as cream
     * @param num    quantity of coffees of this variant
     */
    public Coffee(String size, ArrayList<String> addIns, int num) {
        this.cupSize = size;
        this.addIns = addIns;
        this.quantity = num;
    }

    /**
     * Method that returns coffee price based on amount, size, and add-ins
     *
     * @return Price as a double formatted to two decimal places
     */
    public double itemPrice() {
        double price = INIT;
        if (cupSize.equals("Short")) {
            price += Prices.SHORT.val;
        } else if (cupSize.equals("Tall")) {
            price += Prices.TALL.val;
        } else if (cupSize.equals("Grande")) {
            price += Prices.GRANDE.val;
        } else if (cupSize.equals("Venti")) {
            price += Prices.VENTI.val;
        }
        price += Prices.SYRUP.val * addIns.size();
        price = price * quantity;
        return Double.valueOf(DF.format(price));
    }

    /**
     * Method that returns coffee object as a String to print
     *
     * @return String form of item
     */
    @Override
    public String toString() {
        int ADJUST = 2;
        String item = quantity + " " + cupSize + " coffee";
        if (addIns.size() != INIT) {
            item += " (";
            for (int i = INIT; i < addIns.size(); i++) {
                item += addIns.get(i) + ", ";
            }
            item = item.substring(INIT, item.length() - ADJUST); //crop last ", "
            item += ")";
        }
        return item;
    }
}