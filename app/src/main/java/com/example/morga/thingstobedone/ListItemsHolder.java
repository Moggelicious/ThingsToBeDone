package com.example.morga.thingstobedone;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Morga on 2017-04-23.
 */

public  class ListItemsHolder extends RecyclerView.ViewHolder{
    public TextView titleTextView;
    public TextView subTitleTextView;


    public ListItemsHolder(View itemView){
        super(itemView);
        titleTextView = (TextView) itemView.findViewById(R.id.lbl_item_text);
        subTitleTextView = (TextView) itemView.findViewById(R.id.lbl_item_sub_title);
    }

    public void bindData(ListItem s){
        titleTextView.setText(s.getTitle());
        subTitleTextView.setText(s.getSubTitle());
    }
}