package com.example.darkdeymon.vidaurrejc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.salidaInsumo;
import com.example.darkdeymon.vidaurrejc.classRest.salidaMaterial;

import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 28/10/2016.
 */

public class salidaMaterialAdapter extends BaseAdapter {
    Context context;
    ArrayList<salidaMaterial> salidaMaterialArrayList;
    public salidaMaterialAdapter(Context context, ArrayList<salidaMaterial> itemArrayList){
        this.context=context;
        this.salidaMaterialArrayList=itemArrayList;
    }
    @Override
    public int getCount() {
        return salidaMaterialArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return salidaMaterialArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_salida_material,parent,false);

        TextView material =(TextView)view.findViewById(R.id.material);
        TextView item =(TextView)view.findViewById(R.id.item);
        TextView cantidad =(TextView)view.findViewById(R.id.cantidad);

        salidaMaterial m= salidaMaterialArrayList.get(position);
        if(m!=null){
            material.setText(material.getText() +" "+m.getMaterial());
            item.setText(item.getText()+" "+m.getItem());
            cantidad.setText(cantidad.getText()+" "+m.getCantidad());
        }
        return view;
    }
}
