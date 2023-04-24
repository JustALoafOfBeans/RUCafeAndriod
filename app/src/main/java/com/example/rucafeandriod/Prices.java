package com.example.rucafeandriod;

public enum Prices {
    /**
     * Price in dollars of a SHORT coffee
     */
    SHORT(1.89),
    /**
     * Price in dollars of a TALL coffee
     */
    TALL(2.29),
    /**
     * Price in dollars of a GRANDE coffee
     */
    GRANDE(2.69),
    /**
     * Price in dollars of a VENTI coffee
     */
    VENTI(3.09),
    /**
     * Price in dollars of SYRUP coffee add-in
     */
    SYRUP(0.3),
    /**
     * Price in dollars of a YEAST donut
     */
    YEAST(1.59),
    /**
     * Price in dollars of a CAKE donut
     */
    CAKE(1.79),
    /**
     * Price in dollars of a donut HOLE
     */
    HOLE(0.39);

    /**
     * Double that designates price value
     */
    public final double val;

    /**
     * Attributes of prices
     * @param val dollar value of item as a double
     */
    Prices(double val) {
        this.val = val;
    }
}
