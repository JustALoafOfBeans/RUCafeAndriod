package com.example.rucafeandriod;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import java.util.ArrayList;

/**
 * Main Activity that manages reactions leading to other windows
 * @author Victoria Chen, Bridget Zhang
 */
public class MainActivity extends AppCompatActivity {
    /**
     * Image button UI for linked Activity pages
     */
    private ImageButton donutButton, coffeeButton, basketButton, storeOrdersButton;
    /**
     * Single array list to hold all items in basket. Makes it easier so only have to pass one item between activities.
     */
    public static ArrayList<MenuItem> itemsInBasket;
    /**
     * Array list to hold all the orders that have been added so far
     */
    public static ArrayList<Order> allOrders;
    /**
     * ActivtyResultLauncher that manages return from other Activities
     */
    private ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
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
                } else if (result.getData() != null && result.getData().getSerializableExtra("d") != null) {
                    ArrayList<Donut> bag = (ArrayList<Donut>) result.getData().getSerializableExtra("d");
                    addDonut(bag);
                } else if (result.getData() != null && result.getData().getSerializableExtra("ord") != null) {
                    ArrayList<Order> ordersList = (ArrayList<Order>) result.getData().getSerializableExtra("ord");
                    allOrders.clear();
                    allOrders = ordersList;
                }
            }
        }
    });

    /**
     * Method that handles initial setup for MainActivity
     * @param savedInstanceState information from prior activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateButtons();
    }

    /**
     * Method that adds orders to existing list of orders
     * If existing list of orders is not yet populated, will initialize
     * @param toAdd Order to add to list
     */
    private void addToStoreOrders(Order toAdd) {
        if (allOrders == null) {
            allOrders = new ArrayList<>();
        }
        allOrders.add(toAdd);
    }

    /**
     * Method that adds Coffee item to basket
     * Coffee item is returned from coffee selection screen
     * @param toAdd Coffee object to add
     */
    private void addCoffee(Coffee toAdd) {
        if (itemsInBasket == null) {
            itemsInBasket = new ArrayList<>();
        }
        itemsInBasket.add(toAdd);
    }

    /**
     * Method that adds list of Donut items to basket
     * Donut items are from donut selection screen
     */
    private void addDonut(ArrayList<Donut> toAdd) {
        if (itemsInBasket == null) {
            itemsInBasket = new ArrayList<>();
        }
        itemsInBasket.addAll(toAdd);
    }

    /**
     * Method that instantiates buttons to interact with
     */
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

    /**
     * Method that opens the StoreOrdersActivity where all orders saved
     */
    public void openStoreOrdersActivity() {
        Intent storeIntent = new Intent(this, StoreOrdersActivity.class);
        startActivity(storeIntent);
    }

    /**
     * Method that opens the basket activity to view added items
     */
    public void openBasketActivity() {
        Intent basketIntent = new Intent(this, BasketActivity.class);
        activityResultLaunch.launch(basketIntent);
    }

    /**
     * Method that opens donut selection screen
     */
    public void openDonutActivity() {
        Intent donutIntent = new Intent(this, DonutActivity.class);
        activityResultLaunch.launch(donutIntent);
    }

    /**
     * Method that opens coffee selection screen
     */
    public void openCoffeeActivity() {
        Intent coffeeIntent = new Intent(this, CoffeeActivity.class);
        activityResultLaunch.launch(coffeeIntent);
    }
}