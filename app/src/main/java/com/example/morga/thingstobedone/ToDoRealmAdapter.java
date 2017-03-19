package com.example.morga.thingstobedone;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import io.realm.RealmViewHolder;
import io.realm.internal.Context;

/**
 * Created by Morga on 2017-03-19.
 */

public class ToDoRealmAdapter
        extends RealmBasedRecyclerViewAdapter<TodoItem, ToDoRealmAdapter.ViewHolder> {

    public class ViewHolder extends RealmViewHolder {

        public TextView todoTextView;
        public ViewHolder(FrameLayout container) {
            super(container);
            this.todoTextView = (TextView) container.findViewById(R.id.todo_text_view);
        }
    }

    public ToDoRealmAdapter(
            Context context,
            RealmResults<TodoItem> realmResults,
            boolean automaticUpdate,
            boolean animateResults) {
        super(context, realmResults, automaticUpdate, animateResults);
    }

    //This creates the viewHolder with the view.
    @Override
    public ViewHolder onCreateRealmViewHolder(ViewGroup viewGroup, int viewType) {
        View v = inflater.inflate(R.layout.to_do_item_view, viewGroup, false);
        ViewHolder vh = new ViewHolder((FrameLayout) v);
        return vh;
    }

    //This binds the story to the view.
    @Override
    public void onBindRealmViewHolder(ViewHolder viewHolder, int position) {
        final TodoItem toDoItem = realmResults.get(position);
        viewHolder.todoTextView.setText(toDoItem.getDescription());
        viewHolder.itemView.setBackgroundColor(
                COLORS[(int) (toDoItem.getId() % COLORS.length)]
        );
    }
    private static final int[] COLORS = new int[] {
            Color.argb(255, 28, 160, 170),
            Color.argb(255, 99, 161, 247),
            Color.argb(255, 13, 79, 139),
            Color.argb(255, 89, 113, 173),
            Color.argb(255, 200, 213, 219),
            Color.argb(255, 99, 214, 74),
            Color.argb(255, 205, 92, 92),
            Color.argb(255, 105, 5, 98)
    };
}