package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activity_Loyalty_Report extends Activity implements View.OnClickListener{
    ActionBar actionBar;
    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__loyalty__report);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
        //ImageButton imageButton=(ImageButton)findViewById(R.id.loyalityreportimage);
        ImageButton imageButton2=(ImageButton)findViewById(R.id.ruledefinationimage);
        //ImageButton imageButton3=(ImageButton)findViewById(R.id.retailcarddefineimage);

       // imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        //imageButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            /*case R.id.loyalityreportimage:
                Intent intent = new Intent(this, LoyaltyReportActivity.class);
                startActivity(intent);
                break;*/
            case R.id.ruledefinationimage:
                Intent intent1 = new Intent(this, RuleDefinationActivity.class);
                startActivity(intent1);
                break;
            /*case R.id.retailcarddefineimage:
                Intent intent3= new Intent(this, RetailCarddefineActivity.class);
                startActivity(intent3);
                break;*/
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

            Intent i=new Intent(Activity_Loyalty_Report.this,Activity_masterScreen1.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
