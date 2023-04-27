package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {
    private ArrayList<Integer> orderNums = new ArrayList<>();
    private ListView ordersDisplay;
    private Spinner orderNumsSpinner;
    private TextView orderTotal;
    public static ArrayList<Order> currentStoreOrders;
    private ArrayAdapter<Order> arrayAdapter;
    private ArrayAdapter<MenuItem> arrayAdapter2;

    private static final DecimalFormat DF = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);
        ordersDisplay = (ListView) findViewById(R.id.store_orders_display);
        orderNumsSpinner = (Spinner) findViewById(R.id.order_nums_spinner);
        orderTotal = (TextView) findViewById(R.id.store_total);
        currentStoreOrders = MainActivity.allOrders;
        displayOrders();
        orderNumsSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        Object item = parent.getItemAtPosition(pos);
                        System.out.println("Spinner set to " + item.toString());  //prints the text in spinner item.
                        changeOrder(Integer.parseInt(item.toString()));

                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

    /**
     * Method that returns the total cost for an order as a String
     * @param target order for which to calculate cost
     * @return Total cost of target order
     */
    private String calculateTotal(Order target) {
        double sum = 0;
        for (MenuItem item : target.getItems()) {
            sum += item.itemPrice();
        }
        return ("$ " + Double.valueOf(DF.format(sum)));
    }

    private void displayOrders() {
        Order toDisplay;
        instantiateSpinner();
        int displayID = Integer.parseInt(orderNumsSpinner.getSelectedItem().toString());
        System.out.println("displayID " + displayID);
        for (Order item : currentStoreOrders) {
            if (item.getNum() == displayID) {
                arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, item.getItems());
                ordersDisplay.setAdapter(arrayAdapter2);
                orderTotal.setText(calculateTotal(item));
            }
        }
        // case where order not found?
        //todo NEED ONCLICK TO UPDATE LIST WHEN NEW SPINNER VALUE PICKED
    }

    /**
     * Changes order displayed
     * @param orderNum number associated with order to display
     */
    private void changeOrder(int orderNum) {
        for (Order item:currentStoreOrders) {
            if (item.getNum() == orderNum) {
                System.out.println("Found order " + orderNum);
                arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, item.getItems());
                ordersDisplay.setAdapter(arrayAdapter2);
                orderTotal.setText(calculateTotal(item));
            }
        }
    }

    private void instantiateSpinner() {
        for (Order item : currentStoreOrders) {
            orderNums.add(item.getNum());
        }
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, orderNums);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        orderNumsSpinner.setAdapter(ad);
    }
}