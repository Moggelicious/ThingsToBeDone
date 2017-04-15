package com.example.morga.thingstobedone;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by Morga on 2017-04-13.
 */

public class ToDoItem extends AppCompatActivity {

    private String itemId;
    private String task;
    private String description;

    public ToDoItem() {


    }

    public ToDoItem(String itemId, String task, String description) {
        this.itemId = itemId;
        this.task = task;
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public String getTask() {
        return task;
    }

    public String getDescription() {
        return description;
    }
}
