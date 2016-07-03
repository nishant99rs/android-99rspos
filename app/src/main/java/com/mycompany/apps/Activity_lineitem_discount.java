package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.line_fullproductadapter;
import Adapter.line_productId_Name_adapter;
import Adapter.LineItemDiscount_DropDown;
import Pojo.line_item_Product_Model;

public class Activity_lineitem_discount extends Activity {

    private TextWatcher mTextWatcher;
    DBhelper helper;
    ArrayList<line_item_Product_Model> arrayList;
    ListView listView;
    line_productId_Name_adapter adapter;
    line_fullproductadapter lineproductadapter;
    Button submit;
    Spinner mLineIteam;
    AutoCompleteTextView ProductidorName;
    ActionBar actionBar;
    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_lineitem_discount);


        ProductidorName = (CustomAuto) findViewById(R.id.topproductname);

        listView = (ListView) findViewById(R.id.listView);
        mLineIteam = (Spinner) findViewById(R.id.deletelineiteam);

        //listView.setAdapter(new yourAdapter(this, new String[]{"1","2","3"}));

        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);




        submit = (Button) findViewById(R.id.submit_button);
        Button clrbtn = (Button) findViewById(R.id.clear);


        ProductidorName.setThreshold(1);
        helper = new DBhelper(this);


        final DBhelper helper = new DBhelper(Activity_lineitem_discount.this);
        ArrayList<line_item_Product_Model> reasonReturn = helper.getLineItem();
        LineItemDiscount_DropDown lineItemDiscount_dropDown=new LineItemDiscount_DropDown(reasonReturn,this);

        mLineIteam.setAdapter(lineItemDiscount_dropDown);

        mLineIteam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String   item = mLineIteam.getSelectedItem().toString();


                if (item.matches("")) {
                    return;
                }

                if (adapter == null) {
//

                }
                else if (adapter != null)
                {

                }


//
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });



        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Debuging", "After text changed called ");
                if (ProductidorName.isPerformingCompletion()) {
                    Log.d("Debuging", "Performing completion ");
                    return;
                }
                String userTypedString = ProductidorName.getText().toString().trim();
                if (userTypedString.equals("")) {
                    return;
                }
                arrayList = helper.lineproductnameorid(userTypedString);
                if (arrayList != null) {
                    if (adapter == null) {
                        adapter = new line_productId_Name_adapter(Activity_lineitem_discount.this, android.R.layout.simple_dropdown_item_1line, arrayList);
                        adapter.setList(arrayList);

                        ProductidorName.setAdapter(adapter);
                        ProductidorName.setThreshold(1);
                    } else {
                        adapter.setList(arrayList);
                        adapter.notifyDataSetChanged();

                    }

                }
            }
        };

        ProductidorName.addTextChangedListener(mTextWatcher);
        ProductidorName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                line_item_Product_Model resval = (line_item_Product_Model) parent.getItemAtPosition(position);
                if (lineproductadapter == null) {
                    lineproductadapter = new line_fullproductadapter(Activity_lineitem_discount.this, new ArrayList<line_item_Product_Model>(), android.R.layout.simple_dropdown_item_1line, resval);
                    listView.setAdapter(lineproductadapter);
                }
                lineproductadapter.addProductToList(resval);
                lineproductadapter.notifyDataSetChanged();
                ProductidorName.setText("");

            }
        });




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lineproductadapter.list.size()>=4)
                {
                    Toast.makeText(getApplicationContext(), "Only 3 Items added", Toast.LENGTH_SHORT).show();
                    return;
                }


                helper.linediscountitem(lineproductadapter.getList());
                Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                Intent intent5 = new Intent(Activity_lineitem_discount.this,ActivityLoyality.class);
                startActivity(intent5);
            }


        });


        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                lineproductadapter.clearAllRows();

            }
        });









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

            Intent i=new Intent(Activity_lineitem_discount.this,ActivityLoyality.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
