package com.example.morga.thingstobedone;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;


public class MainActivity extends AppCompatActivity{

    Boolean flag=false;
    private final String TAG = "ListActivity";
    DatabaseReference myDataBase;
    DatabaseReference myListItemRef;
    private RecyclerView myRecyclerView;
    ListItemsAdapter myAdapter;
    private ArrayList<ListItem> myListItems;
    private ItemTouchHelper myItemTouchHelper;


    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.mainLayout);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }




        myDataBase = FirebaseDatabase.getInstance().getReference();
        myListItemRef = myDataBase.child("ListItems");
        myListItems = new ArrayList<>();
        myRecyclerView = (RecyclerView) findViewById(R.id.recycler_list);
        //myRecyclerView.addItemDecoration(new DividerItemDecoration(Activity().LinearLayoutManager.VERTICAL));
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        myAdapter = new ListItemsAdapter(myListItems);
        myRecyclerView.setAdapter(myAdapter);

        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(myAdapter);
        myItemTouchHelper = new ItemTouchHelper(callback);
        myItemTouchHelper.attachToRecyclerView(myRecyclerView);

        updateUI();

        myListItemRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG + "Added", dataSnapshot.getValue(ListItem.class).toString());


                fetchData(dataSnapshot);
            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG + "Changed", dataSnapshot.getValue(ListItem.class).toString());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG + "Removed", dataSnapshot.getValue(ListItem.class).toString());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.d(TAG + "Moved", dataSnapshot.getValue(ListItem.class).toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG + "Cancelled", databaseError.toString());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




/*
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

*/
    public void showItem(View view) {




        LayoutInflater myInflater = LayoutInflater.from(this);
        View alertLayout = myInflater.inflate(R.layout.show_item, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Work in progress", Snackbar.LENGTH_LONG);

                snackbar.show();

            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();
    }


    public void newItem(View view) {

        final String key = FirebaseDatabase.getInstance().getReference().child("ListItems").push().getKey();
        LayoutInflater myInflater = LayoutInflater.from(this);
        View alertLayout = myInflater.inflate(R.layout.new_item, null);

        final EditText itemText = (EditText) alertLayout.findViewById(R.id.item_text);
        final EditText itemSubTitle = (EditText) alertLayout.findViewById(R.id.item_sub_title);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertLayout);


        final EditText userInputTitle = (EditText) alertLayout.findViewById(R.id.item_text);
        final EditText userInputSubTitle = (EditText) alertLayout.findViewById(R.id.item_sub_title);


        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Cancelled", Snackbar.LENGTH_LONG);

                snackbar.show();

            }
        });

        alert.setPositiveButton("Done", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int id) {


                String title = userInputTitle.getText().toString().trim();
                String subTitle = userInputSubTitle.getText().toString().trim();


                if (userInputTitle.getText().toString().isEmpty()) {


                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Doing nothing is doing something", Snackbar.LENGTH_LONG);
                    snackbar.show();
                    return;
                } else {

                    ListItem listItem = new ListItem();

                    listItem.setTitle(title);
                    listItem.setSubTitle(subTitle);
                    Map<String, Object> ItemValues = listItem.toMap();
                    Map<String, Object> childUpdates = new HashMap<>();
                    childUpdates.put("/ListItems/" + key, ItemValues);
                    FirebaseDatabase.getInstance().getReference().updateChildren(childUpdates);

                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "New thing to do added", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
        //hideSoftKeyboard(this);

        Log.d("TAG", "key" + key);


    }


    public void ActionDelete(MenuItem action_delete) {
        FirebaseDatabase.getInstance().getReference().child("ListItems").removeValue();
        myListItems.clear();
        myAdapter.notifyDataSetChanged();
        Snackbar snackbar = Snackbar
                .make(coordinatorLayout, "Everything is gone", Snackbar.LENGTH_LONG);
        snackbar.show();
    }


    private void fetchData(DataSnapshot dataSnapshot) {
        ListItem listItem = dataSnapshot.getValue(ListItem.class);
        myListItems.add(listItem);
        updateUI();
    }

    private void updateUI() {
        myAdapter = new ListItemsAdapter(myListItems);
        myRecyclerView.setAdapter(myAdapter);
    }

    public void change_image(View v) {
        ImageView iv = (ImageView) findViewById(R.id.im_item_icon_secondary);
        //use flag to change image
        if (flag == false) {
            iv.setImageResource(R.drawable.ic_star_border_black_24dp);
            flag = true;
        } else {
            iv.setImageResource(R.drawable.ic_star_black_24dp);
            flag = false;
        }

    }
}


