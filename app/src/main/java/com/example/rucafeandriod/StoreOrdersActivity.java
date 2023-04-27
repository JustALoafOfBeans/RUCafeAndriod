package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Class that manages order review Activity
 * Called by and returns to MainActivity
 * @author Victoria Chen, Bridget Zhang
 */
public class StoreOrdersActivity extends AppCompatActivity {
    /**
     * List of all orders
     */
    private ArrayList<Integer> orderNums = new ArrayList<>();
    /**
     * ListView UI of all orders
     */
    private ListView ordersDisplay;
    /**
     * Dropdown menu to select order number
     */
    private Spinner orderNumsSpinner;
    /**
     * Text UI for total cost of selected order
     */
    private TextView orderTotal;
    /**
     * Button UI to cancel a selected order
     */
    private AppCompatButton orderCancel;
    /**
     * List of all current orders
     */
    public static ArrayList<Order> currentStoreOrders;
    /**
     * ArrayAdapter for all orders
     */
    private ArrayAdapter<MenuItem> arrayAdapter2;
    /**
     *  Constant for tax rate in NJ, applied to find tax and total costs
     */
    private static final double TAXNJ = 0.06625;
    /**
     * Double formatting for decimal prices
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * Method that handles initial setup for Activity
     * @param savedInstanceState information from previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);
        ordersDisplay = findViewById(R.id.store_orders_display);
        orderNumsSpinner = findViewById(R.id.order_nums_spinner);
        orderTotal = findViewById(R.id.store_total);
        orderCancel = findViewById(R.id.delete);
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
        orderCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderNumsSpinner.getSelectedItem() == null) {
                    Toast.makeText(StoreOrdersActivity.this, "There are no current orders", Toast.LENGTH_SHORT).show();
                } else {
                    int selected = Integer.parseInt(orderNumsSpinner.getSelectedItem().toString());
                    System.out.println("Attempting to delete order #" + selected);
                    deleteOrder(selected);
                }
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
        sum += sum*TAXNJ;
        return ("$ " + Double.valueOf(DF.format(sum)));
    }

    /**
     * Method that resets page, displaying first available order
     * Called for initial setup and when an order is removed
     */
    private void displayOrders() {
        Order toDisplay;
        instantiateSpinner();
        if (!currentStoreOrders.isEmpty()) {
            int displayID = Integer.parseInt(orderNumsSpinner.getSelectedItem().toString());
            System.out.println("displayID " + displayID);
            for (Order item : currentStoreOrders) {
                if (item.getNum() == displayID) {
                    arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, item.getItems());
                    ordersDisplay.setAdapter(arrayAdapter2);
                    orderTotal.setText(calculateTotal(item));
                }
            }
        }
    }

    /**
     * Changes order displayed
     * @param orderNum number associated with order to display
     */
    private void changeOrder(int orderNum) {
        for (Order item:currentStoreOrders) {
            if (item.getNum() == orderNum) {
                arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, item.getItems());
                ordersDisplay.setAdapter(arrayAdapter2);
                orderTotal.setText(calculateTotal(item));
            }
        }
    }

    /**
     * Method that deletes a given order
     * @param orderNum index of the order to remove
     */
    private void deleteOrder(int orderNum) {
        Order remOrder = null; // Order to remove
        for (Order ord : currentStoreOrders) {
            if (ord.getNum() == orderNum) {
                remOrder = ord;
            }
        }
        if (remOrder != null) {
            currentStoreOrders.remove(remOrder);
        }
        orderNums.clear();
        if (currentStoreOrders.size() == 0) {
            exitOrders();
        } else {
            displayOrders();
        }
    }

    /**
     * Method that initializes spinner for order selection
     */
    private void instantiateSpinner() {
        if (currentStoreOrders != null) {
            for (Order item : currentStoreOrders) {
                orderNums.add(item.getNum());
            }
            ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, orderNums);
            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            orderNumsSpinner.setAdapter(ad);
        } else {
            currentStoreOrders = new ArrayList<>();
        }
    }

    /**
     * Method that wraps up and exits current activity
     * Called when all orders removed
     */
    private void exitOrders() {
        Intent intent = new Intent (this, MainActivity.class);
        intent.putExtra("ord", currentStoreOrders);
        setResult(RESULT_OK,intent);
        finish();
    }
}