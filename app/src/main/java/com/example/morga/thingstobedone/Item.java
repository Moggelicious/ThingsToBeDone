package com.example.morga.thingstobedone;

import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.IgnoreExtraProperties;
/**
 * Created by Morga on 2017-04-13.
 */
@IgnoreExtraProperties
public class Item extends AppCompatActivity {

    private String itemId;
    private String itemText;
    private String itemSubTitle;

    public Item() {

    }

    public Item(String itemId, String itemText, String itemSubTitle) {
        this.itemId = itemId;
        this.itemText = itemText;
        this.itemSubTitle = itemSubTitle;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemText() {
        return itemText;
    }

    public String getItemSubTitle() {
        return itemSubTitle;
    }
}
