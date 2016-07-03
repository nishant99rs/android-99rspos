package com.mycompany.apps;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import Adapter.RuleDefinationAdapter;
import Pojo.RuleDefinationModel;

public class RuleDefinationActivity extends Activity {
    ListView lviewRuledefination;
    RuleDefinationAdapter ruleDefinationAdapter;
    ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule_defination);
        String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
        final DBhelper helper = new DBhelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        final ArrayList<RuleDefinationModel> arrayList=helper.getRuleDefination();
        Log.e("%%%%%%%%%%%%%%%%%%%", arrayList.toString());
        Log.e("*****************", arrayList.toString());
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
        lviewRuledefination=(ListView)findViewById(R.id.lv_retail_RuleDefination);
        ruleDefinationAdapter= new RuleDefinationAdapter(arrayList,this);
        lviewRuledefination.setAdapter(ruleDefinationAdapter);
        ruleDefinationAdapter.notifyDataSetChanged();

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

            Intent i=new Intent(RuleDefinationActivity.this,ActivityLoyalityCust.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
