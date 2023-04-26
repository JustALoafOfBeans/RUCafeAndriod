package com.example.rucafeandriod;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageButton donutButton, coffeeButton, basketButton, storeOrdersButton;
    /**
     * Single array list to hold all items in basket. Makes it easier so only have to pass one item between activities.
     */
    public static ArrayList<MenuItem> itemsInBasket;
    public static ArrayList<Order> allOrders;
    private ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // watched https://www.youtube.com/watch?v=Ke9PaRdMcgc
            if (result != null && result.getResultCode() == RESULT_OK) {
                if(result.getData() != null && result.getData().getSerializableExtra("c") != null) { //retrieve coffee to add
                    Coffee cup = (Coffee) result.getData().getSerializableExtra("c");
                    addCoffee(cup);
                } else if (result.getData() != null && result.getData().getSerializableExtra("b") != null) { //retrieve updated basket
                    itemsInBasket = (ArrayList<MenuItem>) result.getData().getSerializableExtra("b");
                } else if (result.getData() != null && result.getData().getSerializableExtra("o") != null) {
                    Order toAdd = (Order) result.getData().getSerializableExtra("o");
                    addToStoreOrders(toAdd);
                    itemsInBasket.clear();
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateButtons();
    }

    private void addToStoreOrders(Order toAdd) {
        if (allOrders == null) {
            allOrders = new ArrayList<>();
        }
        allOrders.add(toAdd);
        for (Order item : allOrders) {
            System.out.print(item.getNum() + " : ");
            System.out.println(item.getItems());
        }
    }

    private void addCoffee(Coffee toAdd) {
        if (itemsInBasket == null) {
            itemsInBasket = new ArrayList<>();
        }
        itemsInBasket.add(toAdd);
    }

    private void instantiateButtons() {
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
        activityResultLaunch.launch(basketIntent);
    }

    public void openDonutActivity() {
        Intent donutIntent = new Intent(this, DonutActivity.class);
        activityResultLaunch.launch(donutIntent);
    }

    public void openCoffeeActivity() {
        Intent coffeeIntent = new Intent(this, CoffeeActivity.class);
        activityResultLaunch.launch(coffeeIntent);
    }
}