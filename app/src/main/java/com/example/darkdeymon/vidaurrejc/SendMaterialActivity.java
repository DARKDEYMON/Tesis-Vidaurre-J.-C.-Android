package com.example.darkdeymon.vidaurrejc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.darkdeymon.vidaurrejc.Adapters.materialAdapter;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.materialRest;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.sendReqMaterialRest;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.item;
import com.example.darkdeymon.vidaurrejc.classRest.material;
import com.example.darkdeymon.vidaurrejc.classRest.sendReqMaterial;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SendMaterialActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner mSpinnerMaterial;
    private EditText mCatidad;
    private EditText mPrecioTotal;
    private EditText mObservaciones;
    private Button mEnviar;
    private item mItem;
    private AccesData mAccesData;
    private ArrayList<material> mListMaterial;
    private materialAdapter mMaterialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_material);

        startParams();
    }
    public void startParams(){
        mAccesData = StaticValues.getLogetInfo(this);

        mSpinnerMaterial=(Spinner)findViewById(R.id.select_material);
        mCatidad=(EditText)findViewById(R.id.material_cantidad);
        mPrecioTotal=(EditText)findViewById(R.id.material_precio);
        mObservaciones=(EditText)findViewById(R.id.material_obserbacion);
        mEnviar=(Button)findViewById(R.id.material_enviar_button);
        mEnviar.setOnClickListener(this);

        Bundle b = getIntent().getExtras();
        mItem =item.getJsonItem(b.getString(StaticValues.itemListaSendActivity));
        Log.e("datoMaterial",mItem.getId());

        materialAsync();


        mSpinnerMaterial.setAdapter(mMaterialAdapter);
    }
    public void materialAsync(){
        materialRest mk= new materialRest(mAccesData.getUsername(),mAccesData.getPassword(),this);
        try {
            mk.execute();
            mListMaterial = mk.get();
            Log.e("aqui",mk.get().toString());
            mMaterialAdapter= new materialAdapter(this,mListMaterial);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.material_enviar_button:
                send();
                break;
        }
    }
    public void send(){
        material m=(material) mSpinnerMaterial.getSelectedItem();

        sendReqMaterial s= new sendReqMaterial();
        s.setItem(mItem.getId());
        s.setMaterial(m.getId());
        s.setCantidad(mCatidad.getText().toString());
        s.setPrecio_estimado_total(mPrecioTotal.getText().toString());
        s.setObservaciones(mObservaciones.getText().toString());

        Log.e("este",sendReqMaterial.reqMaterialToJson(s));

        sendReqMaterialRest sen= new sendReqMaterialRest(mAccesData.getUsername(),mAccesData.getPassword(),sendReqMaterial.reqMaterialToJson(s),this);
        sen.execute();
        try {
            if(sen.get()!=null) {
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
