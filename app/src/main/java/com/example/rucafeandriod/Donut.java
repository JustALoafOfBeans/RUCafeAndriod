package com.example.rucafeandriod;

import com.example.rucafeandriod.Prices;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The Donut class extends the abstract MenuItem class and describes orders
 * of donuts from the RU Cafe
 * @author Victoria Chen, Bridget Zhang
 */
public class Donut extends MenuItem{
    /**
     * Type of donut item selected (cake, yeast, or hole)
     */
    private String type;
    /**
     * Amount of item selected
     */
    private int quantity;
    /**
     * Flavor of donut selected, options vary with type
     */
    private String flavor;

    private int image;

    /**
     * Initial size constant
     */
    private static int INIT = 0;
    /**
     * Error integer
     */
    private static int ERROR = -1;
    /**
     * Use DF.format(value) to round value to two decimals places. Rounding
     * up or down is by basic convention. Returns a String.
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * Constructor for Donut class
     */
    public Donut(String fla, int image) {
        this.quantity = INIT;
        this.image = image;
        this.flavor = fla;
        switch(flavor) {
            case "Chocolate Glaze":
            case "Strawberry Glaze":
            case "Boston Cream":
            case "Cookies & Cream":
            case "Raspberry Glaze":
                this.type = "Yeast Donut";
                break;
            case "Lemon":
            case "Blueberry":
            case "Chocolate":
            case "Matcha":
                this.type = "Cake Donut";
                break;
            case "Brownie":
            case "Glazed":
            case "Pumpkin":
                this.type = "Donut Hole";
                break;
        }
    }

    /**
     * Method that returns price of added item based on quantity, flavor, and type
     * @return Price as a double, formatted to two decimal places
     */
    public double itemPrice() {
        switch (type)  {
            case "Yeast Donut":
                return Double.parseDouble(DF.format(Prices.YEAST.val));
            case "Cake Donut":
                return Double.parseDouble(DF.format(Prices.CAKE.val));
            case "Donut Hole":
                return Double.parseDouble(DF.format(Prices.HOLE.val));
            default:
                return ERROR;
        }
    }

    /**
     * Checks if a given Donut object is equivalent to the current one
     * @param equalObject Donut to compare
     * @return true if Donut objects are equivalent, false otherwise
     */
    @Override
    public boolean equals(Object equalObject) {
        if (equalObject instanceof Donut)
        {
            Donut equalDonut = (Donut) equalObject; // Cast into Student if can
            return (this.compareTo(equalDonut) == INIT);
        }
        return false; // Not of type student, invalid comparison
    }

    /**
     * Compares two Donut objects
     * @param compareDonut Donut object to compare to
     * @return 0 if String forms equal, -1 if not
     */
    public int compareTo(Donut compareDonut) {
        if (this.toString().equals(compareDonut.toString())) {
            return INIT;
        }
        return ERROR;
    }

    /**
     * Method that presents Donut object as a printable String format
     * @return String format of Donut
     */
    @Override
    public String toString() {
        return quantity + " " + type + " (" + flavor + ")";
    }

    // Returns String format with no quantity for menu list
    public String toStringMenu() {
        return type + "\n(" + flavor + ")";
    }

    /**
     * Method that returns type of Donut
     * @return type as String
     */
    public String getType() {
        return type;
    }

    /**
     * Method that returns flavor of Donut object
     * @return flavor as String
     */
    public String getFlavor() {
        return flavor;
    }

    /**
     * Method that returns quantity of Donut object
     * @return number as int
     */
    public int getQuantity() {
        return quantity;
    }

    public int getImage() {
        return image;
    }

    /**
     * Method that updates quantity of Donut object
     * Used to account for duplicate orders (same type and flavor)
     * @param newQuantity new value to update quantity to
     */
    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }
}
