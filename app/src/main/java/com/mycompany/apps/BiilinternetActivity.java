package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class BiilinternetActivity extends Activity {
    ListView obj;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biilinternet);
         String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
        DBhelper helper = new DBhelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        helper = new DBhelper(this);
        final ArrayList array_list = helper.getMobileinternet();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        Log.e("*****************", array_list.toString());
        obj = (ListView)findViewById(R.id.lv_billreportinternet);
        Log.e("*********",obj.toString());
        obj.setAdapter(arrayAdapter);
        Log.e("*********", arrayAdapter.toString());

        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
              // String id_To_Search = array_list.get(arg2);


                String id_To_Search=array_list.get(arg2).toString();
                Log.e("***********",array_list.toString());
                //String id_To_Search=
                Bundle dataBundle = new Bundle();
                dataBundle.putString("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(), Activityinternetbill.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }
}
