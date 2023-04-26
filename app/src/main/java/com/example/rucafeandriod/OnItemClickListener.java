package com.example.rucafeandriod;

public interface OnItemClickListener {
    void onItemClicked(int position);

    void setAction(String btnAct);

    void updateSubtotal(String difference);
}
