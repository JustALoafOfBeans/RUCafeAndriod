package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Activity class that manages events relating to the donut selection.
 * Activity called by and returns to MainActivity
 * @author Bridget Zhang
 */
public class DonutActivity extends AppCompatActivity {
    /**
     * Initialized list of all donut options (12 total)
     */
    private final ArrayList<Donut> donuts = new ArrayList<>();
    /**
     * Array that stores all images used for donut options
     */
    private final int[] itemImages = {R.drawable.yeast_chocolate, R.drawable.yeast_strawberry, R.drawable.yeast_boston, R.drawable.yeast_cookie, R.drawable.yeast_raspberry,
            R.drawable.cake_lemon, R.drawable.cake_blueberry, R.drawable.cake_chocolate, R.drawable.cake_matcha,
            R.drawable.hole_chocolate, R.drawable.hole_glazed, R.drawable.hole_pumpkin};
    /**
     * Text UI element displaying current subtotal
     */
    private TextView subtotalText;
    /**
     * Numerical value associated with current subtotal
     */
    private double subtotal;
    /**
     * Minimum number of donuts permitted per kind
     */
    private static final int MIN_DONUTS = 0;
    /**
     * Double format for decimal prices
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    /**
     * Method that initializes the activity window
     * @param savedInstanceState information from previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_select_recycle);
        RecyclerView rcview = findViewById(R.id.rvDonuts);
        subtotal = MIN_DONUTS;
        subtotalText = findViewById(R.id.subtotalText);
        setupMenuItems();
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
        AppCompatButton addToOrder = findViewById(R.id.orderButton);

        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Donuts added to order.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("d", makeDonut());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    /**
     * Method that returns a list of donuts in the order
     * Calls getOrder() from adapter to get relevant flavors
     * @return List of donuts
     */
    public ArrayList<Donut> makeDonut() {
        ArrayList<Donut> order = adapter.getOrder();
        return order;
    }

    /**
     * Method that sets up items in the menu view
     */
    private void setupMenuItems() {
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        for (int i = 0; i < itemNames.length; i++) {
            donuts.add(new Donut(itemNames[i], itemImages[i]));
        }
    }

    /**
     * Interface to handle adding and removing donuts
     */
    private final OnItemClickListener listener = new OnItemClickListener() {
        String action;
        int newCount;
        boolean boundError;

        /**
         * Method that handles onClick events for donut view
         * Called by Adapter
         * @param targeted index of targeted donut item
         */
        public void onItemClicked(int targeted) {
            boundError = false;
            if (action.equals("Add")) {
                Donut targetDonut = adapter.getClicked(targeted);
                newCount = targetDonut.getQuantity() + 1;
                targetDonut.setQuantity(newCount);
            } else if (action.equals("Remove")) {
                Donut targetDonut = adapter.getClicked(targeted);
                if (targetDonut.getQuantity() == MIN_DONUTS) {
                    newCount = MIN_DONUTS;
                    boundError = true;
                } else {
                    newCount = targetDonut.getQuantity() - 1;
                    targetDonut.setQuantity(newCount);
                }
            }
        }

        /**
         * Method that sets whether donut is being added or removed
         * Depends on whether "+" or "-" button pressed
         * @param btnAct String that describes action
         */
        public void setAction(String btnAct) {
            action = btnAct;
        }

        /**
         * Method that updates the subtotal based on additions/removals
         * @param changeStr Amount to change by, negative if removal
         */
        public void updateSubtotal(String changeStr) {
            if (!boundError) {
                Double changeVal = Double.parseDouble(changeStr.substring(1));
                if (action.equals("Add")) {
                    subtotal += changeVal;
                } else if (action.equals("Remove")) {
                    subtotal -= changeVal;
                }
                subtotal = Double.valueOf(DF.format(subtotal));
                subtotalText.setText("Subtotal: $" + subtotal);
            }
        }

        /**
         * Method that returns the updated count to the Adapter
         * @return New quantity of target item as an integer
         */
        public int returnCount() {
            return newCount;
        }
    };

    // Create adapter
    DonutAdapter adapter = new DonutAdapter(this, donuts, listener);

}
