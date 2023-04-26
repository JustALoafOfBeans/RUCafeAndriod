package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class StoreOrdersActivity extends AppCompatActivity {
    private ListView ordersDisplay;
    private ArrayAdapter<Order> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_orders);
        ordersDisplay = (ListView) findViewById(R.id.store_orders_display);
        displayOrders();
    }

    private void displayOrders() {
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.allOrders);
        ordersDisplay.setAdapter(arrayAdapter);
    }
}