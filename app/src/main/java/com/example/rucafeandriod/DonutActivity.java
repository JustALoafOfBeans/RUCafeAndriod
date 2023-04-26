package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;


public class DonutActivity extends AppCompatActivity {
    // Donut things
    private ArrayList<Donut> donuts = new ArrayList<>();
    private int[] itemImages = {R.drawable.yeast_chocolate, R.drawable.yeast_strawberry, R.drawable.yeast_boston, R.drawable.yeast_cookie, R.drawable.yeast_raspberry,
            R.drawable.cake_lemon, R.drawable.cake_blueberry, R.drawable.cake_chocolate, R.drawable.cake_matcha,
            R.drawable.hole_chocolate, R.drawable.hole_glazed, R.drawable.hole_pumpkin};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_select_recycle);
        RecyclerView rcview = findViewById(R.id.rvDonuts);
        setupMenuItems(); //add the list of items to the ArrayList
        // adapter = new DonutAdapter(this, donuts); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupMenuItems() {
        /*
         * Item names are defined in a String array under res/string.xml.
         * Your item names might come from other places, such as an external file, or the database
         * from the backend.
         */
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        /* Add the items to the ArrayList.
         * Note that I use hardcoded prices for demo purpose. This should be replace by other
         * data sources.
         */
        for (int i = 0; i < itemNames.length; i++) {
            donuts.add(new Donut(itemNames[i], itemImages[i]));
        }
    }

    private OnItemClickListener listener = new OnItemClickListener() {
        public void onItemClicked(int added) {
            //do whatever you want with donut
            Donut addedDonut = adapter.getClicked(added);
            System.out.println("Added " + addedDonut.getFlavor());
        }
    };

    //creation of adapter
    DonutAdapter adapter = new DonutAdapter(this, donuts, listener);

}
