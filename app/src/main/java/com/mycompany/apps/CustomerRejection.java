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

import Adapter.CustomerRejectAdapter;

import Pojo.CustomerRejectModel;

import java.util.ArrayList;

public class CustomerRejection extends Activity {


    ActionBar actionBar;
    Spinner mreasone;
    DBhelper mydb;
    EditText mCReasonid,mCReason;
    Button insert,clrbtn,exit;
    ListView listView;
    String Store_Id_To_Update;
    ArrayList<CustomerRejectModel>reasonarraylist;

    ArrayAdapter<String> adapteractiveType;
    CustomerRejectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_rejection);



        insert = (Button) findViewById(R.id.reject_insert);
      //  clrbtn = (Button) findViewById(R.id.reject_clear);
        exit = (Button) findViewById(R.id.reject_exit);
        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
        actionBar.setIcon(R.drawable.w);
        mCReason = (EditText) findViewById(R.id.mRejectreason);


        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);

        mydb = new DBhelper(this);
        mreasone = (Spinner) findViewById(R.id.reasonrejection);



        final DBhelper mydb = new DBhelper(CustomerRejection.this);
        ArrayList<String> reasonReturn = mydb.getReasonRejection();
        ArrayAdapter<String> stringArrayAdapter =
                new ArrayAdapter<String>(CustomerRejection.this, android.R.layout.simple_spinner_dropdown_item, reasonReturn);
        mreasone.setAdapter(stringArrayAdapter);
        mreasone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String   item = mreasone.getSelectedItem().toString();

                if (item.matches("")) {
                    return;
                }
                reasonarraylist= mydb.getCustomerRejection(item);
                if (  reasonarraylist != null &&   reasonarraylist.size() > 0) {
                    if (adapter == null) {

                        Log.d("customerrejection", "Product arraylist size is " + reasonarraylist.size());
                        adapter = new CustomerRejectAdapter(CustomerRejection.this, android.R.layout.simple_dropdown_item_1line, reasonarraylist);


                    }
                    else if (adapter != null)
                    {
                        adapter = new CustomerRejectAdapter(CustomerRejection.this, android.R.layout.simple_dropdown_item_1line, reasonarraylist);

                    }


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


                if (mCReason.getText().toString().matches("")) {
                    mCReason.setError("Please Enter Reason");

                    return;
                }
                try {


                    Long Value = System.currentTimeMillis();
                    //String resval = Long.toString(Value);

                    mydb.updateCReason(Store_Id_To_Update,mCReason.getText().toString());
                    Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(),ActivityLoyality.class);
                    startActivity(intent);


                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }

        });



        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(Buttonok);
                Intent intent = new Intent(getApplicationContext(), ActivityLoyality.class);
                startActivity(intent);
            }
        });

        /*clrbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);
                try {
                    listView.setAdapter(null);
                    mCReason.setText("");

                }catch (NullPointerException ex) {
                    ex.printStackTrace();
                }


            }
        });
*/
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

            Intent i=new Intent(CustomerRejection.this,ActivityLoyality.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}