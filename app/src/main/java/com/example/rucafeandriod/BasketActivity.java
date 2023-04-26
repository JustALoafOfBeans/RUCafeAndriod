package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {
    private ListView basketContents;
    private ArrayList<MenuItem> currentBasket;
    private TextView subtotal, tax, total;
    /**
     *  Constant for tax rate in NJ, applied to find tax and total costs
     */
    private static final double TAXNJ = 0.06625;
    /**
     * Use DF.format(value) to round value to two decimals places. Rounding
     * up or down is by basic convention. Returns a String.
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        basketContents = (ListView) findViewById(R.id.basket_list);
        subtotal = (TextView) findViewById(R.id.subtotal);
        tax = (TextView) findViewById(R.id.tax);
        total = (TextView) findViewById(R.id.total);
        currentBasket = MainActivity.itemsInBasket;
        displayBasket();
    }

    private void displayBasket() {
        ArrayAdapter<MenuItem> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentBasket);
        basketContents.setAdapter(arrayAdapter);
        updateTotals();
    }

    private void updateTotals() {
        double sum = 0;
        for (MenuItem item : currentBasket){
            sum += item.itemPrice();
        }
        subtotal.setText("Subtotal: $" + sum);
        double taxValue = Double.valueOf(DF.format(sum*TAXNJ));
        tax.setText("Tax: $" + taxValue);
        double totalValue = Double.valueOf(DF.format(sum + sum*TAXNJ));
        total.setText("Total: $" + totalValue);
    }
}