package com.example.morga.thingstobedone;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import com.example.morga.thingstobedone.ItemTouchHelperAdapter;
/**
 * Created by Morga on 2017-04-23.
 */

public class ListItemsAdapter extends RecyclerView.Adapter<ListItemsHolder> implements ItemTouchHelperAdapter {

     ArrayList<ListItem> myListItems;

    public ListItemsAdapter(ArrayList<ListItem> ListItems) {
        myListItems = ListItems;
    }


    @Override
    public ListItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater layoutInflater = LayoutInflater.from(view.this);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListItemsHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemsHolder holder, int position) {
        ListItem s = myListItems.get(position);
        holder.bindData(s);

    }


    @Override
    public  boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(myListItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(myListItems, i, i - 1);
            }
        }

        notifyItemMoved(fromPosition, toPosition);
        return true;

    }

    @Override
    public void onItemDismiss(int position) {

    }

    @Override
    public int getItemCount() {
        return myListItems.size();
    }
}
