package com.example.darkdeymon.vidaurrejc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.google.gson.Gson;

public class seleccionar extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;
    private Button mListaItem;
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
        mListaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(seleccionar.this,ListItem.class);
                startActivity(i);
            }
        });

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
}
