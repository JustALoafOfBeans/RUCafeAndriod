package com.example.rucafeandriod;

/**
 * OnItemClickListener interface that is used by DonutAdapter
 */
public interface OnItemClickListener {
    void onItemClicked(int position);

    void setAction(String btnAct);

    void updateSubtotal(String difference);

    int returnCount();
}
