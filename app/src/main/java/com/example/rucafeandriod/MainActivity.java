package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton coffeeButton;
    private ImageButton donutButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coffeeButton = (ImageButton) findViewById(R.id.coffeeButton);
        donutButton = (ImageButton) findViewById(R.id.donutButton);
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoffeeActivity();
            }
        });

        donutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDonutActivity();
            }
        });
    }

    public void openCoffeeActivity() {
        Intent coffeeIntent = new Intent(this, CoffeeActivity.class);
        startActivity(coffeeIntent);
    }

    public void openDonutActivity() {
        Intent donutIntent = new Intent(this, DonutActivity.class);
        startActivity(donutIntent);
    }
}