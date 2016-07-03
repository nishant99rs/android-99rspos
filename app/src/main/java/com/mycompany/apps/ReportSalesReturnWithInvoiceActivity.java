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

import Adapter.ReportWithInvoiceListAdapter;
import Adapter.TransactionIdWithInvoiceSearchAdapter;
import Pojo.SalesReturnReportModel;


public class ReportSalesReturnWithInvoiceActivity extends Activity {

    ListView listview;
    AutoCompleteTextView idTextView;
    private TextWatcher idTextWatcher;
    ReportWithInvoiceListAdapter totalListAdapter;
    ArrayList<String> searchIdList;
    ArrayList<SalesReturnReportModel> arrayTotalList;

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
    TransactionIdWithInvoiceSearchAdapter searchIdAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_return_withinvoice_report);
        final DBhelper helper = new DBhelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        Submit = (Button) findViewById(R.id.Submit);

        FromMonth = (Spinner) findViewById(R.id.TotalFromMonth);
        ToMonth = (Spinner) findViewById(R.id.TotalToMonth);
        FromYear = (Spinner) findViewById(R.id.TotalFromYear);
        ToYear = (Spinner) findViewById(R.id.TotalToYear);
        FromDate = (Spinner) findViewById(R.id.TotalFromDate);
        ToDate = (Spinner) findViewById(R.id.TotalToDate);

        listview = (ListView) findViewById(R.id.lv_SalesReturnReport);
        Log.e("***********Lt1*******", listview.toString());
        idTextView = (CustomAuto) findViewById(R.id.idTextView);
        idTextView.setThreshold(1);

        monthList = (ListView) findViewById(R.id.lv_SalesReturnReport);
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
        for (int i = 1; i <= 31; i++) {
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

                arrayTotalList = helper.SalesReturnData(fromString, toString);
                totalListAdapter = new ReportWithInvoiceListAdapter(arrayTotalList, ReportSalesReturnWithInvoiceActivity.this);
                monthList.setAdapter(totalListAdapter);
                totalListAdapter.notifyDataSetChanged();

            }
        });

        /******************************** vendor name selected from here********************************************************************************************/
        idTextView.setThreshold(1);
        idTextWatcher = new

                TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (idTextView.getText().toString().matches("")) {
                            Toast.makeText(getApplicationContext(), "Please select the Transaction Id", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.d("Debuging", "After text changed called ");
                        if (idTextView.isPerformingCompletion()) {
                            Log.d("Debuging", "Performing completion ");
                            return;
                        }
                        String userTypedString = idTextView.getText().toString().trim();
                        if (userTypedString.equals("")) {
                            return;
                        }
                        searchIdList = helper.getInvoiceTransId(userTypedString);
                        if (searchIdList != null) {
                            if (searchIdAdapter == null) {
                                searchIdAdapter = new TransactionIdWithInvoiceSearchAdapter(ReportSalesReturnWithInvoiceActivity.this, android.R.layout.simple_dropdown_item_1line, searchIdList);
                                searchIdAdapter.setList(searchIdList);

                                idTextView.setAdapter(searchIdAdapter);
                                idTextView.setThreshold(1);
                            } else {
                                searchIdAdapter.setList(searchIdList);
                                searchIdAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                };

        idTextView.addTextChangedListener(idTextWatcher);
        idTextView.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();

                arrayTotalList = helper.getSalesReturnReport(Value);
                if (totalListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    totalListAdapter = new ReportWithInvoiceListAdapter(new ArrayList<SalesReturnReportModel>(), ReportSalesReturnWithInvoiceActivity.this);
                    totalListAdapter.setList(arrayTotalList);
                    listview.setAdapter(totalListAdapter);
                } else {
                    totalListAdapter.setList(arrayTotalList);
                    totalListAdapter.notifyDataSetChanged();
                }

            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                String ty =null;
                for (SalesReturnReportModel prod:arrayTotalList)
                {
                    ty =prod.getGrnTotl().toString();
                    Log.e("Madhavi", "" + prod.getGrnTotl().toString());
                }


                Log.e("Test", ty);
                Bundle dataBundle = new Bundle();
                dataBundle.putString("id", ty);

                Intent intent = new Intent(getApplicationContext(), ShowSalesReturnWithInvoiceListActivity.class);

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

            Intent i=new Intent(ReportSalesReturnWithInvoiceActivity.this,SalesReturnReportActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}