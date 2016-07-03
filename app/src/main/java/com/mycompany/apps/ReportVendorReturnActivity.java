package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
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
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import Adapter.ReportVendorReturnAdapter;
import Adapter.ReportSearchReturnIdAdapter;
import Pojo.ReportVendorReturnModel;

public class ReportVendorReturnActivity extends Activity {

    ListView listview;
    AutoCompleteTextView idTextView;
    private TextWatcher idTextWatcher;
    ReportVendorReturnAdapter PaymentListAdapter;
    ArrayList<ReportVendorReturnModel> searchidList;
    ArrayList<ReportVendorReturnModel> arrayPaymentList;
    ReportSearchReturnIdAdapter searchIdAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_vendor_return);
        final String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
        final DBhelper helper = new DBhelper(this);
        helper.getReadableDatabase();
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        listview = (ListView) findViewById(R.id.lv_vendorReturnReport);
        Log.e("***********Lt1*******", listview.toString());
        idTextView = (CustomAuto) findViewById(R.id.reprtRetrnIdTextView);
        idTextView.setThreshold(1);

        //******************************** grn id selected from here********************************************************************************************/
        idTextView.setThreshold(1);
        idTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if (idTextView.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please select the Grn Id", Toast.LENGTH_SHORT).show();
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
                searchidList = helper.getVendorReturnId(userTypedString);
                if (searchidList != null) {
                    if (searchIdAdapter == null) {
                        searchIdAdapter = new ReportSearchReturnIdAdapter(ReportVendorReturnActivity.this, android.R.layout.simple_dropdown_item_1line, searchidList);
                        searchIdAdapter.setList(searchidList);
                        idTextView.setAdapter(searchIdAdapter);
                        idTextView.setThreshold(1);
                    } else {
                        searchIdAdapter.setList(searchidList);
                        searchIdAdapter.notifyDataSetChanged();
                    }
                }
            }
        };
        idTextView.addTextChangedListener(idTextWatcher);
        idTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();
                arrayPaymentList = helper.getVendorReturnReport(Value);
                if (PaymentListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    PaymentListAdapter = new ReportVendorReturnAdapter(new ArrayList<ReportVendorReturnModel>(), ReportVendorReturnActivity.this);
                    PaymentListAdapter.setList(arrayPaymentList);
                    listview.setAdapter(PaymentListAdapter);
                    idTextView.setText("");
                } else {
                    PaymentListAdapter.setList(arrayPaymentList);
                    PaymentListAdapter.notifyDataSetChanged();
                    idTextView.setText("");
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

            Intent i=new Intent(ReportVendorReturnActivity.this,PurchasingReportActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
