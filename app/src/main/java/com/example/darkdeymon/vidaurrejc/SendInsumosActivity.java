package com.example.darkdeymon.vidaurrejc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.darkdeymon.vidaurrejc.Adapters.insumoAdapter;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.insumoRest;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.sendReqInsumoRest;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.insumo;
import com.example.darkdeymon.vidaurrejc.classRest.item;
import com.example.darkdeymon.vidaurrejc.classRest.sendReqInsumo;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SendInsumosActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner mSpinnerInsumos;
    private EditText mCatidad;
    private EditText mPrecioTotal;
    private EditText mObservaciones;
    private Button mEnviar;

    private item mItem;
    private AccesData mAccesData;

    private ArrayList<insumo> mListInsumo;
    private insumoAdapter mMaterialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_insumos);
        startParams();
        insumosAsync();
    }
    public void startParams(){
        Bundle b = getIntent().getExtras();
        mItem = item.getJsonItem(b.getString(StaticValues.itemListaSendActivity));
        Log.e("datoMaterial",mItem.getId());

        mAccesData = StaticValues.getLogetInfo(this);

        mSpinnerInsumos =(Spinner)findViewById(R.id.select_insumo);
        mCatidad = (EditText)findViewById(R.id.insumo_cantidad);
        mPrecioTotal = (EditText)findViewById(R.id.insumo_precio);
        mObservaciones = (EditText)findViewById(R.id.insumos_obserbacion);
        mEnviar =(Button)findViewById(R.id.insumos_enviar_button);
        mEnviar.setOnClickListener(this);

    }
    public void insumosAsync(){
        insumoRest in= new insumoRest(mAccesData.getUsername(),mAccesData.getPassword(),this);
        in.execute();
        try {
            mListInsumo=in.get();
            Log.e("aqui",in.get().toString());
            mMaterialAdapter = new insumoAdapter(this,mListInsumo);
            mSpinnerInsumos.setAdapter(mMaterialAdapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View v) {
        int id= v.getId();
        switch (id){
            case R.id.insumos_enviar_button:
                send();
                break;
        }
    }

    public void send(){
        insumo i=(insumo) mSpinnerInsumos.getSelectedItem();

        sendReqInsumo ins= new sendReqInsumo();
        ins.setItem(mItem.getId());
        ins.setInsumos(i.getId());
        ins.setCantidad(mCatidad.getText().toString());
        ins.setPrecio_estimado_total(mPrecioTotal.getText().toString());
        ins.setObservaciones(mObservaciones.getText().toString());

        sendReqInsumoRest req= new sendReqInsumoRest(mAccesData.getUsername(),mAccesData.getPassword(),sendReqInsumo.reqInsumoToJson(ins),this);
        req.execute();
        try {
            if(req.get()!=null){
                Toast.makeText(this, "Se Inserto con Exito", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Algo A Fallado", Toast.LENGTH_SHORT).show();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
