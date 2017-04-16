package com.example.morga.thingstobedone;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by Morga on 2017-04-13.
 */
@IgnoreExtraProperties
public class ToDoItem extends AppCompatActivity {

    private String itemId;
    private String itemTask;
    private String itemDescription;

    public ToDoItem() {


    }

    public ToDoItem(String itemId, String itemTask, String itemDescription) {
        this.itemId = itemId;
        this.itemTask = itemTask;
        this.itemDescription = itemDescription;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTask() {
        return itemTask;
    }

    public String getDescription() {
        return itemDescription;
    }
}
