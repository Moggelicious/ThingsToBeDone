package com.example.morga.thingstobedone;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by Morga on 2017-04-13.
 */

public class Item {

    private String text;
    private String subText;

    public Item() {
        /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }
}
