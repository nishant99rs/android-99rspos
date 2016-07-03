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

import Adapter.VendorListAdapter;
import Adapter.VendorSearchAdapter;
import Pojo.ReportVendorModel;


public class VendorReportActivity extends Activity {

    ListView listview;
    AutoCompleteTextView autoCompleteTextView;
    private TextWatcher mTextWatcher;
    VendorListAdapter vendorListAdapter;
    ArrayList<ReportVendorModel> searchVendorList;
    ArrayList<ReportVendorModel> arrayVendorList;
    VendorSearchAdapter searchVendorAdapter;
    ArrayList<ReportVendorModel>GetAllVendor;
    ActionBar actionBar;
    String ActiveType[];
    Spinner Active;
    String SpinValue;
    ArrayAdapter<String> adapterActiveType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_report);

        final DBhelper helper = new DBhelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        listview = (ListView) findViewById(R.id.lv_VendorReport);
        Log.e("***********Lt1*******", listview.toString());
        autoCompleteTextView = (CustomAuto) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);

        Active=(Spinner)findViewById(R.id.activeVendor);
        GetAllVendor=helper.getVendorForReport();
        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
        vendorListAdapter = new VendorListAdapter( GetAllVendor ,VendorReportActivity.this);
        listview.setAdapter(vendorListAdapter);

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
                    GetAllVendor=new ArrayList<ReportVendorModel>();
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    vendorListAdapter = new VendorListAdapter( GetAllVendor ,VendorReportActivity.this);
                    listview.setAdapter(vendorListAdapter);


                    arrayVendorList = helper.getVendorReportforActive(SpinValue);
                    if (vendorListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        vendorListAdapter = new VendorListAdapter( arrayVendorList , VendorReportActivity.this);
                        listview.setAdapter(vendorListAdapter);
                    }
                    /*vendorListAdapter.addProductToList(arrayVendorList.get(0));
                    Log.i("&&&&&&&&", "Adding " + arrayVendorList.get(0) + " to Product List..");*/
                    vendorListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                }else if(SpinValue.equals("N"))
                {//rahul
                    GetAllVendor=new ArrayList<ReportVendorModel>();
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    vendorListAdapter = new VendorListAdapter( GetAllVendor ,VendorReportActivity.this);
                    listview.setAdapter(vendorListAdapter);


                    arrayVendorList = helper.getVendorReportforActive(SpinValue);
                    if (vendorListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        vendorListAdapter = new VendorListAdapter( arrayVendorList , VendorReportActivity.this);
                        listview.setAdapter(vendorListAdapter);
                    }
                    for (ReportVendorModel prod:arrayVendorList){
                        vendorListAdapter.addProductToList(prod);
                    }
                    vendorListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                }
                else
                {   arrayVendorList = new ArrayList<ReportVendorModel>();
                    if (vendorListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        vendorListAdapter = new VendorListAdapter( arrayVendorList , VendorReportActivity.this);
                        listview.setAdapter(vendorListAdapter);
                    }
                    vendorListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /******************************** vendor name selected from here********************************************************************************************/
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
                searchVendorList = helper.getVendorReportName(userTypedString);
                if (searchVendorList != null) {
                    if (searchVendorAdapter == null) {
                        searchVendorAdapter = new VendorSearchAdapter(VendorReportActivity.this, android.R.layout.simple_dropdown_item_1line, searchVendorList);
                        searchVendorAdapter.setList(searchVendorList);

                        autoCompleteTextView.setAdapter(searchVendorAdapter);
                        autoCompleteTextView.setThreshold(1);
                    } else {
                        searchVendorAdapter.setList(searchVendorList);
                        searchVendorAdapter.notifyDataSetChanged();
                    }

                }
            }
        };

        autoCompleteTextView.addTextChangedListener(mTextWatcher);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();
                GetAllVendor=new ArrayList<ReportVendorModel>();
                Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                vendorListAdapter = new VendorListAdapter( GetAllVendor ,VendorReportActivity.this);
                listview.setAdapter(vendorListAdapter);

                arrayVendorList=helper.getVendorReport(Value);
                if (vendorListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    vendorListAdapter = new VendorListAdapter(new ArrayList<ReportVendorModel>(),VendorReportActivity.this);
                    listview.setAdapter(vendorListAdapter);
                }
                vendorListAdapter.addProductToList(arrayVendorList.get(0));
                Log.i("&&&&&&&&", "Adding " + arrayVendorList.get(0) + " to Product List..");
                vendorListAdapter.notifyDataSetChanged();
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

            Intent i=new Intent(VendorReportActivity.this,MasterDataReportActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
