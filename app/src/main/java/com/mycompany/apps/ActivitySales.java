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
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;


public class ActivitySales extends Activity implements View.OnClickListener{
ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_sales);

        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
        ImageButton dayimage = (ImageButton) findViewById(R.id.dayopenimage);
        ImageButton tenderimage = (ImageButton) findViewById(R.id.Tenderimage);
        ImageButton salesreturnimage = (ImageButton) findViewById(R.id.salesreturnpage);
        ImageButton salesimage = (ImageButton) findViewById(R.id.salesimage);
        dayimage.setOnClickListener(this);
        tenderimage.setOnClickListener(this);
        salesreturnimage.setOnClickListener(this);
        salesimage.setOnClickListener(this);
        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);


    }



    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.dayopenimage:
                Intent intent = new Intent(this, ActivityDayOpen.class);
                startActivity(intent);
                break;
            case R.id.Tenderimage:
                Intent intent1 = new Intent(this, ActivityTender.class);
                startActivity(intent1);
                break;
            case R.id.salesreturnpage:
                Intent intent2 = new Intent(this,ActivitySalesreturn .class);
                startActivity(intent2);
                break;
            case R.id.salesimage:
                Intent intent3 = new Intent(this, ActivitySalesbill.class);
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


            Intent i=new Intent(ActivitySales.this,Activity_masterScreen1.class);
            startActivity(i);
            return true;


        }

        return super.onOptionsItemSelected(item);
    }

}