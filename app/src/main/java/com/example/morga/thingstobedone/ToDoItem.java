package com.example.morga.thingstobedone;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Morga on 2017-03-19.
 */

public class ToDoItem extends RealmObject {


    @PrimaryKey
    private long id;
    private String description;

    //getter for sending loing id
    public long getId() {
        return id;
    }

    //setter for making long id
    public void setId(long id) {
        this.id = id;
    }

    //getter for sending string description
    public String getDescription() {
        return description;
    }

    //setter for making string description
    public void setDescription(String description) {
        this.description = description;
    }

}
