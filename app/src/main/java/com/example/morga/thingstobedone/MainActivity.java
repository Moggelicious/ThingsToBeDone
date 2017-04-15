package com.example.morga.thingstobedone;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.transition.Fade;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.List;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements ToDoAdapter.ItemClickCallback {


    public static final String TODO_ID = "Mogge";
    public static final String TODO_TASK = "MoggeId";
    public static final String BUNDLE_EXTRAS = "BUNDLE_EXTRAS";
    public static final String EXTRA_QUOTE = "EXTRA_QUOTE";
    public static final String EXTRA_ATTR = "EXTRA_ATTR";
    public static final String EXTRA_IMAGE_RES_ID = "EXTRA_IMAGE_RES_ID";

    private RecyclerView recyclerView;
    private ToDoAdapter adapter;
    private ArrayList listData;

    EditText editTextTask;
    EditText editTextDescription;
    ListView listViewItems;

    List<ToDoItem> todoItems;

    FloatingActionButton fabAddItem;
    DatabaseReference databaseTodoItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        databaseTodoItems = FirebaseDatabase.getInstance().getReference("todoItems");

        editTextTask = (EditText) findViewById(R.id.input_task);
        editTextDescription = (EditText) findViewById(R.id.input_description);
        listViewItems = (ListView) findViewById(R.id.listViewItems);

        fabAddItem = (FloatingActionButton) findViewById(R.id.btn_add_item);

        todoItems = new ArrayList<>();

        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), ToDoItem.class));
                setContentView(R.layout.todo_item);
                //addItem();
            }
        });
    }

    private void addItem() {
        String Task = editTextTask.getText().toString().trim();
        String Description = editTextDescription.getText().toString().trim();


        if (!TextUtils.isEmpty(Task)) {

            String id = databaseTodoItems.push().getKey();
            // creating todo object
            ToDoItem toDoItem = new ToDoItem(id, Task, Description);
            // saving the todo item
            databaseTodoItems.child(id).setValue(toDoItem);
            //setting editText to blank
            editTextTask.setText("");
            editTextDescription.setText("");
            Toast.makeText(this, "Todo task added", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this, "Please enter a task", Toast.LENGTH_LONG).show();
        }







        listData = (ArrayList) ToDoData.getListData();

        recyclerView = (RecyclerView)findViewById(R.id.recycler_list);
        //LayoutManager: Grid, Staggered
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ToDoAdapter(listData, this);
        recyclerView.setAdapter(adapter);
        adapter.setItemClickCallback(this);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        //fab = (FloatingActionButton)findViewById(R.id.btn_add_item);
        //fab.setOnClickListener(new View.OnClickListener(){
          //  @Override
            //public void onClick(View v) {
              //  addItemToList();
           // }
        //});




    }
    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        moveItem(viewHolder.getAdapterPosition(), target.getAdapterPosition());
                        return true;
                    }


                    @Override
                    public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                        deleteItem(viewHolder.getAdapterPosition());
                    }
                };
        return simpleItemTouchCallback;
    }

    private void addItemToList() {
        ListItem item = ToDoData.getRandomListItem();
        listData.add(item);
        adapter.notifyItemInserted(listData.indexOf(item));
    }

    private void moveItem(int oldPos, int newPos) {

        ListItem item = (ListItem) listData.get(oldPos);
        listData.remove(oldPos);
        listData.add(newPos, item);
        adapter.notifyItemMoved(oldPos, newPos);
    }

    private void deleteItem(final int position) {
        listData.remove(position);
        adapter.notifyItemRemoved(position);
    }


    @Override
    public void onItemClick(View v,int p) {
        ListItem item = (ListItem) listData.get(p);

        Intent i = new Intent(this, DetailActivity.class);

        Bundle extras = new Bundle();
        extras.putString(EXTRA_QUOTE, item.getTitle());
        extras.putString(EXTRA_ATTR, item.getSubTitle());
        extras.putInt(EXTRA_IMAGE_RES_ID, item.getImageResId());
        i.putExtra(BUNDLE_EXTRAS, extras);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(new Fade(Fade.IN));
            getWindow().setExitTransition(new Fade(Fade.OUT));

            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    new Pair<View, String>(v.findViewById(R.id.im_item_icon),
                            getString(R.string.transition_image)),
                    new Pair<View, String>(v.findViewById(R.id.lbl_item_text),
                            getString(R.string.transition_quote)),
                    new Pair<View, String>(v.findViewById(R.id.lbl_item_sub_title),
                            getString(R.string.transition_attribution))
            );

            ActivityCompat.startActivity(this, i, options.toBundle());
        } else {
            startActivity(i);
        }
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
        adapter.notifyDataSetChanged();

    }
}
