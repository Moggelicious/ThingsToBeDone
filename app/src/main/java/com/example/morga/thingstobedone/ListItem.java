package com.example.morga.thingstobedone;

/**
 * Created by Morga on 2017-03-20.
 */
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Morga on 2017-04-17.
 */

public class ListItem {

    public String title;
    public String subTitle;
    private int imageResId;
    private boolean favourite = false;

    public ListItem(){

    }

    public String getSubTitle() {

        return subTitle;
    }

    public void setSubTitle(String subTitle) {

        this.subTitle = subTitle;
    }

    public boolean isFavourite() {

        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public int getImageResId() {

        return imageResId;
    }

    public void setImageResId(int imageResId) {

        this.imageResId = imageResId;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public ListItem(String title, String subTitle) {
        this.title = title;
        this.subTitle = subTitle;

    }

    public String toString(){
        return this.title +this.subTitle;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("title", title);
        result.put("subTitle",subTitle);
        return result;

    }
}
