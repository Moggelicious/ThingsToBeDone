package com.example.morga.thingstobedone;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Morga on 2017-03-20.
 */

class TTBDHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private ImageView icon;
    private View container;

    public TTBDHolder(View itemView) {
        super(itemView);

        title = (TextView)itemView.findViewById(R.id.lbl_item_text);
        icon = (ImageView)itemView.findViewById(R.id.im_item_icon);
        //We'll need the container later on, when we add an View.OnClickListener
        container = itemView.findViewById(R.id.cont_item_root);
    }
}