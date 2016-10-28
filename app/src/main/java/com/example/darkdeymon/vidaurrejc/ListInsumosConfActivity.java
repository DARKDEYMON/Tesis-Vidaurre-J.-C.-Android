package com.example.darkdeymon.vidaurrejc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.darkdeymon.vidaurrejc.Adapters.salidaInsumoAdapter;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.salidaInsumoRest;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.item;
import com.example.darkdeymon.vidaurrejc.classRest.salidaInsumo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListInsumosConfActivity extends AppCompatActivity {

    private ListView mlistInsumosConf;

    private AccesData mAccesData;
    private salidaInsumoAdapter mSalidaInsumoAdapter;
    private ArrayList<salidaInsumo> mListSalidaInsumo;

    boolean volver=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_insumos_conf);
        startParams();
    }
    public void startParams(){
        mAccesData = StaticValues.getLogetInfo(this);
        mlistInsumosConf =(ListView)findViewById(R.id.listInsumosConf);

        salidaInsumoRest lis= new salidaInsumoRest(mAccesData.getUsername(),mAccesData.getPassword(),this);
        lis.execute();
        try {
            mListSalidaInsumo = lis.get();
            Log.e("resultado",lis.get().toString());
            mSalidaInsumoAdapter = new salidaInsumoAdapter(this,mListSalidaInsumo);

            mlistInsumosConf.setAdapter(mSalidaInsumoAdapter);
            mlistInsumosConf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    itemClick(parent,position);
                }
            });

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public void itemClick(AdapterView<?> parent, int position){
        salidaInsumo itemres=(salidaInsumo)parent.getAdapter().getItem(position);
        Log.e("id",itemres.getId());

        Intent i = new Intent(ListInsumosConfActivity.this,SendInsumosConfActivity.class);
        i.putExtra(StaticValues.salidaInsumoActivity,salidaInsumo.getJsonSalidaInsumo(itemres));
        volver= true;
        startActivity(i);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if(volver)
            finish();
    }
}
