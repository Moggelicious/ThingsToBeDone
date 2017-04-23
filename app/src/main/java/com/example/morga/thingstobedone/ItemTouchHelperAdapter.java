package com.example.morga.thingstobedone;

/**
 * Created by Morga on 2017-04-23.
 */

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}