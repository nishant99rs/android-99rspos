package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.ReportSalesReturnWithInvoiceListAdapter;
import Pojo.SalesReturnReportModel;


public class ShowSalesReturnWithInvoiceListActivity extends Activity {

    ListView listView;
    ReportSalesReturnWithInvoiceListAdapter productlistAdapter;
    ArrayList<SalesReturnReportModel> alldata;
    DBhelper helper;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_salereturnlist);
        helper = new DBhelper(this);
        Bundle extras = getIntent().getExtras();
        String Value = extras.getString("id");
        alldata=helper.getallSalesReturndata(Value);

        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        listView = (ListView) findViewById(R.id.lv_SalesReturnReport);
        Log.e("***********Lt1*******", listView.toString());


        productlistAdapter=new ReportSalesReturnWithInvoiceListAdapter(this,alldata,android.R.layout.simple_expandable_list_item_1,null);
        listView.setAdapter(productlistAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent i=new Intent(ShowSalesReturnWithInvoiceListActivity.this,ReportSaleActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
