package com.example.darkdeymon.vidaurrejc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.item;

import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 16/10/2016.
 */

public class itemAdapter extends BaseAdapter {

    Context context;
    ArrayList<item> itemArrayList;
    public itemAdapter(Context context, ArrayList<item> itemArrayList){
        this.context=context;
        this.itemArrayList=itemArrayList;
    }
    @Override
    public int getCount() {
        return this.itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_item,parent,false);

        TextView proyecto =(TextView)view.findViewById(R.id.proyecto);
        TextView item =(TextView)view.findViewById(R.id.item);
        item it = this.itemArrayList.get(position);

        if(it!=null){
            proyecto.setText("Proyecto: "+it.getProyecto_name());
            item.setText("Item: "+it.getDescripcion());
        }

        return view;
    }
}
