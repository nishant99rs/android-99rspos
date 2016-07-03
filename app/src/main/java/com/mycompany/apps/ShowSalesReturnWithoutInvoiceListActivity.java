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

import Adapter.ReportSalesReturnWithoutInvoiceListAdapter;
import Pojo.SalesReturnReportModel;


public class ShowSalesReturnWithoutInvoiceListActivity extends Activity {

    ListView listView;
    ReportSalesReturnWithoutInvoiceListAdapter productlistAdapter;
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
        alldata=helper.getallSalesReturn2data(Value);

        listView = (ListView) findViewById(R.id.lv_SalesReturnReport);
        Log.e("***********Lt1*******", listView.toString());

        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        productlistAdapter=new ReportSalesReturnWithoutInvoiceListAdapter(this,alldata,android.R.layout.simple_expandable_list_item_1,null);
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

            Intent i=new Intent(ShowSalesReturnWithoutInvoiceListActivity.this,ReportSaleActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
