package com.example.morga.thingstobedone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Morga on 2017-03-20.
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoHolder> {

    private List<ListItem> listData;
    private LayoutInflater inflater;

    public ToDoAdapter (List<ListItem> listdata, Context c){
        this.inflater = LayoutInflater.from(c);
        this.listData = listdata;
    }


    @Override
    public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ToDoHolder(view);
    }

    @Override
    public void onBindViewHolder(ToDoHolder holder, int position) {
        ListItem item = listData.get(position);
        holder.title.setText(item.getTitle());
        holder.icon.setImageResource(item.getImageResId());

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class ToDoHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView icon;
        private View container;

        public ToDoHolder(View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.label_item_text);
            icon = (ImageView)itemView.findViewById(R.id.image_item_icon);
            container = itemView.findViewById(R.id.container_item_root);
        }
    }
}
