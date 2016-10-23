package com.example.darkdeymon.vidaurrejc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.darkdeymon.vidaurrejc.Adapters.itemAdapter;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.itemRest;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.item;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListItemReqInsumosActivity extends AppCompatActivity implements View.OnClickListener{

    private Spinner mSpinner;
    private EditText mTextviewSearch;
    private ListView mListView;
    private ArrayList<item> mItems;
    private ArrayList<item> mBuscarItems;
    private AppCompatActivity mThis;
    private AccesData mAccesData;
    private Button mSerachButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_req_insumos);
        start();
    }
    public void start(){
        mSpinner =(Spinner)findViewById(R.id.spinnerListItem);
        mTextviewSearch =(EditText)findViewById(R.id.editTextSearch);
        mListView =(ListView)findViewById(R.id.listItem);

        mSerachButton =(Button)findViewById(R.id.serachButton);
        mSerachButton.setOnClickListener(this);

        mThis = this;
        mAccesData = StaticValues.getLogetInfo(mThis);
        Log.e("lista","empeso");


        try {
            itemRest i= new itemRest(mAccesData.getUsername(),mAccesData.getPassword(),mThis);
            i.execute();
            mItems = i.get();

            itemAdapter adapter = new itemAdapter(getApplicationContext(),mItems);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    itemClick(parent,position);
                }
            });


            Log.e("lista",mItems.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public void buscar(){
        String res=mSpinner.getSelectedItem().toString();
        Log.e("res",res);
        mBuscarItems = new ArrayList<item>();
        if(res.equals("Todo"))
            mBuscarItems = mItems;
        for (int i = 0; i < mItems.size(); i++) {
            Log.e("for",mItems.get(i).toString());
            switch(res){
                case "Nombre de Proyecto":
                    if(mItems.get(i).getProyecto_name().matches(mTextviewSearch.getText().toString())){
                        mBuscarItems.add(mItems.get(i));
                    }
                    break;
                case "Descripcion Item":
                    if(mItems.get(i).getDescripcion().matches(mTextviewSearch.getText().toString())){
                        mBuscarItems.add(mItems.get(i));
                    }
                    break;
            }
        }
        if(mBuscarItems.size()!=0){
            itemAdapter adapter = new itemAdapter(getApplicationContext(),mBuscarItems);
            mListView.setAdapter(adapter);
        }
        else {
            Toast.makeText(this,"no se encontraron coinsidencias",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.serachButton:
                buscar();
                break;
        }
    }


    public void itemClick(AdapterView<?> parent, int position){
        item itemres=(item)parent.getAdapter().getItem(position);
        Log.e("id",itemres.getId());
        Intent i = new Intent(ListItemReqInsumosActivity.this,SendInsumosActivity.class);
        i.putExtra(StaticValues.itemListaSendActivity,item.getItemJson(itemres));
        startActivity(i);
    }
}
