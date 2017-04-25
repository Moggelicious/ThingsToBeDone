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
    private ItemClickCallback itemClickCallback;
    ArrayList<ListItem> myListItems;

    public ListItemsAdapter(ArrayList<ListItem> ListItems) {
        myListItems = ListItems;
    }
    public interface ItemClickCallback {
        void onItemClick(View v, int p);
        void onSecondaryIconClick(int p);
    }


    @Override
    public ListItemsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater layoutInflater = LayoutInflater.from(view.this);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ListItemsHolder(view);
    }

    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;
                    }

                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        onItemDismiss(viewHolder.getAdapterPosition());
                        myListItems.remove(myListItems);
                    }
                };
        return simpleItemTouchCallback;
    }



    @Override
    public void onBindViewHolder(ListItemsHolder holder, int position) {
        ListItem s = myListItems.get(position);
        holder.bindData(s);

    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(myListItems, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(myListItems, i, i - 1);
            }
        }

        return true;

    }

    @Override
    public void onItemDismiss(final int position) {
        myListItems.remove(myListItems);

    }

    @Override
    public int getItemCount() {
        return myListItems.size();
    }


}
