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
import android.widget.ImageButton;


public class Activitypurchase extends Activity implements View.OnClickListener {
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitypurchase);

        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
        ImageButton purchase = (ImageButton) findViewById(R.id.purchaseimage);
        ImageButton inventory = (ImageButton) findViewById(R.id.inventoryimage);
        ImageButton invoice = (ImageButton) findViewById(R.id.invoiceimage);
        ImageButton vendor = (ImageButton) findViewById(R.id.vendorreturn);
        purchase.setOnClickListener(this);
        inventory.setOnClickListener(this);
        invoice.setOnClickListener(this);
        vendor.setOnClickListener(this);


    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.purchaseimage:
                Intent intent = new Intent(this,PurchaseActivity.class);
                startActivity(intent);
                break;
            case R.id.inventoryimage:
                Intent intent1 = new Intent(this,activity_inventorywithpo.class);
                startActivity(intent1);
                break;
            case R.id.invoiceimage:
                Intent intent2 = new Intent(this, Activityvendorpayment.class);
                startActivity(intent2);
                break;
            case R.id.vendorreturn:
                Intent intent3 = new Intent(this, VendorReturnActivity.class);
                startActivity(intent3);
                break;


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_master_screen2, menu);


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


            Intent i=new Intent(Activitypurchase.this,Activity_masterScreen1.class);
            startActivity(i);
            return true;


        }

        return super.onOptionsItemSelected(item);
    }


}