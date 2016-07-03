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

public class MasterDataReportActivity extends Activity implements View.OnClickListener {
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_data_report);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        Button MasterDbtrBtn=(Button)findViewById(R.id.MasterdataReportForDistributor);
        Button MasterVendBtn=(Button)findViewById(R.id.MasterdataReportForVendor);
        Button MasterprodPharmaBtn=(Button)findViewById(R.id.MasterdataReportForProductPharma);
        Button MasterlocalPharmaBtn=(Button)findViewById(R.id.MasterdataReportForProductPharmaLocal);
        Button MasterprodCpgBtn=(Button)findViewById(R.id.MasterdataReportForProductCPG);
        Button MasterlocalCpgBtn=(Button)findViewById(R.id.MasterdataReportForProductCPGLocal);

        MasterDbtrBtn.setOnClickListener(this);
        MasterVendBtn.setOnClickListener(this);
        MasterprodPharmaBtn.setOnClickListener(this);
        MasterlocalPharmaBtn.setOnClickListener(this);
        MasterprodCpgBtn.setOnClickListener(this);
        MasterlocalCpgBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.MasterdataReportForDistributor:
                Intent intent = new Intent(this, DistributorReportActivity.class);
                startActivity(intent);
                break;
            case R.id.MasterdataReportForVendor:
                Intent in = new Intent(this, VendorReportActivity.class);
                startActivity(in);
                break;
            case R.id.MasterdataReportForProductPharma:
                Intent intent3 = new Intent(this, ProductPharmaReportActivity.class);
                startActivity(intent3);
                break;
            case R.id.MasterdataReportForProductPharmaLocal:
                Intent in4 = new Intent(this, LocalProductPharmaReportActivity.class);
                startActivity(in4);
                break;
            case R.id.MasterdataReportForProductCPG:
                Intent intent5 = new Intent(this, ProductCpgReportActivity.class);
                startActivity(intent5);
                break;
            case R.id.MasterdataReportForProductCPGLocal:
                Intent in6 = new Intent(this, LocalProductCpgReportActivity.class);
                startActivity(in6);
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

            Intent i=new Intent(MasterDataReportActivity.this,ActivityLoyalityCust.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
