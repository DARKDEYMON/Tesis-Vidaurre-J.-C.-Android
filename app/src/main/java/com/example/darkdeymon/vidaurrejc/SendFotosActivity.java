package com.example.darkdeymon.vidaurrejc;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.darkdeymon.vidaurrejc.AsyncTasks.sendReporteFotosRest;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendFotosActivity extends AppCompatActivity implements View.OnClickListener {

    private String id_item;
    public static final int seleccionarFoto=1;
    public static final int tomarFoto=0;

    private Button mTomarFoto;
    private Button mSeleccionarFoto;

    private AccesData mAccesData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_fotos);
        startParams();
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        Log.e("id", String.valueOf(id));
        switch (id){
            case R.id.tomar_foto:
                tomarFoto();
                break;
            case R.id.seleccionar_foto:
                seleccionarFoto();
                break;
        }
    }
    public void startParams(){
        Bundle b = getIntent().getExtras();
        this.id_item = b.getString("id_reporte_send");
        Log.e("id_reporte_send",this.id_item);

        mAccesData = StaticValues.getLogetInfo(this);

        mSeleccionarFoto =(Button) findViewById(R.id.seleccionar_foto);
        mTomarFoto =(Button) findViewById(R.id.tomar_foto);
        mSeleccionarFoto.setOnClickListener(this);
        mTomarFoto.setOnClickListener(this);

    }
    public void seleccionarFoto(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto , seleccionarFoto);
    }
    private File photo;
    private Uri picUri;
    public void tomarFoto(){
        Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        photo= new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),timeStamp+".jpg");
        picUri= Uri.fromFile(photo);
        takePicture.putExtra(MediaStore.EXTRA_OUTPUT,picUri);
        takePicture.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(takePicture, tomarFoto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        sendReporteFotosRest send;
        if (resultCode == RESULT_OK)
            switch(requestCode){
                case seleccionarFoto:
                    Uri selectedImage = data.getData();
                    Log.e("select",selectedImage.toString());
                    send= new sendReporteFotosRest(mAccesData.getUsername(),mAccesData.getPassword(),this,id_item,selectedImage);
                    send.execute();
                    break;
                case tomarFoto:
                    if(photo.exists()) {
                        send = new sendReporteFotosRest(mAccesData.getUsername(), mAccesData.getPassword(), this, id_item, photo);
                        send.execute();
                        Log.e("take", picUri.getPath());
                    }
                    break;
                default:
                    break;
            }
    }
}
