package com.example.darkdeymon.vidaurrejc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.darkdeymon.vidaurrejc.Adapters.itemAdapter;
import com.example.darkdeymon.vidaurrejc.AsyncTasks.itemRest;
import com.example.darkdeymon.vidaurrejc.classRest.AccesData;
import com.example.darkdeymon.vidaurrejc.classRest.StaticValues;
import com.example.darkdeymon.vidaurrejc.classRest.item;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListItem extends AppCompatActivity {

    private Spinner mSpinner;
    private EditText mTextview;
    private ListView mListView;
    private ArrayList<item> mItems;
    private AppCompatActivity mThis;
    private AccesData mAccesData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
        start();
    }
    public void start(){
        mSpinner =(Spinner)findViewById(R.id.spinnerListItem);
        mTextview =(EditText)findViewById(R.id.editTextSearch);
        mListView =(ListView)findViewById(R.id.listItem);

        mThis = this;
        mAccesData = StaticValues.getLogetInfo(mThis);
        Log.e("lista","empeso");

        itemRest i= new itemRest(mAccesData.getUsername(),mAccesData.getPassword(),mThis);
        try {
            i.execute();
            mItems = i.get();

            itemAdapter adapter = new itemAdapter(getApplicationContext(),mItems);
            mListView.setAdapter(adapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    item item=(item)parent.getAdapter().getItem(position);
                    Log.e("id",item.getId());
                    Intent i = new Intent(ListItem.this,SendItemActivity.class);
                    i.putExtra("id_item",item.getId());
                    startActivity(i);
                }
            });

            Log.e("lista",mItems.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
