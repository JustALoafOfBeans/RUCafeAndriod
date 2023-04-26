package com.example.rucafeandriod;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private static ArrayList<Donut> items; //need the data binding to each row of RecyclerView

    private static OnItemClickListener onClickListener;

    public DonutAdapter(Context context, ArrayList<Donut> items, OnItemClickListener listener) {
        this.context = context;
        this.items = items;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_donut, parent, false);

        return new ItemsHolder(view);
    }

    public static Donut getClicked(int position) {
        return items.get(position);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.flavor.setText(items.get(position).toStringMenu());
        holder.price.setText("$" + String.valueOf(items.get(position).itemPriceMenu()));
        holder.count.setText(String.valueOf(items.get(position).getQuantity()));
        holder.image.setImageResource(items.get(position).getImage());
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView flavor, price, count;
        private ImageView image;
        private Button add, rem;
        private ConstraintLayout parentLayout; //this is the row layout

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            flavor = itemView.findViewById(R.id.tv_flavor);
            price = itemView.findViewById(R.id.tv_price);
            count = itemView.findViewById(R.id.tv_count);
            image = itemView.findViewById(R.id.im_image);
            add = itemView.findViewById(R.id.btn_add);
            rem = itemView.findViewById(R.id.btn_remove);
            parentLayout = itemView.findViewById(R.id.layout_donut);
            setAddButtonOnClick(itemView); //register the onClicklistener for the button on each row.
            setRemButtonOnClick(itemView);
        }

        private int getClickedPosition(String clicked) {
            String donutClicked = clicked.substring(clicked.indexOf("(") + 1);
            donutClicked = donutClicked.substring(0, donutClicked.indexOf(")"));

            int pos = 0;
            for (Donut donut : items) {
                if (donut.getFlavor().equals(donutClicked)) {
                    return pos;
                }
                pos += 1;
            }
            return -1;
        }

        /**
         * Set the onClickListener for the button on each row.
         * Clicking on the button will create an AlertDialog with the options of YES/NO.
         * @param itemView
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getClickedPosition(flavor.getText().toString());
                    if (onClickListener != null) {
                        onClickListener.setAction("Add");
                        onClickListener.onItemClicked(index); // Process amounts
                        onClickListener.updateSubtotal(price.getText().toString()); // Update subtotal
                        count.setText(Integer.toString(onClickListener.returnCount())); // TOOD GAG
                    }
                }
            });
        }
        private void setRemButtonOnClick(@NonNull View itemView) {
            rem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getClickedPosition(flavor.getText().toString());
                    if (onClickListener != null) {
                        onClickListener.setAction("Remove");
                        onClickListener.onItemClicked(index);
                        onClickListener.updateSubtotal(price.getText().toString());
                        count.setText(Integer.toString(onClickListener.returnCount()));
                    }
                }
            });
        }
    }
}
