package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.Comp_list_adapter;
import Pojo.Companylistmodel;


public class Activity_Comp_Btl extends Activity {

    DBhelper helper;
    ArrayList<Companylistmodel> arrayList;
    Comp_list_adapter ListAdapter;
    ListView listView;
    ActionBar actionBar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__comp__btl);
        helper = new DBhelper(this);

        listView = (ListView) findViewById(R.id.complistView);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);



        arrayList = helper.getcompanylist();
        if (arrayList != null) {
            if (ListAdapter == null) {
                ListAdapter = new Comp_list_adapter(Activity_Comp_Btl.this, android.R.layout.simple_dropdown_item_1line,arrayList);
                ListAdapter.setList(arrayList);
                listView.setAdapter(ListAdapter);


            } else {
                ListAdapter.setList(arrayList);
                ListAdapter.notifyDataSetChanged();

            }

        }








}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_master_screen1, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent i=new Intent(Activity_Comp_Btl.this,Activity_masterScreen2.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
