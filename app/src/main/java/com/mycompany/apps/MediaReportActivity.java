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

public class MediaReportActivity extends Activity implements View.OnClickListener{
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_report);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        Button MediaMainAdvtBtn=(Button)findViewById(R.id.MediaReportmainAdvertsmnt);
        Button MediaContxtSens1Btn=(Button)findViewById(R.id.MediaReportConSens1);
        Button MediaContxtSens2Btn=(Button)findViewById(R.id.MediaReportConSens2);
        Button MediaLogoSpace1Btn=(Button)findViewById(R.id.MediaReportLogoSpace1);
        Button MediaLogoSpace2Btn=(Button)findViewById(R.id.MediaReportLogoSpace2);
        Button MediaRetalrPayBtn=(Button)findViewById(R.id.MediaReportRetlrPayout);

        MediaMainAdvtBtn.setOnClickListener(this);
        MediaContxtSens1Btn.setOnClickListener(this);
        MediaContxtSens2Btn.setOnClickListener(this);
        MediaLogoSpace1Btn.setOnClickListener(this);
        MediaLogoSpace2Btn.setOnClickListener(this);
        MediaRetalrPayBtn.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.MediaReportmainAdvertsmnt:
                Intent intent = new Intent(this, MainAdvertisementReportActivity.class);
                startActivity(intent);
                break;
            case R.id.MediaReportConSens1:
                Intent in = new Intent(this, AdTickerReportActivity.class);
                startActivity(in);
                break;
            case R.id.MediaReportConSens2:
                Intent intent3 = new Intent(this, AdTickerReportActivity.class);
                startActivity(intent3);
                break;
            case R.id.MediaReportLogoSpace1:
                Intent in4 = new Intent(this, BlinkingLogoReportActivity.class);
                startActivity(in4);
                break;
            case R.id.MediaReportLogoSpace2:
                Intent intent5 = new Intent(this, BlinkingLogoReportActivity.class);
                startActivity(intent5);
                break;
            case R.id.MediaReportRetlrPayout:
                Intent in6 = new Intent(this, MainAdvertisementReportActivity.class);
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

            Intent i=new Intent(MediaReportActivity.this,ActivityLoyalityCust.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
