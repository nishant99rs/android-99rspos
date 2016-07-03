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


public class ActivityScheme extends Activity implements View.OnClickListener {
    ActionBar actionBar;
    ImageButton companylist;
    ImageButton mfglist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_scheme);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
        companylist=(ImageButton)findViewById(R.id.companylist_button);
        mfglist=(ImageButton)findViewById(R.id.mfglistbutton);

        companylist.setOnClickListener(this);
        mfglist.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.companylist_button:

                Intent intent = new Intent(this, Activity_Comp_Btl.class);
                startActivity(intent);
                break;
            case R.id.mfglistbutton:
                Intent intent1 = new Intent(this, Activity_Mfg_btl.class);
                startActivity(intent1);
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


            Intent i=new Intent(ActivityScheme.this,Activity_masterScreen2.class);
            startActivity(i);
            return true;


        }

        return super.onOptionsItemSelected(item);
    }

}
