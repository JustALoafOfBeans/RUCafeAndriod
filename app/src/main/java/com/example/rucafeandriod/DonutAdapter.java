package com.example.rucafeandriod;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView donutName, donutCount;
        public Button donutButtonRemove, donutButtonAdd;

        public ViewHolder(View itemView) {
            super(itemView);

            donutName = (TextView) itemView.findViewById(R.id.item_name);
            donutCount = (TextView) itemView.findViewById(R.id.item_count);
            donutButtonRemove = (Button) itemView.findViewById(R.id.message_button);
            donutButtonAdd = (Button) itemView.findViewById(R.id.message_buttonAdd);
        }
    }

    private List<Donut> mDonuts;

    public DonutAdapter(List<Donut> donuts) {
        mDonuts = donuts;
    }

    @Override
    public DonutAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View donutView = inflater.inflate(R.layout.item_donut, parent, false);

        ViewHolder viewHolder = new ViewHolder(donutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DonutAdapter.ViewHolder holder, int position) {
        Donut donuts = mDonuts.get(position);

        TextView textView = holder.donutName;
        textView.setText(donuts.toStringMenu());
        TextView textViewCount = holder.donutCount;
        textViewCount.setText("0");
        Button button = holder.donutButtonAdd;
        button.setText("—");
        Button buttonAdd = holder.donutButtonRemove;
        buttonAdd.setText("+");

        button.setEnabled(true);
    }

    @Override
    public int getItemCount() {
        return mDonuts.size();
    }

}
