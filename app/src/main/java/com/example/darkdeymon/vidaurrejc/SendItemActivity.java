package com.example.darkdeymon.vidaurrejc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darkdeymon.vidaurrejc.AsyncTasks.sendReporteRest;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.getReporte;
import com.example.darkdeymon.vidaurrejc.classRest.item;
import com.example.darkdeymon.vidaurrejc.classRest.sendReporte;

import java.util.concurrent.ExecutionException;

public class SendItemActivity extends AppCompatActivity {

    private item id_item;
    private EditText mAlto;
    private EditText mLargo;
    private EditText mAncho;
    private EditText mObservaciones;
    private Button mButtonItem;
    private TextView mItemTipo;
    private AccesData mAccesData;
    private boolean envio=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_item);
        startParams();

    }

    public void startParams() {
        Bundle b = getIntent().getExtras();
        this.id_item =item.getJsonItem(b.getString(StaticValues.itemListaSendActivity));
        Log.e("id_item_send",this.id_item.getId());

        mAlto=(EditText)findViewById(R.id.item_alto);
        mLargo=(EditText)findViewById(R.id.item_largo);
        mAncho=(EditText)findViewById(R.id.item_ancho);
        mObservaciones=(EditText)findViewById(R.id.item_obserbacion);
        mButtonItem =(Button)findViewById(R.id.item_enviar_button);

        mItemTipo = (TextView)findViewById(R.id.item_tipo);
        mItemTipo.setText(mItemTipo.getText().toString()+id_item.getUnidad());

        mButtonItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("reaction","presed");
                itemSend();
                mButtonItem.setEnabled(false);
            }
        });
    }
    public void itemSend(){
        sendReporte sen = new sendReporte();
        sen.setItem(id_item.getId());
        sen.setAlto(mAlto.getText().toString());
        sen.setLargo(mLargo.getText().toString());
        sen.setAncho(mAncho.getText().toString());
        sen.setObservaciones(mObservaciones.getText().toString());

        String jsonData= sendReporte.jsonSendReporte(sen);
        mAccesData = StaticValues.getLogetInfo(this);
        Log.e("datajson",jsonData);
        try {

            sendReporteRest asin= new sendReporteRest(mAccesData.getUsername(),mAccesData.getPassword(),jsonData,this);
            asin.execute();
            if(asin.get()!=null)
            {
                getReporte r = getReporte.getReporte(asin.get());
                Toast.makeText(this,"Insercion Exitosa",Toast.LENGTH_SHORT).show();
                envio= true;
                Log.e("datajsonresep",asin.get());
                Log.e("id respuesta",r.getId());

                Intent i= new Intent(this,SendFotosActivity.class);
                i.putExtra("id_reporte_send",r.getId());
                startActivity(i);

            }else{
                Toast.makeText(this,"No se Pudo Insertar",Toast.LENGTH_SHORT).show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onRestart() {
        if(envio){
            finish();
        }
        super.onRestart();
    }
}
