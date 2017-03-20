package com.example.morga.thingstobedone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends AppCompatActivity {

private RecyclerView recView;
    private TTBDAdapter adapter;

    @Override
    public TTBDAdapter.TTBDHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new TTBDHolder(view);
    }
 
    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {
        ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.icon.setImageResource(item.getImageResId());
    }
 
    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = (RecyclerView)findViewById(R.id.rec_list);
        recView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TTBDAdapter(TTBDData.getListData(),this);
        recView.setAdapter(adapter);
    }


}
