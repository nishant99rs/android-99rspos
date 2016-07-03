package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Activityreport extends Activity {
    String item;
    EditText reportcriteria;
    EditText comments;
    Button insert;
    Spinner reportspin;
    String ActiveType[] = { "Retail_ad_ticker","Retail_ad_blinkinglogo","Retail_ad_store_main","retail_cust_loyality","retail_card_define","rule_defination"};

    DBhelper mydb;
    ActionBar actionBar;

    // Adapter
    ArrayAdapter<String> adapteractiveType;
    final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityreport);

        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        mydb = new DBhelper(this);
        reportspin = (Spinner) findViewById(R.id.Activespin);

        //**************************************** spinner  for Active type*****************************************
        adapteractiveType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ActiveType);
        reportspin.setAdapter(adapteractiveType);
        reportspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                item = adapterView.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(),
                        "report : " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });

        ArrayList<String> updatelist3 = mydb.getdatareports();
        final   String Store_Id_Search = updatelist3.get(0);

        long value=System.currentTimeMillis();
        final   String Sno= Long.toString(value);


        comments=(EditText)findViewById(R.id.editcomments);
        reportcriteria=(EditText)findViewById(R.id.editreportscriteria);

        insert=(Button)findViewById(R.id.Report_insert);


        insert.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {



                mydb.insertdata3(Store_Id_Search,Sno.toString(),item.toString(),reportcriteria.getText().toString(),comments.getText().toString());
                Log.e("@@@@@@@@@@@@", "item is" + item);
                Toast.makeText(getApplicationContext(), "INSERTED", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Activity_masterScreen1.class);
                startActivity(intent);
            }
        });
        Button Exit1 = (Button) findViewById(R.id.Report_exit);
        Exit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(Buttonok);
                Intent intent = new Intent(getApplicationContext(), Activity_masterScreen1.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public String toString(){
        return item;
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

            Intent i=new Intent(Activityreport.this,ActivityLoyality.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

