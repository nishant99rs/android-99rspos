package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by w7 on 1/27/2016.
 */
public class Activity_masterScreen2 extends Activity implements View.OnClickListener{
    ActionBar actionBar;
    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_screen2);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        ImageButton strimage = (ImageButton) findViewById(R.id.master_storemanagement_button);
        ImageButton prodimage = (ImageButton) findViewById(R.id.master_productmanagement_button);
       // ImageButton taximage = (ImageButton) findViewById(R.id.master_taxmanagememt_button);
        ImageButton vendorimage = (ImageButton) findViewById(R.id.master_vendormanagement_button);
        ImageButton schemaimage = (ImageButton) findViewById(R.id.master_schemamanagement_button);
        ImageButton customerimage = (ImageButton) findViewById(R.id.master_custmanagement_button);
        ImageButton localprodimage = (ImageButton) findViewById(R.id.master_localproduct_button);
        ImageButton localvendorimage = (ImageButton) findViewById(R.id.master_local_vendor_button);
        ImageButton localprodimagecpg = (ImageButton) findViewById(R.id.master_localproductcpg_button);
        ImageButton prodcpg = (ImageButton) findViewById(R.id.master_productcpg_button);

        ImageButton doctor= (ImageButton) findViewById(R.id.master__doctor_button);

        strimage.setOnClickListener(this);
        prodimage.setOnClickListener(this);
       // taximage.setOnClickListener(this);
        vendorimage.setOnClickListener(this);
        schemaimage.setOnClickListener(this);
        customerimage.setOnClickListener(this);
        localprodimage.setOnClickListener(this);
        localvendorimage.setOnClickListener(this);
        localprodimagecpg.setOnClickListener(this);
        prodcpg.setOnClickListener(this);
        doctor.setOnClickListener(this);
    }
    @Override
    public void onBackPressed() {
        // nothing to do here
        // … really
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


            Intent i=new Intent(Activity_masterScreen2.this,Activity_masterScreen1.class);
            startActivity(i);
            return true;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.master_storemanagement_button:
                Intent intent = new Intent(this, ActivityStore.class);
                startActivity(intent);
                break;
            case R.id.master_productmanagement_button:
                Intent intent4 = new Intent(this,ActivityProduct.class);
                startActivity(intent4);
                break;
//            case R.id.master_taxmanagememt_button:
//                Intent intent5 = new Intent(this, ActivityTax.class);
//                startActivity(intent5);
//                break;
            case R.id.master_vendormanagement_button:
                Intent intent6 = new Intent(this, ActivityVendor.class);
                startActivity(intent6);
                break;

            case R.id.master_schemamanagement_button:
                Intent intent7 = new Intent(this,ActivityScheme.class);
                startActivity(intent7);
                break;

            case R.id.master_custmanagement_button:
                Intent intent8 = new Intent(this, ActivityCustomer.class);
                startActivity(intent8);
                break;

            case R.id.master_local_vendor_button:
                Intent intent9 = new Intent(this,ActivitylocalVendor .class);
                startActivity(intent9);
                break;

            case R.id.master_localproduct_button:
                Intent intent10 = new Intent(this, ActivityLocalProduct.class);
                startActivity(intent10);
                break;

            case R.id.master_localproductcpg_button:
                Intent intent11 = new Intent(this, Activitylocalproductcpg.class);
                startActivity(intent11);
                break;

            case R.id.master_productcpg_button:
                Intent intent12 = new Intent(this, Activityproductcpg.class);
                startActivity(intent12);
                break;

            case R.id.master__doctor_button:
                Intent intent13 = new Intent(this, Activity_Doctor.class);
                startActivity(intent13);
                break;

        }
    }
}
