package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapter.CustomAdaptor;
import Pojo.Tax;


public class ActivityTax extends Activity {
ActionBar actionBar;
    private ListView listView;
    Tax tax;
    CustomAdaptor customAdaptor;

    TextView text1;
    TextView text2;
    DBhelper  mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_tax);
        String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
        mydb = new DBhelper(this);
        SQLiteDatabase db = mydb.getReadableDatabase();
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        DBhelper hp=new DBhelper(this);
        final ArrayList arrayList=hp.getAllTax();
        Resources res =getResources();

        listView = (ListView) findViewById(R.id.listView);
        customAdaptor=new CustomAdaptor(this,arrayList);
        listView.setAdapter(customAdaptor);
        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);
        final Button ok = (Button)findViewById(R.id.tax_ok_button);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(Buttonok);
                Intent intent = new Intent(getApplicationContext(), Activity_masterScreen2.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
