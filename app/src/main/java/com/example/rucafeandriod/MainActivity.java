package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton donutButton, coffeeButton, basketButton, storeOrdersButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Open Donuts Activity Page
        donutButton = (ImageButton) findViewById(R.id.donutButton);
        donutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDonutActivity();
            }
        });

        // Open Coffee Activity Page
        coffeeButton = (ImageButton) findViewById(R.id.coffeeButton);
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoffeeActivity();
            }
        });

        // Open Current Order Activity Page
        basketButton = (ImageButton) findViewById(R.id.basketButton);
        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBasketActivity();
            }
        });

        // Open Store Orders Activity Page
        storeOrdersButton = (ImageButton) findViewById(R.id.storeOrdersButton);
        storeOrdersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStoreOrdersActivity();
            }
        });
    }

    public void openStoreOrdersActivity() {
        Intent storeIntent = new Intent(this, StoreOrdersActivity.class);
        startActivity(storeIntent);
    }

    public void openBasketActivity() {
        Intent basketIntent = new Intent(this, BasketActivity.class);
        startActivity(basketIntent);
    }

    public void openDonutActivity() {
        Intent donutIntent = new Intent(this, DonutActivity.class);
        startActivity(donutIntent);
    }

    public void openCoffeeActivity() {
        Intent coffeeIntent = new Intent(this, CoffeeActivity.class);
        startActivity(coffeeIntent);
    }
}