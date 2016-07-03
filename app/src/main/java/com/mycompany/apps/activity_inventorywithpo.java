package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.godbtech.sync.GBrowserNotifyMessageEvt;
import com.godbtech.sync.GNativeSync;
import com.godbtech.sync.GSyncServerConfig;
import com.godbtech.sync.GSyncStatusEvt;
import com.godbtech.sync.GSyncable;
import com.godbtech.sync.GUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import Adapter.InventoryProductDropdownAdapter;
import Adapter.Inventorygrnadapter;
import Adapter.Inventorywithpoadapter;
import Adapter.PurchaseproductlistwithpoAdapter;
import Pojo.Inventorygrnmodel;
import Pojo.PurchaseProductModelwithpo;


public class activity_inventorywithpo extends Activity implements GSyncable{
    public static final int MIN_LENGTH_OF_BARCODE_STRING = 13;
    public static final String BARCODE_STRING_PREFIX = "@";
  //Autocompletetext views
    AutoCompleteTextView InvProductidorName;
    AutoCompleteTextView autoCompleteTextView;
  //Adapter class
    PurchaseproductlistwithpoAdapter productListAdapter;
    Inventorygrnadapter grnnumberlistadapter;
    InventoryProductDropdownAdapter inventoryProductDropdownAdapter;
 //pojo class
    ArrayList<PurchaseProductModelwithpo> dropdownProductArrayList;
    ArrayList<PurchaseProductModelwithpo>  HoldInventoryList;
 //textwatcher
    private TextWatcher mTextWatcher;
    private TextWatcher ListTextWatcher;

    String x_imei,x1;
    String Store_Id;
    TextView vendorname;
    TextView Ponumbers;
    String userTypedInvoiceno;
    String Spinnervalue,HoldInventoryorderNo;
    EditText quantity;
    private TextView GrandTotal,Billno;
    private TextView Totalitems;
    ActionBar actionBar;
    Button update ,Holdinventorybutton;
    DBhelper helper;
    ArrayList<PurchaseProductModelwithpo> arrayList;
    Inventorywithpoadapter adapter;
    ListView listView;
    TelephonyManager tel;
    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));

//*********************************filter***************************************************************
    Bundle syncDataBundle = null;
    public static final String GCM_PROJECT_ID = "407176891585";//only if you need GCM notifications
    private boolean    syncInProgress = false;
    private boolean    didSyncSucceed = false;
    private GSyncStatusHandler gSyncStatusHandler = new GSyncStatusHandler();
    private GBrowserNotifyHandler gBrowserNotifyHandler = new GBrowserNotifyHandler();
    private GSyncResultHandler gSyncResultNotifyHandler = new GSyncResultHandler();
    private GSyncItemStatusHandler gSyncItemStatusHandler = new GSyncItemStatusHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_inventorywithpo);

        update = (Button) findViewById(R.id.addtolist_button);
        Button clrbtn = (Button) findViewById(R.id.clear);
        vendorname=(TextView)findViewById(R.id.productvendor);
        Ponumbers=(TextView)findViewById(R.id.Po_numbers);
        tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Billno = (TextView) findViewById(R.id.sales_billno);
        Billno.setText(tel.getDeviceId().toString());
        Holdinventorybutton=(Button)findViewById(R.id.HoldInventorybill);
        GrandTotal = (TextView) findViewById(R.id.discount_textt10);
        Totalitems = (TextView) findViewById(R.id.discount_textt);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);
        helper = new DBhelper(this);
        autoCompleteTextView = (CustomAuto) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);
        InvProductidorName = (CustomAuto) findViewById(R.id.InventoryautoProductIdandName);
        InvProductidorName.setThreshold(1);

        listView = (ListView) findViewById(R.id.listView);
        SelectPurchaseAlertDialog();
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
                arrayList = helper.getvendorsearch();
                // ArrayList<String>vendornames=helper.getvendorsearchforSpinner();
                if (arrayList != null) {
                    if (adapter == null) {
                        adapter = new Inventorywithpoadapter(activity_inventorywithpo.this, android.R.layout.simple_dropdown_item_1line, arrayList);
                        adapter.setList(arrayList);

                        autoCompleteTextView.setAdapter(adapter);
                        autoCompleteTextView.setThreshold(1);
                    } else {
                        adapter.setList(arrayList);
                        adapter.notifyDataSetChanged();

                    }

                }
            }
        };


        autoCompleteTextView.addTextChangedListener(mTextWatcher);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();
            }
        });


        helper = new DBhelper(this);
        ListTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (autoCompleteTextView.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Please select the vendor Or Distributor ", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.i("&&&&&&&&", "After text changed called and text value is" + s.toString());
                if (InvProductidorName.isPerformingCompletion()) {
                    Log.i("&&&&&&&&", "Performing completion and hence drop down will not be shown ");
                    return;
                }
                String userTypedString = InvProductidorName.getText().toString().trim();
                if (userTypedString.equals("")) {
                    return;
                }

                if(userTypedString.startsWith(BARCODE_STRING_PREFIX) ) {
                    if(dropdownProductArrayList!= null) {
                        dropdownProductArrayList.clear();
                    }
                    //this is a barcode generated string
                    if(userTypedString.length() <= MIN_LENGTH_OF_BARCODE_STRING) {
                        //ignore all strings of length < 13
                        return;
                    }
                    dropdownProductArrayList = helper.getProductdataforInventory(userTypedString.substring(1));
                    //dropdownProductArrayList = helper.getProductdata(userTypedString);
                    if(dropdownProductArrayList.size() == 1) {
                        //we have found the product
                        addProductToPurchaseList(dropdownProductArrayList.get(0));
                        return;
                    } else if( dropdownProductArrayList.size() < 1) {
                        //no product found
                        Toast.makeText(activity_inventorywithpo.this, "No Product found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    dropdownProductArrayList = helper.getProductdataforInventory(userTypedString);
                    if( dropdownProductArrayList.size() < 1) {
                        //no product found
                        Toast.makeText(activity_inventorywithpo.this, "No Product found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (inventoryProductDropdownAdapter == null) {
                    inventoryProductDropdownAdapter = new InventoryProductDropdownAdapter(activity_inventorywithpo.this, android.R.layout.simple_dropdown_item_1line, dropdownProductArrayList);
                    inventoryProductDropdownAdapter.setList(dropdownProductArrayList);
                    InvProductidorName.setAdapter(inventoryProductDropdownAdapter);
                    InvProductidorName.setThreshold(1);
                }
                else {
                    inventoryProductDropdownAdapter.setList(dropdownProductArrayList);
                    inventoryProductDropdownAdapter.notifyDataSetChanged();
                }
            }

        };
        InvProductidorName.addTextChangedListener(ListTextWatcher);
        InvProductidorName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Cursor curral=(Cursor)parent.getItemAtPosition(position);
                // PurchaseProductModel resval1=(PurchaseProductModel) parent.getItemAtPosition(position);

                PurchaseProductModelwithpo resval1 = (PurchaseProductModelwithpo) parent.getItemAtPosition(position);
                addProductToPurchaseList(resval1);
                productListAdapter.setBatchdata(resval1);
            }

        });




        //********************************************submit button***************************************************8
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(Buttonok);

                try {
                    Long Value = System.currentTimeMillis();
                    final String result = Long.toString(Value);
                    String invoicevalue = Billno.getText().toString();
                    ArrayList<String> billno = helper.getimeino();
                    for (String str : billno) {
                        if (str.equals(invoicevalue)) {
                            ArrayList<String> imei = helper.getprefix(str);
                            Log.e("%%%%%%%%%%%%%", imei.toString());
                            x_imei = imei.toString();
                            x1 = x_imei.replace("[", "").replace("]", "").concat(result);
                            Log.e("X1_imei is :", x1);
                        } else {
                            continue;
                        }
                    }
                    helper.saveInventorywithpo(productListAdapter.getList(), autoCompleteTextView.getText().toString(), Ponumbers.getText().toString(), x1);
                    helper.saveGranddataintoGrnMaster(x1, Ponumbers.getText().toString(), autoCompleteTextView.getText().toString(), GrandTotal.getText().toString());
                    helper.SavePdfDetailForInventorywithpo(x1, autoCompleteTextView.getText().toString());
                    //  helper.updatebatchnowithpo(productListAdapter.getList());
                    Toast.makeText(getApplicationContext(), x1.toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Activitypurchase.class);
                    startActivity(intent);
                    loadSyncLibrary();
                    doSync(syncDataBundle);
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                } catch (IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
        });
//********************************************hiddon button***************************************************8

        Holdinventorybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);
                helper = new DBhelper(activity_inventorywithpo.this);

                if (autoCompleteTextView.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Select Vendor Or Distributor Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (productListAdapter.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No Product Selected", Toast.LENGTH_SHORT).show();

                    return;
                }
                try {
                    Long Value = System.currentTimeMillis();
                    final String result = Long.toString(Value);
                    String invoicevalue = Billno.getText().toString();
                    ArrayList<String> billno = helper.getimeino();
                    for (String str : billno) {
                        if (str.equals(invoicevalue)) {
                            ArrayList<String> imei = helper.getprefix(str);
                            Log.e("%%%%%%%%%%%%%", imei.toString());
                            x_imei = imei.toString();
                            x1 = x_imei.replace("[", "").replace("]", "").concat(result);
                            Log.e("X1_imei is :", x1);
                        } else {
                            continue;
                        }
                    }

                    helper.saveInventoryholdbillwithpo(productListAdapter.getList(), autoCompleteTextView.getText().toString(), Ponumbers.getText().toString(), x1);

                    Toast.makeText(getApplicationContext(), x1.toString(), Toast.LENGTH_SHORT).show();

                    Intent syncIntent = new Intent(getApplicationContext(), com.mycompany.apps.Activitypurchase.class);
                    syncIntent.putExtras(syncDataBundle);
                    startActivityForResult(syncIntent, GoSyncActivity.INTENT_CODE);
                    loadSyncLibrary();
                    doSync(syncDataBundle);


                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                } catch (IndexOutOfBoundsException ex) {
                    ex.printStackTrace();
                }
            }
        });
        //********************************************cencel button***************************************************8
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);

                try {

                    productListAdapter.clearAllRows();
                    autoCompleteTextView.setText("");
                    Ponumbers.setText("");
                    // Ponumbers.setAdapter(null);
                    setSummaryRow();
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    private void addProductToPurchaseList(PurchaseProductModelwithpo resval1) {
        if (productListAdapter == null) {
            Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
            productListAdapter = new PurchaseproductlistwithpoAdapter(activity_inventorywithpo.this, new ArrayList<PurchaseProductModelwithpo>(), android.R.layout.simple_dropdown_item_1line, resval1);
            listView.setAdapter(productListAdapter);
        }
        productListAdapter.addProductToList(resval1);
        Log.i("&&&&&&&&", "Adding " + resval1 + " to Product List..");
        productListAdapter.notifyDataSetChanged();
        InvProductidorName.setText("");
        setSummaryRow();
        // setFocusOnBarcodeView();
        if (dropdownProductArrayList!=null) {
            return;
        } //dropdownProductArrayList.clear();
        inventoryProductDropdownAdapter.setList(dropdownProductArrayList);

    }

    //**************************************8alertbox***********************************************************
    public void SelectPurchaseAlertDialog() {

        LayoutInflater inflater = getLayoutInflater();

        final View alertLayout = inflater.inflate(R.layout.inventory_alert_dialog, null);

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final Button po = (Button) alertLayout.findViewById(R.id.po);
        final Button withoutpo = (Button) alertLayout.findViewById(R.id.withpo);
        final Button cancel = (Button) alertLayout.findViewById(R.id.cancelinventoryalert);
        final Button submit = (Button) alertLayout.findViewById(R.id.submitbtn);
        final Spinner spinner = (Spinner) alertLayout.findViewById(R.id.VendororDistributorName);

        final Button Hold=(Button)alertLayout.findViewById(R.id.HoldButton);
        final Button SubmitForHold=(Button)alertLayout.findViewById(R.id.submitbtnForHold);
        final Spinner Holddata=(Spinner)alertLayout.findViewById(R.id.HoldPurchaseNoforinventory);

        final Spinner Last3InvoiceNo = (Spinner) alertLayout.findViewById(R.id.Last3InvoiceNo);
        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);


        Last3InvoiceNo.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.INVISIBLE);
        cancel.setVisibility(View.VISIBLE);
        Hold.setVisibility(View.VISIBLE);
        Holddata.setVisibility(View.GONE);
        SubmitForHold.setVisibility(View.GONE);
        alert.setView(alertLayout);
        alert.setCancelable(true);
        final AlertDialog dialog = alert.create();


        po.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                withoutpo.setVisibility(View.INVISIBLE);
                Last3InvoiceNo.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                SubmitForHold.setVisibility(View.GONE);
                Holddata.setVisibility(View.GONE);
                Hold.setVisibility(View.GONE);
            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);

                Intent intent = new Intent(getApplicationContext(), Activitypurchase.class);
                startActivity(intent);
            }
        });
        withoutpo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);


                Intent intent = new Intent(getApplicationContext(), activity_inventory.class);
                startActivity(intent);

            }
        });


        Hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Holddata.setVisibility(View.VISIBLE);
                Last3InvoiceNo.setVisibility(View.GONE);
                po.setVisibility(View.GONE);
                withoutpo.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                SubmitForHold.setVisibility(View.VISIBLE);
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);

                Spinnervalue = spinner.getSelectedItem().toString();
                userTypedInvoiceno = Last3InvoiceNo.getSelectedItem().toString().trim();
                Log.e("!!!!!!!!!!!", "" + Last3InvoiceNo);
                if (userTypedInvoiceno.equals("")) {
                    return;
                }

                ArrayList<PurchaseProductModelwithpo> alldata = helper.getPurchaseProductdata(userTypedInvoiceno);
                //   PurchaseProductModelwithpo resval1 = (PurchaseProductModelwithpo) parent.getItemAtPosition(position);
                Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                productListAdapter = new PurchaseproductlistwithpoAdapter(activity_inventorywithpo.this, new ArrayList<PurchaseProductModelwithpo>(), android.R.layout.simple_dropdown_item_1line, null);
                listView.setAdapter(productListAdapter);

                for (PurchaseProductModelwithpo prod : alldata) {
                    productListAdapter.addProductToList(prod);
                    productListAdapter.setBatchdata(prod);
                }
                productListAdapter.notifyDataSetChanged();
                //autoCompleteTextView.setText("");
                setSummaryRow();
                autoCompleteTextView.setText("" + spinner.getSelectedItem().toString() + "");
                autoCompleteTextView.removeTextChangedListener(mTextWatcher);

                Ponumbers.setText(userTypedInvoiceno);
                //  Log.i("&&&&&&&&!", "Adding " + OldPurchaseArraylist.get(0) + " to Product List..");
                productListAdapter.notifyDataSetChanged();
                setSummaryRow();
                autoCompleteTextView.setEnabled(false);
                dialog.dismiss();


            }
        });
        arrayList = helper.getvendorsearch();
        ArrayAdapter<PurchaseProductModelwithpo> stringArrayAdapter =
                new ArrayAdapter<PurchaseProductModelwithpo>(activity_inventorywithpo.this, android.R.layout.simple_spinner_dropdown_item, arrayList);
        spinner.setAdapter(stringArrayAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), "OnItemSelected  :" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                String Spinnervalue = spinner.getSelectedItem().toString();
                Log.e("*************", Spinnervalue);
                ArrayList<String> LastInvoices = helper.getPo_numbers(Spinnervalue);
                ArrayAdapter<String> InvoiceNoAdapter =
                        new ArrayAdapter<String>(activity_inventorywithpo.this, android.R.layout.simple_spinner_dropdown_item, LastInvoices);
                Last3InvoiceNo.setAdapter(InvoiceNoAdapter);
                Last3InvoiceNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        SubmitForHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (HoldInventoryorderNo == null)
                    {
                        Toast.makeText(getApplicationContext(), "No Hold Item Found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    HoldInventoryList = helper.getholddataforinventory(HoldInventoryorderNo);
                    if (HoldInventoryList != null && HoldInventoryList.size() > 0) {
                        if (productListAdapter == null) {
                            Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                            productListAdapter = new PurchaseproductlistwithpoAdapter(activity_inventorywithpo.this, new ArrayList<PurchaseProductModelwithpo>(), android.R.layout.simple_dropdown_item_1line, null);
                            listView.setAdapter(productListAdapter);
                        }
                        for (PurchaseProductModelwithpo prod : HoldInventoryList) {
                            productListAdapter.addProductToList(prod);
                            // Ponumbers.findViewById(R.id.Po_numbers);
                        }

                        autoCompleteTextView.setText(HoldInventoryList.get(0).getVendorName());
                        // Ponumbers.setText(HoldInventoryList.get(0).getPurchaseno());
                        //   autoCompleteTextView.setText("" + Holddata.getSelectedItem().toString() + "");
                        helper.Updateflag(HoldInventoryorderNo);
                        autoCompleteTextView.removeTextChangedListener(mTextWatcher);
                        autoCompleteTextView.setEnabled(false);
                        Log.i("&&&&&&&&!", "Adding " + HoldInventoryList.get(0) + " to Product List..");
                        productListAdapter.notifyDataSetChanged();
                        setSummaryRow();
                        dialog.dismiss();


                    }

            }
        });


        ArrayList<Inventorygrnmodel> LastInvoices = helper.getgrnNumberForinventory();
        grnnumberlistadapter = new Inventorygrnadapter(activity_inventorywithpo.this, android.R.layout.simple_dropdown_item_1line, LastInvoices);
        Holddata.setAdapter(grnnumberlistadapter);
        Holddata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Inventorygrnmodel resval1 = (Inventorygrnmodel) parent.getItemAtPosition(position);
                HoldInventoryorderNo = resval1.getInventoryOrderNo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

    }


    public void setSummaryRow() {
        float Getval = productListAdapter.getGrandTotal();
        Log.d("&&&&&&&&", "" + Getval);
        String GrandVal = Float.toString(Getval);
        GrandTotal.setText(GrandVal);

        int Getitem = productListAdapter.getCount();
        int Allitem = Getitem;
        String GETITEM = Integer.toString(Allitem);
        Totalitems.setText(GETITEM);
    }
    @Override
    protected void onStart()
    {
        syncDataBundle = getSyncBudle();//get sync parameters as bundle
        //these can be obtained from a form
        //setSyncParamView(syncDataBundle);//display the parameters
        super.onStart();
    }




    protected  Bundle getSyncBudle()
    {
        Bundle b = new Bundle();

        //db name, this will be available in that package
        //do not change this, this is the only directory where this activity can write to
        //specifying another directory will cause the sync to fail

        String dbName = "/data/data/" + GMainActivity.class.getPackage().getName() + "/sync.bdb.db";

      /*
      //if you are using encrypted SQLITE Sync agent use code below
      //Note using this code on an unencrypted SQlite Sync agent will not work
      //For obtaining SQLITE encrypted Sync Agent contact GoDB support
      //start
      String key     = "mykey";
      //NOTE: Important, For demonstration purposes key is being hardcoded here,
      //In production this key should not be available stored on the device
      //or hardcoded into the application. How to handle key is outside the scope
      //of this demo app.
      //start SQLITE ENCRYPTION
      String algorithm = "rc4";//RC4,AES128-OFB,AES258-OFB are currently Supported algorithms
      boolean shouldKey = true;
      if(Util.doesFileExist(dbName))
      {
         if(Util.isSqliteEncrypted(dbName))//if sqlite is already encrypted, lets rekey it
         {
            shouldKey = false;
            dbName = dbName + ";rekey="+algorithm+":"+key;
         }
      }
      if(shouldKey)
         dbName = dbName + ";key="+algorithm+":"+key;//if sqlite is not yet created, or is not encrypted, lets key it
      //end SQLITE ENCRYPTION
       */
        DBhelper mydb = new DBhelper(activity_inventorywithpo.this);
        ArrayList<String>value = mydb.getStoreid();
        String filterstoreid = value.toString().replace("[", "").replace("]", "");
        b.putString("dbName", dbName);

        b.putString("syncServerUsername",  filterstoreid);//sync user name
        //b.putString("syncServerPassword", "8776F108E247AB1E2B323042C049C266407C81FBAD41BDE1E8DFC1BB66FD267E");//SHA-256 sync server password
        b.putString("syncServerPassword", GUtils.getSHADigest("SHA-256", "Admin@123"));//SHA-256 sync server password
        //b.putString("syncServerPassword",    GUtils.getMD5Hash("mypassword"));//MD5 server password
        b.putString("syncServerUserID",    "3");//if needed

        b.putInt("syncMode",            GSyncServerConfig.SYNC_DELTA);//delta sync
        b.putBoolean("logEnabled", true);//logs "http.log", "sync.log", "syncstat.log" will be available in
        //folder /data/data/getClass().getPackage().getName()
        //after synchronization
        b.putString("syncServerIP", "52.76.28.14");//sync server ip/domain
        //b.putString("syncServerIP",     "https://www.myserver.com");//sync server ip/domain if https is supported
        //if https is supported please make sure its "https://" + servername

        b.putInt("syncServerPort", 8080);//sync server http port
        //b.putInt("syncServerPort",      443);//sync server https port if SSL is enabled

        b.putString("syncServerBasePath", "/godbss/");//sync server basepath ex: http://www.yourdomain.com/godbss/, here godbss is the basepath

        //socket parameters
        b.putInt("sockConnectTimeoutMillis", 5000);//five seconds
        b.putInt("sockSendTimeoutMillis",  30000);//30 seconds
        b.putInt("sockRecvTimeoutMillis", 30000);//30 seconds

        //proxy parameters if needed
        b.putBoolean("proxyEnabled", false);
        b.putString("httpProxy", "192.168.0.134");//only valid if proxyEnabled is set to true
        b.putInt("httpProxyPort",        3128);//only valid if proxyEnabled is set to true

        b.putString("d4s",                 "Synctype=RS_Inv");//D4SSynctype=RS_Inv

        //b.putString("chunkedTableList",  "meap_order_item");//name of the table which you want to download in chunks
        //b.putInt("maxRecChunkSize",     10);//chunk size in number of records

        b.putString("gcmProjectID",       GCM_PROJECT_ID);//only if you need GCM notifications

        return b;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch(requestCode)
        {
            case GoSyncActivity.INTENT_CODE:
                if(resultCode == RESULT_OK)
                {
                    boolean b = data.getBooleanExtra("syncstat", false);
                    if(b)
                    {
                        Toast.makeText(this, "Sync Succeded", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(this, "Sync Failed", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    protected GSyncServerConfig getSyncConfigFromSyncIntent(Bundle syncDataBundle) {
        GSyncServerConfig gCfg = new GSyncServerConfig();
        gCfg.setSyncServerAddress(syncDataBundle.getString("syncServerIP"));// set SyncServer Address, can be IP
        gCfg.setSyncServerPort(syncDataBundle.getInt("syncServerPort", 80));// set SyncServer Port
        gCfg.setSyncServerBasePath(syncDataBundle.getString("syncServerBasePath"));// set Base path
        gCfg.setSyncServerUsername(syncDataBundle.getString("syncServerUsername"));
        gCfg.setSyncServerPassword(syncDataBundle.getString("syncServerPassword"));
        gCfg.setSyncServerUserID(syncDataBundle.getString("syncServerUserID"));
        gCfg.setLogEnabled(true);//enable logging
        gCfg.setDBName(syncDataBundle.getString("dbName"));//db name
        gCfg.setSyncMode(syncDataBundle.getInt("syncMode", 1));// set Synchronization to full sync if missing
        gCfg.setUseProxy(syncDataBundle.getBoolean("proxyEnabled", false));
        gCfg.setHttpProxy(syncDataBundle.getString("httpProxy"));
        gCfg.setHttpProxyPort(syncDataBundle.getInt("httpProxyPort", 8080));
        gCfg.setSockConnectTimeoutMillis(syncDataBundle.getInt("sockConnectTimeoutMillis", 0));
        gCfg.setSockSendTimeoutMillis(syncDataBundle.getInt("sockSendTimeoutMillis", 0));
        gCfg.setSockRecvTimeoutMillis(syncDataBundle.getInt("sockRecvTimeoutMillis", 0));
        gCfg.setD4S(syncDataBundle.getString("d4s"));
        gCfg.setChunkedTableList(syncDataBundle.getString("chunkedTableList"));
        gCfg.setMaxRecChunkSize(syncDataBundle.getInt("maxRecChunkSize", 0));
        gCfg.setWithDSEnabled(syncDataBundle.getBoolean("withDsEnabled", false));//withds=1
        //Log.d("GSync", "Syncing with " + gCfg.toString());
        return gCfg;
    }


    public void doSync(Bundle syncDataBundle)
    {
        GNativeSync gNSync = GNativeSync.getNativeSyncSingleton();

        gNSync.addSyncListener(this);// important register sync listener to recieve notifications
        GSyncServerConfig gCfg = getSyncConfigFromSyncIntent(syncDataBundle);
        String rootPath = syncDataBundle.getString("dbName");
        if(rootPath==null || rootPath.lastIndexOf("/")<=0) return;
        rootPath = rootPath.substring(0, rootPath.lastIndexOf("/"));
        extractPEMFile(getAssets(), rootPath);//extracts on every launch, you can change it to extract only if file isnt present.
        //clearViews();
        initDB(gCfg.getDBName(), gNSync._hasCodec());
        gNSync.startSync(gCfg);

    }



// private void clearViews()
// {
//    statusEditText.setText("");
// }

    @Override
    public void syncStatusEvent(GSyncStatusEvt statEvt) {
        String msg = statEvt.getMsg();
        if (msg == null)
            msg = "";
        Bundle b = new Bundle();
        b.putString("syncstatusmsg", msg);
        b.putInt("status1", statEvt.getTStatus1());
        b.putInt("status2max", statEvt.getStatus2Max());
        b.putInt("status2", statEvt.getStatus2());
        b.putInt("status3max", statEvt.getStatus3Max());
        b.putInt("status3", statEvt.getStatus3());
        Message m = Message.obtain();
        m.setData(b);

        if (statEvt.getTStatus1() == 100)//100 sync failed 200 sync succeeded
        {
            syncInProgress = false;
            didSyncSucceed = false;
//            Toast.makeText(this, "Sync failed", Toast.LENGTH_LONG).show();
            Log.i("GSync", "Sync failed");
            m.setTarget(gSyncResultNotifyHandler);
        } else if (statEvt.getTStatus1() == 200)//sync succeeded
        {
            didSyncSucceed = true;
            syncInProgress = false;
            //  Toast.makeText(this, "Sync Success", Toast.LENGTH_LONG).show();
            Log.i("GSync", "Sync Success");
            m.setTarget(gSyncResultNotifyHandler);
        } else
            m.setTarget(gSyncStatusHandler);
        m.sendToTarget();
        Log.i("GSync :: syncStatus", statEvt.toString());
    }

    @Override
    public void browserNotifyEvent(GBrowserNotifyMessageEvt gNMsgEvt) {
        String msg = null;
        Bundle b = new Bundle();
        Message m = Message.obtain();
        msg = gNMsgEvt.getNotifySrc() + ":" + gNMsgEvt.getMsgType();
        m.setTarget(gBrowserNotifyHandler);
        b.putString("browsernotifymsg", msg);
        m.setData(b);
        m.sendToTarget();
        Log.i("GSync", gNMsgEvt.toString());
    }

    @Override
    public void itemStatusEvent(String ItemName, String Msg) {
        Bundle b = new Bundle();
        Message m = Message.obtain();
        m.setTarget(gSyncItemStatusHandler);
        b.putString("itemname", ItemName);
        b.putString("msg", Msg);
        m.setData(b);
        m.sendToTarget();
        Log.i("GSync", "onItemStatus " + ItemName + " : " + Msg);
    }


    private void updateStatus(Message msg) {
        //if(status==null) return;
        Bundle bundle = msg.getData();
        int s1 = bundle.getInt("status1");
        int s2 = bundle.getInt("status2");
        int s2max = bundle.getInt("status2max");
        int s3 = bundle.getInt("status3");
        int s3max = bundle.getInt("status3max");
//
//    spb1.setMax(5);//currently fixed at 5
//    spb1.setProgress(s1);
//
//    //Log.i("GSync", "status1 " + s1);
//
//    spb2.setMax(s2max);
//    spb2.setProgress(s2);
//
//    spb3.setMax(s3max);
//    spb3.setProgress(s3);

        System.out.println("status2max " + bundle.getInt("status2max"));
        Log.i("GSync", "status2max " + bundle.getInt("status2max"));
    }

    private void updateNotify(String notification) {
        if (notification == null) return;
        //currently not used
    }

    private void updateResult(String notification) {
        if (notification == null) return;
        //currently not used
        //  Toast.makeText(this, "Sync Succeded", Toast.LENGTH_LONG).show();
    }

    private void updateItemStatus(String itemName, String msg) {
        if (itemName == null) itemName = "";
        if (msg == null) msg = "";
//    String t = statusEditText.getText().toString();
//    if(t==null)t="";
//    statusEditText.append(msg + " " + itemName + "\n");
//    scrollView.post(new Runnable()
//    {
//       public void run()
//       {
//          scrollView.smoothScrollTo(0, statusEditText.getBottom());
//       }
//    });
        Log.i("GSync", "->" + itemName + " " + msg + "\n");
    }


    class GSyncStatusHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {activity_inventorywithpo.this.updateStatus(msg);
        }
    }

    ;

    class GBrowserNotifyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            activity_inventorywithpo.this.updateNotify(msg.getData().getString("browsernotifymsg"));
        }
    }

    ;

    class GSyncResultHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            activity_inventorywithpo.this.updateResult(msg.getData().getString("syncstatusmsg"));
        }
    }

    ;

    class GSyncItemStatusHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            activity_inventorywithpo.this.updateItemStatus(msg.getData().getString("itemname"), msg.getData().getString("msg"));
        }
    }

    ;

    @Override
    public void onBackPressed() {
        if (syncInProgress) {
            //       Toast.makeText(this, "Sync in process.. Please wait for it to complete.", Toast.LENGTH_LONG).show();
            return;
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("syncstat", didSyncSucceed);
        setResult(RESULT_OK, returnIntent);
        finish();
    }


    /**
     * extract ROOT.PEM to /data/data/yourpackage/ if it exists
     * this is to support https based synchronization
     */
    void extractPEMFile(AssetManager mgr, String rootPath) {
        try {
            String[] filelist = mgr.list("");
            for (String asset : filelist) {
                if (asset.equalsIgnoreCase("ROOT.pem")) {
                    File f = new File(rootPath, "ROOT.pem");
                    FileOutputStream os = new FileOutputStream(f);
                    byte[] buf = new byte[200 * 1024];
                    int n;
                    InputStream is = mgr.open(asset);
                    while ((n = is.read(buf)) > 0)
                        os.write(buf, 0, n);
                    os.close();
                    is.close();
                }
            }
        } catch (Exception e) {
        }
    }

    //this is just to create a blank database if doesnt exist
    static void initDB(String dbName, int hasCodec)
    {
        String keyStr="";
        com.godbtech.sql.database.sqlite.SQLiteDatabase db = null;
        if(hasCodec==1)
        {
            if(dbName.indexOf(";")>0&&dbName.indexOf("=")>0)
            {
                keyStr="key='"+dbName.substring(dbName.indexOf("=")+1)+"'";
                dbName = dbName.substring(0, dbName.indexOf(";"));
            }
            db = com.godbtech.sql.database.sqlite.SQLiteDatabase.openOrCreateDatabase(dbName, null);
            if(keyStr.length()>0)
            {
                String pragma="PRAGMA "+keyStr;
                db.execSQL(pragma);//set the key
                Log.i("GSync", "testEncryptedDB:::pragma:::" + pragma);
            }
        }
        else
        {
            if(dbName.indexOf(";")>0)
                dbName=dbName.substring(0, dbName.indexOf(";"));
            db = com.godbtech.sql.database.sqlite.SQLiteDatabase.openOrCreateDatabase(dbName, null);
        }
        com.godbtech.sql.database.sqlite.SQLiteStatement st = null;;
        String res = null;
        try
        {
            st = db.compileStatement("SELECT sqlite_version()");
            res = st.simpleQueryForString();
            //st.close();
            //db.execSQL("CREATE TABLE IF NOT EXISTS t1(x, y)");
            //db.execSQL("delete from t1");
            //db.execSQL("INSERT INTO t1 VALUES (1, 2), (3, 4)");
            //st = db.compileStatement("SELECT sum(x+y) FROM t1");
            //res = st.simpleQueryForString();
            Log.i("GSync", "sqlite_version():::" + res);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(st!=null)
                st.close();//dont forget to close the db
            if(db!=null)
                db.close();//dont forget to close the db
        }
    }

    /**
     * Load sync library.
     */
    private void loadSyncLibrary() {
        try {
            String trgLib = "gSyncDLL";
            System.loadLibrary(trgLib);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("GSync", e.getMessage());
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
            Intent i=new Intent(activity_inventorywithpo.this,Activitypurchase.class);
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


    private void calcResult() throws NumberFormatException {
        //  Editable editableText1 = Mrp.getText();
        Editable editableText2 = quantity.getText();
        double result, value1 = 0.0, Mrp = 0.0;
        if (editableText2 != null && editableText2.length() >= 1)
            value1 = Double.parseDouble(editableText2.toString());
        result = value1 * Mrp;

    }








}