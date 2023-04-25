package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class DonutActivity extends AppCompatActivity {

    /*private ImageButton cakeChocolate, cakeBlueberry, cakeLemon, cakeMatcha;
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
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quantities);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantityList.setAdapter(ad);

        // Set up subtotal text

        // Set up add to cart button
    }*/

    ArrayList<Donut> donuts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_select_recycle);

        RecyclerView rvDonuts = (RecyclerView) findViewById(R.id.rvDonuts);

        donuts = createDonutsList();
        // Create adapter passing in the sample user data
        DonutAdapter adapter = new DonutAdapter(donuts);
        rvDonuts.setAdapter(adapter);
        // Set layout manager to position the items
        rvDonuts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

    // Creates list of each type with 0 quantity at start
    public static ArrayList<Donut> createDonutsList() {
        ArrayList<Donut> donuts = new ArrayList<Donut>();

        donuts.add(new Donut("Yeast Donut", "Chocolate glaze", 0));
        donuts.add(new Donut("Yeast Donut", "Strawberry Glaze", 0));
        donuts.add(new Donut("Yeast Donut", "Boston Cream", 0));
        donuts.add(new Donut("Yeast Donut", "Cookies & Cream", 0));
        donuts.add(new Donut("Yeast Donut", "Raspberry Glaze", 0));
        donuts.add(new Donut("Cake Donut", "Lemon", 0));
        donuts.add(new Donut("Cake Donut", "Blueberry", 0));
        donuts.add(new Donut("Cake Donut", "Chocolate", 0));
        donuts.add(new Donut("Cake Donut", "Matcha", 0));
        donuts.add(new Donut("Donut Hole", "Chocolate", 0));
        donuts.add(new Donut("Donut Hole", "Glazed", 0));
        donuts.add(new Donut("Donut Hole", "Pumpkin", 0));

        return donuts;
    }
}
