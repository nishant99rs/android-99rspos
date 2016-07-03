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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.BillLevelAdapter;
import Pojo.BillLevelModel;

public class Activity_Bill_Level extends Activity {


    ActionBar actionBar;
    Spinner mbilllevelspinner;
    DBhelper mydb;
    EditText mBilllevelname,mBillleveltype;
    Button insert,exit;
    ListView listView;
    String Store_Id_To_Update;
    ArrayList<BillLevelModel>reasonarraylist;

    ArrayAdapter<String> adapteractiveType;
    BillLevelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__bill__level);


        mBilllevelname = (EditText) findViewById(R.id.mbilllevlname);
        mBillleveltype = (EditText) findViewById(R.id.mbilllevltype);

        Button insert = (Button) findViewById(R.id.Billlevel_mbutton);
        Button clrbtn = (Button) findViewById(R.id.clearbilllevel);


        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setIcon(R.drawable.w);

        // TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Roboto-Regular.ttf"); // font from assets: "assets/fonts/Roboto-Regular.ttf


        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);

        mydb = new DBhelper(this);
        //listView=(ListView) findViewById(R.id.lv_rejection) ;
        mbilllevelspinner = (Spinner) findViewById(R.id.billlevelspinner);



        final DBhelper mydb = new DBhelper(Activity_Bill_Level.this);
        ArrayList<String> billLevel = mydb.getBillLevel();
        ArrayAdapter<String> stringArrayAdapter =

                new ArrayAdapter<String>(Activity_Bill_Level.this, android.R.layout.simple_spinner_dropdown_item, billLevel);
        mbilllevelspinner.setAdapter(stringArrayAdapter);
        mbilllevelspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String   item = mbilllevelspinner.getSelectedItem().toString();

                //String item = holdbill.getSelectedItem().toString();
                if (item.matches("")) {
                    return;
                }
                // reasonarraylist= mydb.getBillLevel(item);
                if (  reasonarraylist != null &&   reasonarraylist.size() > 0) {
                    if (adapter == null) {

                        Log.d("customerrejection", "Product arraylist size is " + reasonarraylist.size());
                        adapter = new BillLevelAdapter(Activity_Bill_Level.this, android.R.layout.simple_dropdown_item_1line, reasonarraylist);
                        // listView.setAdapter(adapter);

                    }
                    else if (adapter != null)
                    {
                        adapter = new BillLevelAdapter(Activity_Bill_Level.this, android.R.layout.simple_dropdown_item_1line, reasonarraylist);
                        // listView.setAdapter(adapter);
                    }


//                    for (VendorRejectionModel prod: reasonarraylist)
//                    {
//                        adapter.addProductToList(prod);
//                    }
//                        listView.setAdapter(adapter);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });




        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(Buttonok);


                // mCReasonid = (EditText) findViewById(R.id.mRejectid);


                if (mBilllevelname.getText().toString().matches("")) {
                    mBilllevelname.setError("Please Enter Name");

                    return;
                }
                if (mBillleveltype.getText().toString().matches("")) {
                    mBillleveltype.setError("Please Enter Type");

                    return;
                }
                try {


                    Long Value = System.currentTimeMillis();
                    String resval = Long.toString(Value);

                    mydb.updateBillLevel(Store_Id_To_Update,mBilllevelname.getText().toString(),mBillleveltype.getText().toString());
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), Activity_masterScreen1.class);
                    startActivity(intent);


                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }

        });


       /* Button Exit = (Button) findViewById(R.id.mBexit_button);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(Buttonok);
                try {
                    Intent intent = new Intent(getApplicationContext(), ActivityLoyality.class);
                    startActivity(intent);

                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }});*/

        clrbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);
                try {


                    mBillleveltype.setText("");
                    mBilllevelname.setText("");

                }catch (NullPointerException ex) {
                    ex.printStackTrace();
                }


            }
        });

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

            Intent i=new Intent(Activity_Bill_Level.this,ActivityLoyality.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}