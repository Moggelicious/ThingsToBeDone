package com.example.morga.thingstobedone;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Morga on 2017-04-23.
 */

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private ItemTouchHelperAdapter mAdapter;


    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override

    public int getMovementFlags(RecyclerView myRecyclerView, RecyclerView.ViewHolder ListItemsHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;

        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);

    }

    @Override

    public boolean onMove(RecyclerView myRecyclerView, RecyclerView.ViewHolder ListItemsHolder,

                          RecyclerView.ViewHolder target) {

        mAdapter.onItemMove(ListItemsHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;

    }


    @Override

    public void onSwiped(RecyclerView.ViewHolder ListItemsHolder, int direction) {

        mAdapter.onItemDismiss(ListItemsHolder.getAdapterPosition());

    }


}

