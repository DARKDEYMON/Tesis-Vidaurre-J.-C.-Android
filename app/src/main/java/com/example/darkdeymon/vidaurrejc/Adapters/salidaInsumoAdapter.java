package com.example.darkdeymon.vidaurrejc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.darkdeymon.vidaurrejc.R;
import com.example.darkdeymon.vidaurrejc.classRest.material;
import com.example.darkdeymon.vidaurrejc.classRest.salidaInsumo;

import java.util.ArrayList;

/**
 * Created by DARKDEYMON on 27/10/2016.
 */

public class salidaInsumoAdapter extends BaseAdapter {

    Context context;
    ArrayList<salidaInsumo> salidaInsumoArrayList;
    public salidaInsumoAdapter(Context context, ArrayList<salidaInsumo> itemArrayList){
        this.context=context;
        this.salidaInsumoArrayList=itemArrayList;
    }
    @Override
    public int getCount() {
        return salidaInsumoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return salidaInsumoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layoud_salida_insumo,parent,false);

        TextView insumo =(TextView)view.findViewById(R.id.insumo);
        TextView item =(TextView)view.findViewById(R.id.item);
        TextView cantidad =(TextView)view.findViewById(R.id.cantidad);

        salidaInsumo m= salidaInsumoArrayList.get(position);
        if(m!=null){
            insumo.setText(insumo.getText() +" "+m.getInsumos());
            item.setText(item.getText()+" "+m.getItem());
            cantidad.setText(cantidad.getText()+" "+m.getCantidad());
        }
        return view;
    }
}
