package com.example.rucafeandriod;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {
    private ListView basketListView;
    public static ArrayList<MenuItem> currentBasket;
    private TextView subtotal, tax, total;
    private AppCompatButton deleteBtn, finalBtn;
    private MenuItem selectedItem;
    public static int nextOrderNum = 1;
    private ArrayAdapter<MenuItem> arrayAdapter;
    /**
     *  Constant for tax rate in NJ, applied to find tax and total costs
     */
    private static final double TAXNJ = 0.06625;
    /**
     * Use DF.format(value) to round value to two decimals places. Rounding
     * up or down is by basic convention. Returns a String.
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        basketListView = (ListView) findViewById(R.id.basket_list);
        subtotal = (TextView) findViewById(R.id.subtotal);
        tax = (TextView) findViewById(R.id.tax);
        total = (TextView) findViewById(R.id.total);
        deleteBtn = (AppCompatButton) findViewById(R.id.delete);
        finalBtn = (AppCompatButton) findViewById(R.id.finalize_button);
        currentBasket = MainActivity.itemsInBasket;
        displayBasket();
        instantiateOnClicks();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent (this, MainActivity.class);
        intent.putExtra("b", currentBasket);
        setResult(RESULT_OK,intent);
        finish();
    }

    private void instantiateOnClicks() {
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItem != null) {
                    raiseAlert();
                } else {
                    Toast.makeText(BasketActivity.this, "Please select an item to delete.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        finalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentBasket.isEmpty()) {
                    finish();
                } else {
                    Order newOrder = new Order(nextOrderNum, currentBasket);
                    nextOrderNum++;
                    Intent intent = new Intent (BasketActivity.this, MainActivity.class);
                    intent.putExtra("o", newOrder);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        // Maintain last item selected in selectedItem
        basketListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem listItem = (MenuItem) basketListView.getItemAtPosition(position);
                selectedItem = listItem;
            }
        });
    }

    private void raiseAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Delete item")
                .setMessage("Are you sure you want to delete " + selectedItem + " from the basket?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        currentBasket.remove(selectedItem);
                        Toast.makeText(BasketActivity.this, selectedItem.toString() + " removed.", Toast.LENGTH_SHORT).show();
                        selectedItem = null; //reset selectedItem
                        arrayAdapter.notifyDataSetChanged();
                        updateTotals();
                    }
                })
                .setNegativeButton(android.R.string.no, null) // do nothing when no pressed
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void displayBasket() {
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, currentBasket);
        basketListView.setAdapter(arrayAdapter);
        updateTotals();
    }

    private void updateTotals() {
        double sum = 0;
        for (MenuItem item : currentBasket){
            sum += item.itemPrice();
        }
        subtotal.setText("Subtotal: $" + DF.format(sum));
        double taxValue = Double.valueOf(DF.format(sum*TAXNJ));
        tax.setText("Tax: $" + taxValue);
        double totalValue = Double.valueOf(DF.format(sum + sum*TAXNJ));
        total.setText("Total: $" + totalValue);
    }
}