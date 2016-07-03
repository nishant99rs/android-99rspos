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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import Adapter.PurchaseOrderListAdapter;
import Adapter.VendorSearchProcurementAdapter;
import Pojo.VendorReportModel;



public class ProcurementReportActivity extends Activity {

    ListView listview;
    CustomAuto vendorTextViewName;
    private TextWatcher vendorTextWatcher;
    PurchaseOrderListAdapter orderNoListAdapter;
    ArrayList<String> searchVendorList;
    ArrayList<VendorReportModel> arrayVendorList;

    public Spinner FromMonth;
    public Spinner ToMonth;
    public Spinner FromYear;
    public Spinner ToYear;
    public Spinner FromDate;
    public Spinner ToDate;

    String FromYearValue;
    String ToYearValue;
    String FromMonthValue;
    String ToMonthValue;
    String FromDateValue;
    String ToDateValue;

    String fromString;
    String toString ;
    Button Submit;
    private ListView monthList;
    VendorSearchProcurementAdapter searchVendorAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_reportprocurement);
        final DBhelper helper = new DBhelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        Submit=(Button)findViewById(R.id.Submit);

        FromMonth = (Spinner) findViewById(R.id.NoFromMonth);
        ToMonth=(Spinner)findViewById(R.id.NoToMonth);
        FromYear=(Spinner)findViewById(R.id.NoFromYear);
        ToYear=(Spinner)findViewById(R.id.NoToYear);
        FromDate=(Spinner)findViewById(R.id.NoFromDate);
        ToDate=(Spinner)findViewById(R.id.NoToDate);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);


        listview = (ListView) findViewById(R.id.lv_VendorReport);
        Log.e("***********Lt1*******", listview.toString());
        vendorTextViewName = (CustomAuto) findViewById(R.id.VendorTextView);
        vendorTextViewName.setThreshold(1);

        monthList = (ListView) findViewById(R.id.lv_VendorReport);
        Log.e("***********Lt1*******", monthList.toString());

        final String[] month = {"Jan", "Feb", "March", "April", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, month);


        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 2016; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        ArrayList<String> dates = new ArrayList<String>();
        int Date = Calendar.getInstance().get(Calendar.DATE);
        for (int i = 1;i<=31;i++){
            dates.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        FromMonth.setAdapter(arrayAdapter);
        ToMonth.setAdapter(arrayAdapter);
        FromYear.setAdapter(adapter);
        ToYear.setAdapter(adapter);

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dates);
        FromDate.setAdapter(adapter1);
        ToDate.setAdapter(adapter1);

       FromMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               FromMonthValue = FromMonth.getSelectedItem().toString();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

        ToMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToMonthValue = ToMonth.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FromYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FromYearValue = FromYear.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ToYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToYearValue = ToYear.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        FromDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FromDateValue = FromDate.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ToDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToDateValue = ToDate.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String month_from = FromMonthValue;
                String year_from = FromYearValue;
                String date_from = FromDateValue;

                String month_to = ToMonthValue;
                String year_to = ToYearValue;
                String date_to = ToDateValue;


                fromString = String.format("%s-%02d-%s " + "00:00:00", year_from, getIntFromMonthName(month_from), date_from);
                Log.e("Value from date ", fromString);
                toString = String.format("%s-%02d-%s " + "23:59:59", year_to, getIntFromMonthName(month_to), date_to);
                Log.e("Value To date ", toString);

                arrayVendorList = helper.Demo(fromString, toString);
                orderNoListAdapter = new PurchaseOrderListAdapter(arrayVendorList, ProcurementReportActivity.this);
                monthList.setAdapter(orderNoListAdapter);
                orderNoListAdapter.notifyDataSetChanged();

            }
        });

        /******************************** vendor name selected from here********************************************************************************************/
        vendorTextViewName.setThreshold(1);
        vendorTextWatcher = new

                TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (vendorTextViewName.getText().toString().matches("")) {
                            Toast.makeText(getApplicationContext(), "Please select the Vendor Name", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.d("Debuging", "After text changed called ");
                        if (vendorTextViewName.isPerformingCompletion()) {
                            Log.d("Debuging", "Performing completion ");
                            return;
                        }
                        String userTypedString = vendorTextViewName.getText().toString().trim();
                        if (userTypedString.equals("")) {
                            return;
                        }
                        searchVendorList = helper.getVendorNameprocurement(userTypedString);
                        if (searchVendorList != null) {
                            if (searchVendorAdapter == null) {
                                searchVendorAdapter = new VendorSearchProcurementAdapter(ProcurementReportActivity.this, android.R.layout.simple_dropdown_item_1line, searchVendorList);
                                searchVendorAdapter.setList(searchVendorList);

                                vendorTextViewName.setAdapter(searchVendorAdapter);
                                vendorTextViewName.setThreshold(1);
                            } else {
                                searchVendorAdapter.setList(searchVendorList);
                                searchVendorAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                };

        vendorTextViewName.addTextChangedListener(vendorTextWatcher);
        vendorTextViewName.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), "U select " + Value, Toast.LENGTH_SHORT).show();
                arrayVendorList = helper.getVendorReportprocurement(Value);
                if (orderNoListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    orderNoListAdapter = new PurchaseOrderListAdapter(new ArrayList<VendorReportModel>(), ProcurementReportActivity.this);
                    orderNoListAdapter.setList(arrayVendorList);
                    listview.setAdapter(orderNoListAdapter);
                } else {
                    orderNoListAdapter.setList(arrayVendorList);
                    orderNoListAdapter.notifyDataSetChanged();
                }

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                String ty =null;
                for (VendorReportModel prod:arrayVendorList)
                {
                    ty =prod.getPo_No().toString();
                    Log.e("Madhavi", "" + prod.getPo_No().toString());
                }


                Log.e("Test", ty);
                Bundle dataBundle = new Bundle();
                dataBundle.putString("id", ty);

                Intent intent = new Intent(getApplicationContext(), ShowPurchaselistActivity.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
}

    /**
     *
     * @param monthName Month name either in full ("January") or short "Jan"
     *                  case insensitive
     * @return Returns integer corresponding to month name (1 for January...)
     *          Returns -1 if month name not recognized
     */

    private int getIntFromMonthName( String monthName ) {
        String[] monthNames = new String[] { "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December" };

        int returnVal = -1;

        for( int i=0; i < monthNames.length; i++ ) {
            if( monthNames[i].toLowerCase().contains(monthName.toLowerCase())) {
                returnVal = i+1;
                break;
            }
        }

        return returnVal;
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

            Intent i=new Intent(ProcurementReportActivity.this,PurchasingReportActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}