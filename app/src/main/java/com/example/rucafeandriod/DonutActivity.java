package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


public class DonutActivity extends AppCompatActivity {

    private ImageButton cakeChocolate, cakeBlueberry, cakeLemon, cakeMatcha;
    private ImageButton yeastCookie, yeastChocolate, yeastRaspberry, yeastStrawberry, yeastBoston;
    private ImageButton holeChocolate, holePumpkin, holeGlazed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_select);
        String[] quantities = {"1", "2", "3", "4", "5"};

        // Set up image buttons
        cakeChocolate = (ImageButton) findViewById(R.id.cakechocolateButton);
        cakeBlueberry = (ImageButton) findViewById(R.id.cakeblueberryButton);
        cakeLemon = (ImageButton) findViewById(R.id.cakelemonButton);
        cakeMatcha = (ImageButton) findViewById(R.id.cakematchaButton);
        yeastCookie = (ImageButton) findViewById(R.id.yeastcookieButton);
        yeastChocolate = (ImageButton) findViewById(R.id.yeastchocolateButton);
        yeastRaspberry = (ImageButton) findViewById(R.id.yeastraspberryButton);
        yeastStrawberry = (ImageButton) findViewById(R.id.yeaststrawberryButton);
        yeastBoston = (ImageButton) findViewById(R.id.yeastbostonButton);
        holeChocolate = (ImageButton) findViewById(R.id.holechocolateButton);
        holePumpkin = (ImageButton) findViewById(R.id.holepumpkinButton);
        holeGlazed = (ImageButton) findViewById(R.id.holeglazedButton);

        // Set up quantity spinner
        Spinner quantityList = (Spinner) findViewById(R.id.quantitySpinner);
        quantityList.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quantities);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantityList.setAdapter(ad);

        // Set up subtotal text

        // Set up add to cart button
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Yahoobruh
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Yoink
    }
}
