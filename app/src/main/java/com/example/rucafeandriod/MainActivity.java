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
    private ArrayList<MenuItem> orderDonut;
    private ArrayList<MenuItem> orderCoffee;
    private ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            // watched https://www.youtube.com/watch?v=Ke9PaRdMcgc
            if (result != null && result.getResultCode() == RESULT_OK) {
                if(result.getData() != null && result.getData().getSerializableExtra("c") != null) {
                    Coffee cup = (Coffee) result.getData().getSerializableExtra("c");
                    System.out.println(cup);
                }
            }
        }
    });

    private static int DONUTCODE = 123;
    private static int COFFEECODE = 456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instantiateButtons();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void addDonut(ActivityResult result) {
        // need to implement, ignore nonesense below

        //Intent data = result.getData();
        //System.out.println(data.toString());
        //Coffee data = (Coffee) getIntent().getSerializableExtra("coffee");
        //System.out.println(data);
        /**if(getActivity().getIntent().getExtras().getSerializable("coffee") != null) {
            Coffee data = (Coffee) getIntent().getExtras().getSerializable("coffee");
            System.out.println(data.toString());
        } */
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
        startActivity(basketIntent);
    }

    public void openDonutActivity() {
        Intent donutIntent = new Intent(this, DonutActivity.class);
        startActivity(donutIntent);
    }

    public void openCoffeeActivity() {
        Intent coffeeIntent = new Intent(this, CoffeeActivity.class);
        activityResultLaunch.launch(coffeeIntent);
    }
}