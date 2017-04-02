package com.example.morga.thingstobedone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ToDoAdapter.ItemClickCallback {

    public static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    public static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    public static final String EXTRA_ATTR = "EXTRA_ATTR";

    private RecyclerView recyclerView;
    private ToDoAdapter adapter;
    private ArrayList listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listData = (ArrayList) ToDoData.getListData();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_list);
        //LayoutManager: Grid, Staggered
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ToDoAdapter(ToDoData.getListData(), this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);

    }

    @Override
    public void onItemClick(int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_QUOTE, item.getTitle());
        extras.putString(EXTRA_ATTR, item.getSubTitle());

        i.putExtra(BUNDLE_EXTRAS, extras);
        startActivity(i);
    }

    @Override
    public void onSecondaryIconClick(int p) {
        ListItem item = (ListItem) listData.get(p);
        //update data
        if (item.isFavourite()) {
            item.setFavourite(false);
        }else {
            item.setFavourite(true);
        }
        //pass new data to adapter and update
        adapter.setListData(listData);
        adapter.notifyDataSetChanged();

    }
}
