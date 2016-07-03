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
import android.view.animation.AlphaAnimation;
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
import Adapter.CustomerRejectAdapter;
import Adapter.TopProductDropDownAdapter;
import Adapter.Topfullproductadapter;
import Adapter.TopproductidandnameAdapter;
import Pojo.Topfullproductmodel;


public class Activity_Top_Product extends Activity {
    AutoCompleteTextView ProductidorName;
    private TextWatcher mTextWatcher;
    DBhelper helper;
    ArrayList<Topfullproductmodel> arrayList;
    ListView listView;
    TopproductidandnameAdapter adapter;
    Topfullproductadapter topproductadapter;
    Button submit, clrbtn;
    String Store_Id;
    ActionBar actionBar;
    Spinner spinner;

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__top__product);
        ProductidorName = (CustomAuto) findViewById(R.id.topproductname);
        listView = (ListView) findViewById(R.id.listView);
        submit = (Button) findViewById(R.id.submit_button);
        clrbtn = (Button) findViewById(R.id.clear);
        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
        spinner =(Spinner) findViewById(R.id.topprodspinner);
        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);
        ProductidorName.setThreshold(1);
        helper = new DBhelper(this);
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
                arrayList = helper.topproductnameorid(userTypedString);
                if (arrayList != null) {
                    if (adapter == null) {
                        adapter = new TopproductidandnameAdapter(Activity_Top_Product.this, android.R.layout.simple_dropdown_item_1line, arrayList);
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


        final DBhelper helper = new DBhelper(Activity_Top_Product.this);
        ArrayList<Topfullproductmodel> reasonReturn = helper.gettopProductSpinner();
        final TopProductDropDownAdapter topProductDropDownAdapter=new TopProductDropDownAdapter(reasonReturn,this);
        spinner.setAdapter(topProductDropDownAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String   item = spinner.getSelectedItem().toString();


                if (item.matches("")) {
                    return;
                }
                if (adapter == null) {



                }
                else if (adapter != null)
                {


                }

            }@Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });

        ProductidorName.addTextChangedListener(mTextWatcher);
        ProductidorName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Topfullproductmodel resval = (Topfullproductmodel) parent.getItemAtPosition(position);
                if (topproductadapter == null) {
                    topproductadapter = new Topfullproductadapter(Activity_Top_Product.this, new ArrayList<Topfullproductmodel>(), android.R.layout.simple_dropdown_item_1line, resval);
                    listView.setAdapter(topproductadapter);
                }
                topproductadapter.addProductToList(resval);
                topproductadapter.notifyDataSetChanged();
                ProductidorName.setText("");

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(Buttonok);
                try{
                    if (topproductadapter.list.size() >= 15) {
                        Toast.makeText(getApplicationContext(), "Only 15 product added", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
//                if (topProductDropDownAdapter.getList().isEmpty())
//                {
//                    return;
//                }
                helper.saveTop15Product(topproductadapter.getList());
                Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), ActivityLoyality.class);
                startActivity(intent);
            }
        });
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);
                try {
                    topproductadapter.clearAllRows();
                    ProductidorName.setText("");
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_local_product, menu);
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
            Intent i = new Intent(Activity_Top_Product.this, ActivityLoyality.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}