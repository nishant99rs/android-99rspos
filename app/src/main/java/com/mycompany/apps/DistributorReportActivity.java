package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import Adapter.DistributorListAdapter;
import Adapter.DistributorSearchAdapter;
import Adapter.VendorListAdapter;
import Pojo.ReportDistributorModel;
import Pojo.ReportVendorModel;


public class DistributorReportActivity extends Activity {

ListView listview;
AutoCompleteTextView autoCompleteTextView;
private TextWatcher mTextWatcher;
DistributorListAdapter distributorListAdapter;
  ArrayList<ReportDistributorModel>GetAllDistibutor;
ArrayList<ReportDistributorModel> searchDistributorList;
ArrayList<ReportDistributorModel> arrayDistributorList;
DistributorSearchAdapter searchDistributorAdapter;
ActionBar actionBar;
    String ActiveType[];
    Spinner Active;
    String SpinValue;
    ArrayAdapter<String> adapterActiveType;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_distributor_report);
    final DBhelper helper = new DBhelper(this);
    SQLiteDatabase db = helper.getReadableDatabase();

    listview = (ListView) findViewById(R.id.lv_DistributorReport);
    Log.e("***********Lt1*******", listview.toString());
    autoCompleteTextView = (CustomAuto) findViewById(R.id.autoCompleteTextView);
    autoCompleteTextView.setThreshold(1);
    actionBar=getActionBar();
    actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
    actionBar.setDisplayShowHomeEnabled(true);
    actionBar.setDisplayShowTitleEnabled(true);
    actionBar.setDisplayUseLogoEnabled(true);
    actionBar.setIcon(R.drawable.w);
    Active=(Spinner)findViewById(R.id.activeDistributor);

    GetAllDistibutor=helper.getDistributorForReport();
    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
    distributorListAdapter = new DistributorListAdapter( GetAllDistibutor ,DistributorReportActivity.this);
    listview.setAdapter(distributorListAdapter);

    ActiveType = getResources().getStringArray(R.array.active_Status_for_Report);
    adapterActiveType = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ActiveType);
    adapterActiveType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

    Active.setAdapter(adapterActiveType);
    Active.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            SpinValue = Active.getSelectedItem().toString();
            if (SpinValue.equals("Y"))
            {//rahul
                GetAllDistibutor=new ArrayList<ReportDistributorModel>();
                Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                distributorListAdapter = new DistributorListAdapter( GetAllDistibutor ,DistributorReportActivity.this);
                listview.setAdapter(distributorListAdapter);


                arrayDistributorList = helper.getDistributorReportforActive(SpinValue);
                if (distributorListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    distributorListAdapter = new DistributorListAdapter( arrayDistributorList , DistributorReportActivity.this);
                    listview.setAdapter(distributorListAdapter);
                }

                distributorListAdapter.notifyDataSetChanged();
                autoCompleteTextView.setText("");
            }else if(SpinValue.equals("N"))
            {//rahul
                GetAllDistibutor=new ArrayList<ReportDistributorModel>();
                Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                distributorListAdapter = new DistributorListAdapter( GetAllDistibutor ,DistributorReportActivity.this);
                listview.setAdapter(distributorListAdapter);


                arrayDistributorList = helper.getDistributorReportforActive(SpinValue);
                if (distributorListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    distributorListAdapter = new DistributorListAdapter( arrayDistributorList , DistributorReportActivity.this);
                    listview.setAdapter(distributorListAdapter);
                }
                for (ReportDistributorModel prod:arrayDistributorList){
                    distributorListAdapter.addProductToList(prod);
                }
                distributorListAdapter.notifyDataSetChanged();
                autoCompleteTextView.setText("");
            }
            else
            {   arrayDistributorList = new ArrayList<ReportDistributorModel>();
                if (distributorListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    distributorListAdapter = new DistributorListAdapter( arrayDistributorList , DistributorReportActivity.this);
                    listview.setAdapter(distributorListAdapter);
                }
                distributorListAdapter.notifyDataSetChanged();
                autoCompleteTextView.setText("");

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
        /******************************** distributor name selected from here********************************************************************************************/
        autoCompleteTextView.setThreshold(1);
        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Debuging", "After text changed called ");
                if (autoCompleteTextView.isPerformingCompletion()) {
                    Log.d("Debuging", "Performing completion ");
                    return;
                }
                String userTypedString = autoCompleteTextView.getText().toString().trim();
                if (userTypedString.equals("")) {
                    return;
                }
                searchDistributorList = helper.getDistributorName(userTypedString);
                if (searchDistributorList != null) {
                    if (searchDistributorAdapter == null) {
                        searchDistributorAdapter = new DistributorSearchAdapter(DistributorReportActivity.this, android.R.layout.simple_dropdown_item_1line, searchDistributorList);
                        searchDistributorAdapter.setList(searchDistributorList);

                        autoCompleteTextView.setAdapter(searchDistributorAdapter);
                        autoCompleteTextView.setThreshold(1);
                    } else {
                        searchDistributorAdapter.setList(searchDistributorList);
                        searchDistributorAdapter.notifyDataSetChanged();
                    }

                }
            }
        };

        autoCompleteTextView.addTextChangedListener(mTextWatcher);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();
                GetAllDistibutor=new ArrayList<ReportDistributorModel>();
                Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                distributorListAdapter = new DistributorListAdapter( GetAllDistibutor ,DistributorReportActivity.this);
                listview.setAdapter(distributorListAdapter);

                arrayDistributorList = helper.getDistributorReport(Value);
                if (distributorListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    distributorListAdapter = new DistributorListAdapter(new ArrayList<ReportDistributorModel>(), DistributorReportActivity.this);
                    listview.setAdapter(distributorListAdapter);
                }
                distributorListAdapter.addProductToList(arrayDistributorList.get(0));
                Log.i("&&&&&&&&", "Adding " + arrayDistributorList.get(0) + " to Product List..");
                distributorListAdapter.notifyDataSetChanged();
                autoCompleteTextView.setText("");
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

            Intent i=new Intent(DistributorReportActivity.this,MasterDataReportActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}