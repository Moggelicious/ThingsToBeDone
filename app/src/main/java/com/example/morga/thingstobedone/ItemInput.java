/*
package com.example.morga.thingstobedone;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * Created by Morga on 2017-04-16.
 *//*


public class ItemInput extends AppCompatActivity {

    public static final String TODO_ID = "Mogge";
    public static final String TODO_TASK = "MoggeId";

    EditText editTextTask;
    EditText editTextDescription;
    ListView listViewItems;

    List<Item> todoItems;

    DatabaseReference databaseTodoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(getApplicationContext(), ItemInput.class));

        Button buttonSendItem;
        databaseTodoItems = FirebaseDatabase.getInstance().getReference("todoItems");

        editTextTask = (EditText) findViewById(R.id.item_text);
        editTextDescription = (EditText) findViewById(R.id.item_sub_title);
        listViewItems = (ListView) findViewById(R.id.listViewItems);




        todoItems = new ArrayList<>();

        buttonSendItem = (Button) findViewById(R.id.btn_add_item);

        buttonSendItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                // the method is defined below
                // this method is actually performing the write operation
                Toast.makeText(ItemInput.this, "Hello olle!", Toast.LENGTH_SHORT).show();
                sendTask(view);
            }
        });

    }

    public void addItem (View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));


    }

    public void newItem(View view) {
        startActivity(new Intent(getApplicationContext(), ItemInput.class));

    }

        Log.d("DEBUG_RANDOM", "RANDOM number is:2");
        if (!TextUtils.isEmpty(Task)) {

            String id = databaseTodoItems.push().getKey();
            // creating todo object
            Item todoItem = new Item(id, Task, Description);
            // saving the todo item
            databaseTodoItems.child(id).setValue(todoItem);
            //setting editText to blank
            editTextTask.setText("");
            editTextDescription.setText("");
            Toast.makeText(this, "Todo task added", Toast.LENGTH_LONG).show();
            Log.d("DEBUG_RANDOM", "RANDOM number is:3");
        } else {
            Toast.makeText(this, "Please enter a task", Toast.LENGTH_LONG).show();
        }
    }
}



*/
