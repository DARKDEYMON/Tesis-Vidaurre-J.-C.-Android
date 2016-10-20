package com.example.darkdeymon.vidaurrejc.AsyncTasks;

import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;

import com.example.darkdeymon.vidaurrejc.Multipart.MultipartUtility;
import com.example.darkdeymon.vidaurrejc.R;

import java.io.File;
import java.io.IOException;

/**
 * Created by DARKDEYMON on 20/10/2016.
 */

public class sendReporteFotosRest extends AsyncTask<Void,Void,Boolean> {

    private AppCompatActivity view;
    private File file;
    private Uri uri;
    private String username;
    private String password;
    private String id;

    public sendReporteFotosRest(String username,String password, AppCompatActivity j,String id,File f)
    {
        this.username=username;
        this.password=password;
        this.id=id;
        this.file=f;
        this.view=j;
    }
    public sendReporteFotosRest(String username,String password, AppCompatActivity j,String id,Uri u)
    {
        this.username=username;
        this.password=password;
        this.id=id;
        this.uri=u;
        this.view=j;
    }
    @Override
    protected Boolean doInBackground(Void... params) {
        if(file==null)
        {
            file= new File(getRealPathFromURI(uri));
        }
        try {
            MultipartUtility m = new MultipartUtility(username,password,view.getString(R.string.server)+view.getString(R.string.sendfotos),"UTF-8");
            m.addFormField("reportes_avance",id);
            m.addFilePart("fotos_reporte",file);
            m.finish();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = view.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}
