package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.MylistAdapter;
import Pojo.ListModel;


public class ReportActivity extends Activity implements AdapterView.OnItemSelectedListener {

    public Spinner spinner;
    private ListView listView;
    ArrayList<ListModel> arraylist;
    public ReportActivity customview=null;


    ListModel listModel;

    MylistAdapter mylistadapter;
    public static final String TAG = "demo";
    /*layout created display _report _row which text view are used*/
    TextView TVAd_Main_Id;
    TextView TVStoreId;
    TextView TVAd_Start_Date;
    TextView TVAd_End_Date;
    TextView TVAd_Cst_Slb1;
    TextView TVAd_Cst_Slb2;
    TextView TVAd_Cst_Slb3;
    TextView TVAd_Text;
    ActionBar actionBar;


    DBhelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
        DBhelper helper=new DBhelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        final ArrayList arrayList = helper.getReportData();
        Log.e("%%%%%%%%%%%%%%%%%%%", arrayList.toString());
        Log.e("*****************", arrayList.toString());
        listView = (ListView) findViewById(R.id.lv_report);
        Log.e("***********Lt1*******", listView.toString());
        mylistadapter = new MylistAdapter(this, arrayList);
        listView.setAdapter(mylistadapter);
        Log.e("***********lt2*******", listView.toString());
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

    /*
            listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // TODO Auto-generated method stub
                  //  ListModel id_To_Search = arrayList.get(position);
                    Bundle dataBundle = new Bundle();
                  //  dataBundle.putString("id", id_To_Search);
                    if (dataBundle != null) {
                        String value = dataBundle.getString("id");
                        Cursor curs = helper.getReport(value);
                        curs.moveToFirst();
                        String tvAd_Main_Id = curs.getString(curs.getColumnIndex(GDBHelper.COLUMN_AD_MAIN_ID));
                        TVAd_Main_Id.setText(tvAd_Main_Id);
                        String tvAdStore_Id = curs.getString(curs.getColumnIndex(GDBHelper.COLUMN_STR_ID));
                        TVStoreId.setText(tvAdStore_Id);
                        String tvAd_Start_Date = curs.getString(curs.getColumnIndex(GDBHelper.COLUMN_AD_START_DATE));
                        TVAd_Start_Date.setText(tvAd_Start_Date);
                        String tvAd_End_Date = curs.getString(curs.getColumnIndex(GDBHelper.COLUMN_AD_END_DATE));
                        TVAd_End_Date.setText(tvAd_End_Date);
                        String tvAd_Cst_Slb1 = curs.getString(curs.getColumnIndex(GDBHelper.COLUMN_AD_CST_SLB1));
                        TVAd_Cst_Slb1.setText(tvAd_Cst_Slb1);
                        String tvAd_Cst_Slb2 = curs.getString(curs.getColumnIndex(GDBHelper.COLUMN_AD_CST_SLB2));
                        TVAd_Cst_Slb2.setText(tvAd_Cst_Slb2);
                        String tvAd_Cst_Slb3 = curs.getString(curs.getColumnIndex(GDBHelper.COLUMN_AD_CST_SLB3));
                        TVAd_Cst_Slb3.setText(tvAd_Cst_Slb3);
                        String tvAd_text = curs.getString(curs.getColumnIndex(GDBHelper.COLUMN_AD_TEXT));
                        TVAd_Text.setText(tvAd_text);

                        if (!curs.isClosed()) {
                            curs.close();
                        }

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });*/


        String[] report = {"January", "February", "March", "April", "May", "June","July","August","September","October","November","December"};
        ArrayAdapter<String> stringArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, report);
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Toast.makeText(getApplicationContext(), "OnItemSelectedListener  :" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
        String Spinnervalue = spinner.getSelectedItem().toString();

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

       // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }
//        if (id == R.id.email) {
//
//            Intent i=new Intent(ReportActivity.this,Activitymail.class);
//            startActivity(i);
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
