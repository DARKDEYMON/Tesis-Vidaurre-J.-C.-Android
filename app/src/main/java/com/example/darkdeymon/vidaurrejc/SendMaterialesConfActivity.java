package com.example.darkdeymon.vidaurrejc;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.darkdeymon.vidaurrejc.Multipart.JasonMultipartSend;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.salidaInsumo;
import com.example.darkdeymon.vidaurrejc.classRest.salidaMaterial;
import com.google.gson.JsonObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class SendMaterialesConfActivity extends AppCompatActivity implements View.OnClickListener{

    private AccesData mAccesData;
    private salidaMaterial mSalidaMaterial;

    private TextView mTxId;
    private TextView mTxAlmacen;
    private TextView mTxMaterial;
    private TextView mTxItem;
    private TextView mTxFecha;
    private TextView mTxCantidad;

    private Button mConfirmar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_materiales_conf);
        starParams();
    }
    public void starParams(){
        mAccesData = StaticValues.getLogetInfo(this);

        Bundle b = getIntent().getExtras();
        mSalidaMaterial = salidaMaterial.getSalidaMaterialFromJson(b.getString(StaticValues.salidaInsumoActivity));
        Log.e("id_item_send",mSalidaMaterial.getId());

        mTxId=(TextView) findViewById(R.id.txid);
        mTxAlmacen=(TextView) findViewById(R.id.txalmacen);
        mTxMaterial=(TextView) findViewById(R.id.txmaterial);
        mTxItem=(TextView) findViewById(R.id.txitem);
        mTxFecha=(TextView) findViewById(R.id.txfecha);
        mTxCantidad=(TextView) findViewById(R.id.txcantidad);
        mConfirmar=(Button)findViewById(R.id.confirmar);
        mConfirmar.setOnClickListener(this);

        mTxId.setText(mTxId.getText()+mSalidaMaterial.getId());
        mTxAlmacen.setText(mTxAlmacen.getText()+mSalidaMaterial.getAlmacen());
        mTxMaterial.setText(mTxMaterial.getText()+mSalidaMaterial.getMaterial());
        mTxItem.setText(mTxItem.getText()+mSalidaMaterial.getItem());
        mTxFecha.setText(mTxFecha.getText()+mSalidaMaterial.getFecha());
        mTxCantidad.setText(mTxCantidad.getText()+mSalidaMaterial.getCantidad());

    }

    @Override
    public void onClick(View v) {
        int id= v.getId();
        switch (id){
            case R.id.confirmar:
                sen();
                break;
        }
    }
    public void sen(){
        JsonObject j=new JsonObject();
        j.addProperty("confirmado",true);
        Log.e("envio",j.toString());
        confirmMaterial con=new confirmMaterial(j.toString(),mSalidaMaterial.getId());
        con.execute();
        try {
            if(con.get()) {
                Toast.makeText(this, "Echo", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(this,"Algo salio mal",Toast.LENGTH_SHORT).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public class confirmMaterial extends AsyncTask<Void,Void,Boolean> {

        private String data;
        private String id;
        public confirmMaterial(String data,String id){
            this.data=data;
            this.id=id;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            JasonMultipartSend j= new JasonMultipartSend(true);
            try {
                Log.e("url",getString(R.string.server)+getString(R.string.getsalidainsumos)+this.id+"/");
                String dat = j.getData(new URL(getString(R.string.server)+getString(R.string.getsalidamaterial)+id+"/"),mAccesData.getUsername(),mAccesData.getPassword(),data);
                return true;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}
