package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity {
    private ArrayList<Integer> orderNums = new ArrayList<>();
    private ListView ordersDisplay;
    private Spinner orderNumsSpinner;
    public static ArrayList<Order> currentStoreOrders;
    private ArrayAdapter<Order> arrayAdapter;
    private ArrayAdapter<MenuItem> arrayAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);
        ordersDisplay = (ListView) findViewById(R.id.store_orders_display);
        orderNumsSpinner = (Spinner) findViewById(R.id.order_nums_spinner);
        currentStoreOrders = MainActivity.allOrders;
        displayOrders();
        orderNumsSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                        Object item = parent.getItemAtPosition(pos);
                        System.out.println(item.toString());     //prints the text in spinner item.

                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

    private void displayOrders() {
        Order toDisplay;
        instantiateSpinner();
        int displayID = Integer.parseInt(orderNumsSpinner.getSelectedItem().toString());
        for (Order item : currentStoreOrders) {
            if (item.getNum() == displayID) {
                arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, item.getItems());
                ordersDisplay.setAdapter(arrayAdapter2);
            }
        }
        // case where order not found?
        //todo NEED ONCLICK TO UPDATE LIST WHEN NEW SPINNER VALUE PICKED
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