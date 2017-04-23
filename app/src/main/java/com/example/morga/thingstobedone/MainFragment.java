/*
package com.example.morga.thingstobedone;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

*/
/**
 * Created by Morga on 2017-04-23.
 *//*


public class MainFragment extends Fragment {

    public MainFragment() {

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView myRecyclerView = (RecyclerView)view.findViewById(R.id.recycler_list);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        ListItemsAdapter myAdapter = new ListItemsAdapter();
        myRecyclerView.setAdapter(myAdapter);


        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(myAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(myRecyclerView);
    }


}
*/
