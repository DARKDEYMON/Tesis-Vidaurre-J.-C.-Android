package com.example.darkdeymon.vidaurrejc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;

import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;

public class seleccionar extends AppCompatActivity implements View.OnClickListener{

    private Button mButton;
    private TextView mTextView;
    private Button mListaItem;
    private Button mReqMateriles;
    private Button mReqInsumos;

    private Button mConfMateriales;
    private Button mConfinsumos;

    private AppCompatActivity mThis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar);

        mButton =(Button)findViewById(R.id.informe);
        mThis = this;

        mTextView = (TextView)findViewById(R.id.username);
        mTextView.setText(getString(R.string.bienvenido)+" : "+(StaticValues.getLogetInfo(this)!=null ? StaticValues.getLogetInfo(this).getUsername() : "").toUpperCase());

        mListaItem = (Button)findViewById(R.id.item_informes_ged);
        mListaItem.setOnClickListener(this);

        mReqMateriles =(Button)findViewById(R.id.item_requerimientos_material);
        mReqMateriles.setOnClickListener(this);

        mReqInsumos =(Button)findViewById(R.id.item_requerimientos_insumos);
        mReqInsumos.setOnClickListener(this);

        mConfMateriales =(Button)findViewById(R.id.conf_materiales);
        mConfMateriales.setOnClickListener(this);

        mConfinsumos =(Button)findViewById(R.id.conf_insumos);
        mConfinsumos.setOnClickListener(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mButton.setText(StaticValues.getLogetInfo(mThis)!=null ? StaticValues.getLogetInfo(mThis).getUsername() : "");
            }
        });
    }


    public void logout(){
        SharedPreferences mPrefs = getSharedPreferences(StaticValues.GeneralPreferences,MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(StaticValues.SharedPreferencesAuth, "");
        prefsEditor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seleccionar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_salir:
                Log.e("Salir","Salir Precionado");
                logout();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        switch (id){
            case R.id.item_informes_ged:
                listaItemInformes();
                break;
            case R.id.item_requerimientos_material:
                listaItemMateriales();
                break;
            case R.id.item_requerimientos_insumos:
                listaItemInsumos();
                break;
            case R.id.conf_materiales:

                break;
            case  R.id.conf_insumos:
                listaConfInsumos();
                break;
        }
    }
    public void listaItemInformes(){
        Intent i = new Intent(seleccionar.this,ListItemReporteActivity.class);
        startActivity(i);
    }
    public void listaItemMateriales(){
        Intent i= new Intent(seleccionar.this,ListItemReqMaterialActivity.class);
        startActivity(i);
    }
    public void listaItemInsumos(){
        Intent i = new Intent(seleccionar.this,ListItemReqInsumosActivity.class);
        startActivity(i);
    }
    public void listaConfInsumos(){
        Intent i= new Intent(seleccionar.this,ListInsumosConfActivity.class);
        startActivity(i);
    }
}
