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
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import Adapter.VendorNameForVendorReturnAdapter;
import Adapter.VendorReturnProductListAdapter;
import Adapter.VendorReturnSelectProductToreturnAdapter;
import Pojo.VendorModel;
import Pojo.VendorReturnModel;

public class VendorReturnActivity extends Activity implements GSyncable {
    ActionBar actionBar;
    Spinner selectReasonsforVendorreturn;
    TextWatcher SelectProducttoreturn;
    TextWatcher selectVendorordistriName;
    ArrayList<String> selectthereason;
    ArrayList<VendorModel> getAllVenderToselect;
    ArrayList<VendorReturnModel>getAllProductstoreturn;
    ArrayList<VendorReturnModel>GetthedatausingPoNumber;
    AutoCompleteTextView VendororDistributorNameAutoComplete;
    AutoCompleteTextView ProductToreturnAutocomplete;
    VendorNameForVendorReturnAdapter adapter;
    VendorReturnSelectProductToreturnAdapter dropDownProductNameAdpater;
    VendorReturnProductListAdapter ProductListAdapter;
    ListView lv_ProductVendorreturn;
    TextView GrandTotal,Billno;
    DBhelper helper;
    Button clrbtn;
    Button Submit;
    String ReasonSelected;
    public static final String GCM_PROJECT_ID = "407176891585";//only if you need GCM notifications
    Bundle syncDataBundle = null;
    private boolean syncInProgress = false;
    private boolean didSyncSucceed = false;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    // private GoogleApiClient client;
    private GSyncStatusHandler gSyncStatusHandler = new GSyncStatusHandler();
    private GBrowserNotifyHandler gBrowserNotifyHandler = new GBrowserNotifyHandler();
    private GSyncResultHandler gSyncResultNotifyHandler = new GSyncResultHandler();
    private GSyncItemStatusHandler gSyncItemStatusHandler = new GSyncItemStatusHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_return);
        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);


        final String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
        helper = new DBhelper(this);
        returnpopupdialog();

        lv_ProductVendorreturn=(ListView)findViewById(R.id.lv_ProductVendorreturn);
        GrandTotal=(TextView)findViewById(R.id.GrandTotalVendorReturn);
        clrbtn = (Button) findViewById(R.id.clearvendorReturn);
        Submit=(Button)findViewById(R.id.SubmitVendorReturn);


        VendororDistributorNameAutoComplete = (CustomAuto) findViewById(R.id.VendorNameordistributorNames);
        VendororDistributorNameAutoComplete.setThreshold(1);
        ProductToreturnAutocomplete=(CustomAuto)findViewById(R.id.SelectProducttoReturn);
        ProductToreturnAutocomplete.setThreshold(1);

        selectReasonsforVendorreturn = (Spinner) findViewById(R.id.Vendorreturn);
        selectthereason = helper.getthereasonsfromVendorReturn();
        final ArrayAdapter<String> selecttheadapterforreturn = new ArrayAdapter<String>(VendorReturnActivity.this, android.R.layout.simple_spinner_item, selectthereason);
        selectReasonsforVendorreturn.setAdapter(selecttheadapterforreturn);
        selectReasonsforVendorreturn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ReasonSelected = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        selectVendorordistriName = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Debuging", "After text changed called ");
                if (VendororDistributorNameAutoComplete.isPerformingCompletion()) {
                    Log.d("Debuging", "Performing completion ");
                    return;
                }
                String UserTypedValue = VendororDistributorNameAutoComplete.getText().toString().trim();
                if (UserTypedValue.equals("")) {
                    return;
                }
                getAllVenderToselect = helper.getVendorName(UserTypedValue);
                if (getAllVenderToselect != null) {
                    if (adapter == null) {
                        adapter = new VendorNameForVendorReturnAdapter(VendorReturnActivity.this, android.R.layout.simple_spinner_item, getAllVenderToselect);
                        adapter.setList(getAllVenderToselect);
                        VendororDistributorNameAutoComplete.setAdapter(adapter);
                    } else {
                        adapter.setList(getAllVenderToselect);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

        };
        VendororDistributorNameAutoComplete.addTextChangedListener(selectVendorordistriName);

        SelectProducttoreturn =new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("Debuging", "After text changed called ");
                if (VendororDistributorNameAutoComplete.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Select Vendor Or Distributor Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ReasonSelected.equals(""))
                {
                    //Toast.makeText(getApplicationContext(), "No Reason Selected", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(ProductToreturnAutocomplete.isPerformingCompletion())
                {
                    return;
                }
                String UserTypedproduct=ProductToreturnAutocomplete.getText().toString().trim();
                if (UserTypedproduct.equals(""))
                {
                    return;
                }
                getAllProductstoreturn=helper.GetDataToreturn(UserTypedproduct);
                if (getAllProductstoreturn != null) {
                    if (dropDownProductNameAdpater== null)
                    {
                        dropDownProductNameAdpater= new VendorReturnSelectProductToreturnAdapter(VendorReturnActivity.this,android.R.layout.simple_list_item_1,getAllProductstoreturn);
                        dropDownProductNameAdpater.setList(getAllProductstoreturn);
                        ProductToreturnAutocomplete.setAdapter(dropDownProductNameAdpater);
                    }else
                    {
                        dropDownProductNameAdpater.setList(getAllProductstoreturn);
                        dropDownProductNameAdpater.notifyDataSetChanged();
                    }

                }
            }
        };

        ProductToreturnAutocomplete.addTextChangedListener(SelectProducttoreturn);
        ProductToreturnAutocomplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VendorReturnModel resval = (VendorReturnModel) parent.getItemAtPosition(position);
                if (ProductListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    ProductListAdapter = new VendorReturnProductListAdapter(VendorReturnActivity.this, android.R.layout.simple_dropdown_item_1line, new ArrayList<VendorReturnModel>(), resval);
                    lv_ProductVendorreturn.setAdapter(ProductListAdapter);
                }
                ProductListAdapter.addProductToList(resval);
                Log.i("&&&&&&&&", "Adding " + resval + " to Product List..");
                ProductListAdapter.notifyDataSetChanged();
                ProductToreturnAutocomplete.setText("");
                setSummaryRow();
            }
        });

        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ProductListAdapter.clearAllRows();
                    setSummaryRow();
                    VendororDistributorNameAutoComplete.setText("");
                    VendororDistributorNameAutoComplete.addTextChangedListener(selectVendorordistriName);
                    VendororDistributorNameAutoComplete.setEnabled(true);
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
            }

        });


       Submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Long value = System.currentTimeMillis();
               String Vendor_Return_ID = String.valueOf(value);


               if (VendororDistributorNameAutoComplete.getText().toString().trim().isEmpty()) {
                   Toast.makeText(getApplicationContext(), "Please Select Vendor Or Distributor Name", Toast.LENGTH_SHORT).show();
                   return;
               }
               try {
                   if (ProductListAdapter.isEmpty()) {
                       Toast.makeText(getApplicationContext(), "No Product Selected", Toast.LENGTH_SHORT).show();
                       return;
                   }

                   if (ReasonSelected.equals("")) {
                       Toast.makeText(getApplicationContext(), "No Reason Selected", Toast.LENGTH_SHORT).show();
                       return;
                   }

                   helper.InsertDataforVendorReturn(Vendor_Return_ID, ProductListAdapter.getList(), VendororDistributorNameAutoComplete.getText().toString(), ReasonSelected);
                   helper.InsertMasterDataForVendorReturn(Vendor_Return_ID, VendororDistributorNameAutoComplete.getText().toString(), ReasonSelected, GrandTotal.getText().toString());
                   helper.UpdateStockQtyForVendorReturn(ProductListAdapter.getList());

                   helper.saveDataforPdfVendorReturn(Vendor_Return_ID, VendororDistributorNameAutoComplete.getText().toString());
                   loadSyncLibrary();
                   doSync(syncDataBundle);
                   Intent intent = new Intent(getApplicationContext(), Activitypurchase.class);
                   startActivity(intent);

               }catch (NullPointerException ex)
               {
                   ex.printStackTrace();
               }
           }
       });
    }

    public void setSummaryRow() {
        float Getval = ProductListAdapter.getGrandTotal();
        Log.d("&&&&&&&&", "" + Getval);
        String GrandVal = Float.toString(Getval);
        GrandTotal.setText(GrandVal);
    }

    public void returnpopupdialog() {

        final LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.alertdailog_vendorreturn, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);

        final Button WithPo = (Button) alertLayout.findViewById(R.id.WithPoButton);
        final Button WithoutPo = (Button) alertLayout.findViewById(R.id.WithoutPObutton);
        final Button submit = (Button) alertLayout.findViewById(R.id.submitbtn);
        final Button Cancel = (Button) alertLayout.findViewById(R.id.Cancelbtn);
         final Spinner VendornameForReturnSpinner=(Spinner)alertLayout.findViewById(R.id.VendororDistributorNameforVendorReturn);
        final Spinner GrnIdForVendorreturnSpinner=(Spinner)alertLayout.findViewById(R.id.Last3InvoiceNoVendorReturn);

        //final EditText LastInvoiceno = (EditText) alertLayout.findViewById(R.id.EnterInvoicenoforVendorReturn);
        alert.setCancelable(false);
        //LastInvoiceno.setVisibility(View.INVISIBLE);
        WithPo.setVisibility(View.VISIBLE);
        WithoutPo.setVisibility(View.VISIBLE);
        VendornameForReturnSpinner.setVisibility(View.INVISIBLE);
        GrnIdForVendorreturnSpinner.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);
        Cancel.setVisibility(View.VISIBLE);
        alert.setView(alertLayout);
        alert.setCancelable(true);
        TextView title = new TextView(this);
// You Can Customise your Title here
        title.setText("Select The inventory Stock Document Options");
        title.setBackgroundColor(Color.DKGRAY);
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(25);

        alert.setCustomTitle(title);
        final AlertDialog dialog = alert.create();
        WithPo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithoutPo.setVisibility(View.INVISIBLE);
                VendornameForReturnSpinner.setVisibility(View.VISIBLE);
                GrnIdForVendorreturnSpinner.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                // LastInvoiceno.setVisibility(View.VISIBLE);
            }
        });
        WithoutPo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WithPo.setVisibility(View.INVISIBLE);
                //LastInvoiceno.setVisibility(View.INVISIBLE);
                VendornameForReturnSpinner.setVisibility(View.INVISIBLE);
                GrnIdForVendorreturnSpinner.setVisibility(View.INVISIBLE);
                submit.setVisibility(View.INVISIBLE);
                dialog.dismiss();
            }
        });



        ArrayList<VendorModel> vendors = helper.getVendorsforVendorReturn();
        ArrayAdapter<VendorModel > stringArrayAdapter =
                new ArrayAdapter<VendorModel>(VendorReturnActivity.this, android.R.layout.simple_spinner_dropdown_item,vendors);
        VendornameForReturnSpinner.setAdapter(stringArrayAdapter);
        VendornameForReturnSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getApplicationContext(), "OnItemSelected  :" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                String Spinnervalue = VendornameForReturnSpinner.getSelectedItem().toString();
                Log.e("*************", Spinnervalue);
                ArrayList<String> LastInvoices = helper.getLastInvoicesForVendorReturn(Spinnervalue);
                ArrayAdapter<String> InvoiceNoAdapter =
                        new ArrayAdapter<String>(VendorReturnActivity.this, android.R.layout.simple_spinner_dropdown_item, LastInvoices);
                GrnIdForVendorreturnSpinner.setAdapter(InvoiceNoAdapter);
                GrnIdForVendorreturnSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserEnteredNumber=null;
                UserEnteredNumber=GrnIdForVendorreturnSpinner.getSelectedItem().toString().trim();
                GetthedatausingPoNumber = helper.getAllVendorreturndata(UserEnteredNumber);
                ArrayList<String>GetVendorNameforAutocomplete=helper.getVendorNameForAuto(UserEnteredNumber);
                if (GetthedatausingPoNumber != null && GetthedatausingPoNumber.size() > 0) {
                    if (ProductListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        ProductListAdapter = new VendorReturnProductListAdapter(VendorReturnActivity.this, android.R.layout.simple_dropdown_item_1line, new ArrayList<VendorReturnModel>(), null);
                        lv_ProductVendorreturn.setAdapter(ProductListAdapter);
                    }
                    for (VendorReturnModel prod : GetthedatausingPoNumber) {
                        ProductListAdapter.addProductToList(prod);
                    }
                    VendororDistributorNameAutoComplete.setText("" + GetVendorNameforAutocomplete.toString().replace("[", "").replace("]","" + ""));
                    VendororDistributorNameAutoComplete.removeTextChangedListener(selectVendorordistriName);
                    VendororDistributorNameAutoComplete.setEnabled(false);
                    ProductListAdapter.notifyDataSetChanged();
                    setSummaryRow();
                    dialog.dismiss();

                }

            }
        });
        Cancel.setOnClickListener(new View.OnClickListener()

                                  {
                                      @Override
                                      public void onClick (View v){
                                          WithPo.setVisibility(View.VISIBLE);
                                          WithoutPo.setVisibility(View.VISIBLE);
                                          submit.setVisibility(View.VISIBLE);
                                         // LastInvoiceno.setVisibility(View.INVISIBLE);
                                          Intent intent = new Intent(getApplicationContext(), Activitypurchase.class);
                                          startActivity(intent);
                                      }
                                  }

        );
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);

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

            Intent i=new Intent(VendorReturnActivity.this,Activitypurchase.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
        DBhelper mydb = new DBhelper(VendorReturnActivity.this);
        ArrayList<String> value = mydb.getStoreid();
        String filterstoreid = value.toString().replace("[", "").replace("]", "");
        b.putString("dbName", dbName);

        b.putString("syncServerUsername", filterstoreid);//sync user name
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

        b.putString("d4s",                 "RS_Vendreturn");//D4SRS_Po

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
                        //Toast.makeText(this, "Sync Succeded", Toast.LENGTH_LONG).show();
                    }
                    else{}
                    //Toast.makeText(this, "Sync Failed", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    protected GSyncServerConfig getSyncConfigFromSyncIntent(Bundle syncDataBundle)
    {
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
    public void syncStatusEvent(GSyncStatusEvt statEvt)
    {
        String msg = statEvt.getMsg();
        if (msg == null)
            msg = "";
        Bundle b = new Bundle();
        b.putString("syncstatusmsg",   msg);
        b.putInt("status1",          statEvt.getTStatus1());
        b.putInt("status2max",           statEvt.getStatus2Max());
        b.putInt("status2",          statEvt.getStatus2());
        b.putInt("status3max",           statEvt.getStatus3Max());
        b.putInt("status3",          statEvt.getStatus3());
        Message m = Message.obtain();
        m.setData(b);

        if(statEvt.getTStatus1() == 100)//100 sync failed 200 sync succeeded
        {
            syncInProgress = false;
            didSyncSucceed = false;
            //  Toast.makeText(this, "Sync failed", Toast.LENGTH_LONG).show();
            Log.i("GSync", "Sync failed");
            m.setTarget(gSyncResultNotifyHandler);
        }
        else
        if(statEvt.getTStatus1() == 200)//sync succeeded
        {
            didSyncSucceed = true;
            syncInProgress = false;
            // Toast.makeText(this, "Sync Success", Toast.LENGTH_LONG).show();
            Log.i("GSync", "Sync Success");
            m.setTarget(gSyncResultNotifyHandler);
        }
        else
            m.setTarget(gSyncStatusHandler);
        m.sendToTarget();
        Log.i("GSync :: syncStatus", statEvt.toString());
    }

    @Override
    public void browserNotifyEvent(GBrowserNotifyMessageEvt gNMsgEvt)
    {
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
    public void itemStatusEvent(String ItemName, String Msg)
    {
        Bundle b = new Bundle();
        Message m = Message.obtain();
        m.setTarget(gSyncItemStatusHandler);
        b.putString("itemname", ItemName);
        b.putString("msg",        Msg);
        m.setData(b);
        m.sendToTarget();
        Log.i("GSync", "onItemStatus " + ItemName + " : " + Msg);
    }


    private void updateStatus(Message msg)
    {
        //if(status==null) return;
        Bundle bundle = msg.getData();
        int s1        = bundle.getInt("status1");
        int s2        = bundle.getInt("status2");
        int s2max  = bundle.getInt("status2max");
        int s3        = bundle.getInt("status3");
        int s3max  = bundle.getInt("status3max");
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

    private void updateNotify(String notification)
    {
        if(notification==null) return;
        //currently not used
    }

    private void updateResult(String notification)
    {
        if(notification==null) return;
        //currently not used
        //  Toast.makeText(this, "Sync Succeded", Toast.LENGTH_LONG).show();
    }

    private void updateItemStatus(String itemName, String msg)
    {
        if(itemName==null) itemName="";
        if(msg==null) msg="";
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


    class GSyncStatusHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            VendorReturnActivity.this.updateStatus(msg);
        }
    };

    class GBrowserNotifyHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            VendorReturnActivity.this.updateNotify(msg.getData().getString("browsernotifymsg"));
        }
    };

    class GSyncResultHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            VendorReturnActivity.this.updateResult(msg.getData().getString("syncstatusmsg"));
        }
    };

    class GSyncItemStatusHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            VendorReturnActivity.this.updateItemStatus(msg.getData().getString("itemname"), msg.getData().getString("msg"));
        }
    };

    @Override
    public void onBackPressed()
    {
        if(syncInProgress)
        {
            Toast.makeText(this, "Sync in process.. Please wait for it to complete.", Toast.LENGTH_LONG).show();
            return;
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("syncstat", didSyncSucceed);
        setResult(RESULT_OK,   returnIntent);
        finish();
    }



    /**
     * extract ROOT.PEM to /data/data/yourpackage/ if it exists
     * this is to support https based synchronization
     */
    void extractPEMFile(AssetManager mgr, String rootPath)
    {
        try
        {
            String[] filelist = mgr.list("");
            for(String asset: filelist)
            {
                if(asset.equalsIgnoreCase("ROOT.pem"))
                {
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
        }
        catch(Exception e)
        {
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
    private void loadSyncLibrary()
    {
        try
        {
            String trgLib = "gSyncDLL";
            System.loadLibrary(trgLib);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.e("GSync", e.getMessage());
        }
    }

}
