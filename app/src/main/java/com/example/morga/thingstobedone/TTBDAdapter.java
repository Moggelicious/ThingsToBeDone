package com.example.morga.thingstobedone;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import java.util.List;

/**
 * Created by Morga on 2017-03-19.
 */

public class TTBDAdapter extends RecyclerView.Adapter<TTBDAdapter.TTBDHolder>{

    private List<ListItem> listData;
    private LayoutInflater inflater;

    public TTBDAdapter(List<ListItem>listData, Context c){
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

}
