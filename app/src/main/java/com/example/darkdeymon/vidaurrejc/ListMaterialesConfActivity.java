package com.example.darkdeymon.vidaurrejc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.darkdeymon.vidaurrejc.Adapters.salidaInsumoAdapter;
import com.example.darkdeymon.vidaurrejc.Adapters.salidaMaterialAdapter;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.salidaInsumoRest;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.salidaMaterialRest;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.salidaInsumo;
import com.example.darkdeymon.vidaurrejc.classRest.salidaMaterial;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListMaterialesConfActivity extends AppCompatActivity {

    private ListView mlistMaterialConf;

    private AccesData mAccesData;
    private salidaMaterialAdapter mSalidaMaterialAdapter;
    private ArrayList<salidaMaterial> mListSalidaMaterial;

    boolean volver=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_materiales_conf);
        startParams();
    }
    public void startParams(){
        mAccesData = StaticValues.getLogetInfo(this);
        mlistMaterialConf =(ListView)findViewById(R.id.listMaterialConf);

        salidaMaterialRest lis= new salidaMaterialRest(mAccesData.getUsername(),mAccesData.getPassword(),this);
        lis.execute();
        try {
            mListSalidaMaterial = lis.get();
            Log.e("resultado",lis.get().toString());
            mSalidaMaterialAdapter = new salidaMaterialAdapter(this,mListSalidaMaterial);

            mlistMaterialConf.setAdapter(mSalidaMaterialAdapter);
            mlistMaterialConf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        salidaMaterial itemres=(salidaMaterial) parent.getAdapter().getItem(position);
        Log.e("id",itemres.getId());


        Intent i = new Intent(ListMaterialesConfActivity.this,SendMaterialesConfActivity.class);
        i.putExtra(StaticValues.salidaInsumoActivity,salidaMaterial.getJsonSalidaMaterial(itemres));
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
