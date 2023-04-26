package com.example.rucafeandriod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] quantities = {"1", "2", "3", "4", "5"};
    private RadioGroup coffee_radio_group;
    private RadioButton selected, shrt, tall, grande, venti;
    private CheckBox sCream, fVanilla, iCream, caramel, mocha;
    private AppCompatButton addToOrder;
    private TextView subtotal;

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

    public void updateTotal() {
        subtotal.setText("Subtotal: $" + Double.toString(makeCoffee().itemPrice()));
    }

    public Coffee makeCoffee() {
        String size = getSize();
        ArrayList<String> addIns = getAddIns();
        Spinner quantityList = (Spinner) findViewById(R.id.spinner2);
        int numberOf = Integer.parseInt(quantityList.getSelectedItem().toString());
        Coffee item = new Coffee(size, addIns, numberOf);
        return item;
    }

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

    private String getSize() {
        int selectedID = coffee_radio_group.getCheckedRadioButtonId();
        selected = (RadioButton) findViewById(selectedID);
        return (String) selected.getText();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateTotal();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // autogenerated, ignore for now
    }
}