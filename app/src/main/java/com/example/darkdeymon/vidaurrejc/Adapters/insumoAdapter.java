package com.example.darkdeymon.vidaurrejc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.insumo;
import com.example.darkdeymon.vidaurrejc.classRest.material;

import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 22/10/2016.
 */

public class insumoAdapter extends BaseAdapter {

    Context context;
    ArrayList<insumo> insumoArrayList;
    public insumoAdapter(Context context, ArrayList<insumo> itemArrayList){
        this.context=context;
        this.insumoArrayList=itemArrayList;
    }

    @Override
    public int getCount() {
        return insumoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return insumoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_insumo,parent,false);

        TextView insumo =(TextView)view.findViewById(R.id.insumo);
        insumo in=insumoArrayList.get(position);
        if(in!=null)
            insumo.setText("Nombre: "+in.getDecripcion());
        return view;
    }
}
