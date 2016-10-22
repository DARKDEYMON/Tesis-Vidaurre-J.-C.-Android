package com.example.darkdeymon.vidaurrejc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.item;
import com.example.darkdeymon.vidaurrejc.classRest.material;

import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 22/10/2016.
 */

public class materialAdapter extends BaseAdapter {
    Context context;
    ArrayList<material> materialArrayList;
    public materialAdapter(Context context, ArrayList<material> itemArrayList){
        this.context=context;
        this.materialArrayList=itemArrayList;
    }

    @Override
    public int getCount() {
        return materialArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return materialArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_material,parent,false);

        TextView material =(TextView)view.findViewById(R.id.material);

        material m= materialArrayList.get(position);
        if(m!=null){
            material.setText("Nombre:"+m.getDecripcion());
        }
        return view;
    }
}
