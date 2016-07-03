package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SaleReportActivity extends Activity implements View.OnClickListener{
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_sale);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        ImageButton SalesBtn=(ImageButton)findViewById(R.id.SaleReportSales);
        ImageButton SalesRetrnBtn=(ImageButton)findViewById(R.id.SaleReportSalesReturn);

        SalesBtn.setOnClickListener(this);
        SalesRetrnBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.SaleReportSales:
                Intent intent = new Intent(this, ReportSaleActivity.class);
                startActivity(intent);
                break;
            case R.id.SaleReportSalesReturn:
                Intent in = new Intent(this, SalesReturnReportActivity.class);
                startActivity(in);
                break;
        }
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

            Intent i=new Intent(SaleReportActivity.this,ActivityLoyalityCust.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

