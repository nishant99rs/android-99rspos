package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import Adapter.ReportProductCpgListAdapter;
import Adapter.ReportProductCpgSearchAdapter;
import Pojo.ReportProductCpgModel;


public class ProductCpgReportActivity extends Activity {

    ListView listview;
    AutoCompleteTextView autoCompleteTextView;
    private TextWatcher mTextWatcher;
    ReportProductCpgListAdapter productListAdapter;
    ArrayList<ReportProductCpgModel> searchProductList;
    ArrayList<ReportProductCpgModel> arrayProductList;
    ReportProductCpgSearchAdapter searchProductAdapter;
    ActionBar actionBar;
    ArrayList<ReportProductCpgModel>GetAllCpgProduct;
    String ActiveType[];
    Spinner Active;
    String SpinValue;
    ArrayAdapter<String> adapterActiveType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cpg_report);
        final DBhelper helper = new DBhelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        listview = (ListView) findViewById(R.id.lv_ProductCpgReport);
        Log.e("***********Lt1*******", listview.toString());
        autoCompleteTextView = (CustomAuto) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);

        Active=(Spinner)findViewById(R.id.activeProdCpg);
        GetAllCpgProduct=helper.getProductCpgForReport();
        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
        productListAdapter = new ReportProductCpgListAdapter( GetAllCpgProduct ,ProductCpgReportActivity.this);
        listview.setAdapter(productListAdapter);

        ActiveType = getResources().getStringArray(R.array.active_Status_for_Report);
        adapterActiveType = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,ActiveType);
        adapterActiveType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Active.setAdapter(adapterActiveType);
        Active.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinValue = Active.getSelectedItem().toString();
                if (SpinValue.equals("Y"))
                {//rahul
                    GetAllCpgProduct=new ArrayList<ReportProductCpgModel>();
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    productListAdapter = new ReportProductCpgListAdapter( GetAllCpgProduct ,ProductCpgReportActivity.this);
                    listview.setAdapter(productListAdapter);


                    arrayProductList = helper.getCpgReportforActive(SpinValue);
                    if (productListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        productListAdapter = new ReportProductCpgListAdapter( arrayProductList , ProductCpgReportActivity.this);
                        listview.setAdapter(productListAdapter);
                    }
                   /* localproductListAdapter.addProductToList(arraylocalProductList.get(0));
                    Log.i("&&&&&&&&", "Adding " + arraylocalProductList.get(0) + " to Product List..");*/
                    productListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                }else if(SpinValue.equals("N"))
                {//rahul
                    GetAllCpgProduct=new ArrayList<ReportProductCpgModel>();
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    productListAdapter = new ReportProductCpgListAdapter( GetAllCpgProduct ,ProductCpgReportActivity.this);
                    listview.setAdapter(productListAdapter);


                    arrayProductList = helper.getCpgReportforActive(SpinValue);
                    if (productListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        productListAdapter = new ReportProductCpgListAdapter( arrayProductList , ProductCpgReportActivity.this);
                        listview.setAdapter(productListAdapter);
                    }
                    for (ReportProductCpgModel prod:arrayProductList){
                        productListAdapter.addProductToList(prod);
                    }
                    productListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                }
                else
                {   arrayProductList = new ArrayList<ReportProductCpgModel>();
                    if (productListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        productListAdapter = new ReportProductCpgListAdapter( arrayProductList , ProductCpgReportActivity.this);
                        listview.setAdapter(productListAdapter);
                    }
                    productListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /******************************** product name selected from here********************************************************************************************/
        autoCompleteTextView.setThreshold(1);
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
                if (autoCompleteTextView.isPerformingCompletion()) {
                    Log.d("Debuging", "Performing completion ");
                    return;
                }
                String userTypedString = autoCompleteTextView.getText().toString().trim();
                if (userTypedString.equals("")) {
                    return;
                }
                searchProductList = helper.getProductCpgName(userTypedString);
                if (searchProductList != null) {
                    if (searchProductAdapter == null) {
                        searchProductAdapter = new ReportProductCpgSearchAdapter(ProductCpgReportActivity.this, android.R.layout.simple_dropdown_item_1line, searchProductList);
                        searchProductAdapter.setList(searchProductList);

                        autoCompleteTextView.setAdapter(searchProductAdapter);
                        autoCompleteTextView.setThreshold(1);
                    } else {
                        searchProductAdapter.setList(searchProductList);
                        searchProductAdapter.notifyDataSetChanged();
                    }

                }
            }
        };

        autoCompleteTextView.addTextChangedListener(mTextWatcher);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();
                GetAllCpgProduct=new ArrayList<ReportProductCpgModel>();
                Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                productListAdapter = new ReportProductCpgListAdapter( GetAllCpgProduct ,ProductCpgReportActivity.this);
                listview.setAdapter(productListAdapter);
                arrayProductList = helper.getProductCpgReport(Value);
                if (productListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    productListAdapter = new ReportProductCpgListAdapter(new ArrayList<ReportProductCpgModel>(), ProductCpgReportActivity.this);
                    productListAdapter.setList(arrayProductList);
                    listview.setAdapter(productListAdapter);
                }
                else {
                    productListAdapter.setList(arrayProductList);
                    productListAdapter.notifyDataSetChanged();
                    autoCompleteTextView.setText("");
                }
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

            Intent i=new Intent(ProductCpgReportActivity.this,MasterDataReportActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
