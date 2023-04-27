package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Controls the coffee activity. Allows for manipulation of coffee items to add to basket.
 * @author Victoria Chen
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    /**
     * String array for quantities that can be ordered
     */
    String[] quantities = {"1", "2", "3", "4", "5"};
    /**
     * Group for size radio buttons, guarantees only one pressed at a time
     */
    private RadioGroup coffee_radio_group;
    /**
     * Instantiates radio buttons
     */
    private RadioButton selected, shrt, tall, grande, venti;
    /**
     * Instantiates check boxes
     */
    private CheckBox sCream, fVanilla, iCream, caramel, mocha;
    /**
     * Instantiates button
     */
    private AppCompatButton addToOrder;
    /**
     * Instantiates textview for subtotal
     */
    private TextView subtotal;

    /**
     * Runs on creation of the activity. Instantiates the views on the page and sets up for further
     * usage.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_select);
        // Link features
        sCream = (CheckBox) findViewById(R.id.sweet_cream);
        fVanilla = (CheckBox) findViewById(R.id.french_vanilla);
        iCream = (CheckBox) findViewById(R.id.irish_cream);
        caramel = (CheckBox) findViewById(R.id.caramel);
        mocha =  (CheckBox) findViewById(R.id.mocha);
        subtotal = (TextView) findViewById(R.id.coffee_subtotal);
        addToOrder = (AppCompatButton) findViewById(R.id.addCoffee);
        shrt = (RadioButton) findViewById(R.id.short_btn);
        tall = (RadioButton) findViewById(R.id.tall_btn);
        grande = (RadioButton) findViewById(R.id.grande_btn);
        venti = (RadioButton) findViewById(R.id.venti_btn);
        addToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), makeCoffee().toString() + " added to order", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("c", makeCoffee());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        // Set up radio group
        coffee_radio_group = (RadioGroup) findViewById(R.id.coffee_radio_group);
        coffee_radio_group.clearCheck();
        RadioButton defaultSize = (RadioButton) findViewById(R.id.short_btn);
        defaultSize.setChecked(true);
        // Set up spinner for quantities
        Spinner quantityList = (Spinner) findViewById(R.id.spinner2);
        quantityList.setOnItemSelectedListener(this);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, quantities);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quantityList.setAdapter(ad);
        setCheckBoxListeners();
        setRadioBtnListeners();
    }

    /**
     * Instantiate listeners for radio buttons
     */
    private void setRadioBtnListeners() {
        shrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
        tall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
        grande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
        venti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
    }

    /**
     * Instantiate listeners for check boxes
     */
    private void setCheckBoxListeners() {
        sCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
        fVanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
        iCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
        caramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
        mocha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTotal();
            }
        });
    }

    /**
     * Updates the subtotal at bottom of page
     */
    public void updateTotal() {
        subtotal.setText("Subtotal: $" + Double.toString(makeCoffee().itemPrice()));
    }

    /**
     * Creates a coffee item with details pulled from activity
     * @return Coffee object
     */
    public Coffee makeCoffee() {
        String size = getSize();
        ArrayList<String> addIns = getAddIns();
        Spinner quantityList = (Spinner) findViewById(R.id.spinner2);
        int numberOf = Integer.parseInt(quantityList.getSelectedItem().toString());
        Coffee item = new Coffee(size, addIns, numberOf);
        return item;
    }

    /**
     * Gets list of selected add ins
     * @return Arraylist of selected add ins
     */
    private ArrayList<String> getAddIns() {
        ArrayList<String> ans = new ArrayList<>();
        if (sCream.isChecked()) {
            ans.add("sweet cream");
        }
        if (fVanilla.isChecked()) {
            ans.add("french vanilla");
        }
        if (iCream.isChecked()) {
            ans.add("irish cream");
        }
        if (caramel.isChecked()) {
            ans.add("caramel");
        }
        if (mocha.isChecked()) {
            ans.add("mocha");
        }
        return ans;
    }

    /**
     * Gets size of coffee from activity
     * @return String size of coffee from activity
     */
    private String getSize() {
        int selectedID = coffee_radio_group.getCheckedRadioButtonId();
        selected = (RadioButton) findViewById(selectedID);
        return (String) selected.getText();
    }

    /**
     * Updates subtotal when new value selected in quantity spinner
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateTotal();
    }

    /**
     * Autogenerated, no function
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // autogenerated, ignore for now
    }
}