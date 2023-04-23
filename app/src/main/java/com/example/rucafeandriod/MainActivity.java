package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton coffeeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coffeeButton = (ImageButton) findViewById(R.id.coffeeButton);
        coffeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoffeeActivity();
            }
        });
    }

    public void openCoffeeActivity() {
        Intent coffeeIntent = new Intent(this, CoffeeActivity.class);
        startActivity(coffeeIntent);
    }
}