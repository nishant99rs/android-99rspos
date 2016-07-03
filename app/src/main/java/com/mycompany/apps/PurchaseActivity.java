package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
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

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HandshakeCompletedListener;

import Adapter.PurchaseAdapter;
import Adapter.PurchaseInvoiceNoListAdapter;
import Adapter.PurchaseProductAdapter;
import Adapter.PurchaseproductlistAdapter;
import Pojo.PurchaseInvoiceDropDownModel;
import Pojo.VendorModel;
import Pojo.PurchaseProductModel;

public class PurchaseActivity extends Activity implements GSyncable {
    public static final int MIN_LENGTH_OF_BARCODE_STRING = 13;
    public static final String BARCODE_STRING_PREFIX = "@";
    AutoCompleteTextView autoCompleteTextView;
    private TextWatcher mTextWatcher;
    private TextWatcher pTextWatcher;
    EditText statusEditText = null;
    ActionBar actionBar;
    private TextWatcher barcodeTextWatcher;

    AutoCompleteTextView ProductidorName;
    EditText BarcodeScan;
    TextView invoiceNo;
    DBhelper helper;
    ArrayList<VendorModel> arrayList;
    ArrayList<PurchaseProductModel> dropdownProductArrayList;

    ArrayList<PurchaseProductModel> purchaseBarcodearraylist;
    ArrayList<PurchaseProductModel> OldPurchaseArraylist;
    ArrayList<PurchaseProductModel>HoldPurchaseList;
    PurchaseAdapter adapter;
    PurchaseProductAdapter productDropdownAdapter;
    PurchaseInvoiceNoListAdapter invoiceNOListAdapter;
    PurchaseInvoiceDropDownModel purchasemodel;
    TelephonyManager tel;

    String PurchaseOrderNUmber;
    String HoldPurchaseorderNo;
    String x_imei;
    //purchaseBarcodeAdapter barcodeAdapter;
    PurchaseproductlistAdapter productListAdapter = null;
    ListView listView;
    private TextView GrandTotal;
    private TextView Saving;
    private TextView Totalitems,Billno;


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
        setContentView(R.layout.activity_purchase);
        Log.v("&&&&&&&&&&", "on create");


        // getBundle.putString("d4s", "Synctype=RS");

        final String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
        helper = new DBhelper(this);
        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

        Button sbtbtn = (Button) findViewById(R.id.submit);
        Button clrbtn = (Button) findViewById(R.id.clear);
        Button HoldBtn=(Button)findViewById(R.id.HoldPurchase);
        Billno = (TextView)findViewById(R.id.sales_billno);
        TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = tel.getDeviceId();
        Log.e("imei is :", device_id);

        Billno.setText(device_id);

        Log.v("&&&&&&&&&&", "Listview initilised");
        listView = (ListView) findViewById(R.id.lv_PurchaseProduct);
        GrandTotal = (TextView) findViewById(R.id.GrandTotal);
        Totalitems = (TextView) findViewById(R.id.Totalitem);
        // Saving=(TextView)findViewById(R.id.PurchaseSaving);

        Log.v("&&&&&&&&&&", "AutoCompletetextview for vendorname initilised");
        autoCompleteTextView = (CustomAuto) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);

        Log.v("&&&&&&&&&&", "AutoCompletetextview for Productname initilised");
        ProductidorName = (CustomAuto) findViewById(R.id.autoProductIdandName);
        ProductidorName.setThreshold(1);

      /*  BarcodeScan = (EditText) findViewById(R.id.Barcodescan);*/

        invoiceNo = (TextView) findViewById(R.id.invoiceno);
        Billno =  (TextView) findViewById(R.id.sales_billno);

//        final Long Value = System.currentTimeMillis();
//        final String resval = Long.toString(Value);
//        invoiceNo.setText(resval);

        SelectPurchaseAlertDialog();
        invoiceno();

/******************************** vendor name oor Distributor name selected from here********************************************************************************************/
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
                arrayList = helper.getVendorNameforPurchase(userTypedString);
                if (arrayList != null) {
                    if (adapter == null) {
                        adapter = new PurchaseAdapter(PurchaseActivity.this, android.R.layout.simple_dropdown_item_1line, arrayList);
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
                String VendorName = parent.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), "U select " + VendorName, Toast.LENGTH_SHORT).show();
            }
        });
       /* InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (!Character.isLetterOrDigit(source.charAt(i))) {
                        return "";
                    }
                }
                return null;
            }
        };
        autoCompleteTextView.setFilters(new InputFilter[]{filter});
*/
        /**************************End for select product and display into the listview ***********************************************************************************************************************/



        helper = new DBhelper(this);
        pTextWatcher = new TextWatcher() {
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
                if (ProductidorName.isPerformingCompletion()) {
                    Log.i("&&&&&&&&", "Performing completion and hence drop down will not be shown ");
                    return;
                }
                String userTypedString = ProductidorName.getText().toString().trim();
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
                    dropdownProductArrayList = helper.getProductdata(userTypedString.substring(1));
                    //dropdownProductArrayList = helper.getProductdata(userTypedString);
                    if(dropdownProductArrayList.size() == 1) {
                        //we have found the product
                        addProductToPurchaseList(dropdownProductArrayList.get(0));
                        return;
                    } else if( dropdownProductArrayList.size() < 1) {
                        //no product found
                       // Toast.makeText(PurchaseActivity.this, "No Product found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    dropdownProductArrayList = helper.getProductdata(userTypedString);
                    if( dropdownProductArrayList.size() < 1) {
                        //no product found
                       // Toast.makeText(PurchaseActivity.this, "No Product found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (productDropdownAdapter == null) {
                    productDropdownAdapter = new PurchaseProductAdapter(PurchaseActivity.this, android.R.layout.simple_dropdown_item_1line, dropdownProductArrayList);
                    productDropdownAdapter.setList(dropdownProductArrayList);
                    ProductidorName.setAdapter(productDropdownAdapter);
                    ProductidorName.setThreshold(1);
                } else {
                    productDropdownAdapter.setList(dropdownProductArrayList);
                    productDropdownAdapter.notifyDataSetChanged();
                }
            }
            //}
        };
        ProductidorName.addTextChangedListener(pTextWatcher);
        ProductidorName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Cursor curral=(Cursor)parent.getItemAtPosition(position);
                // PurchaseProductModel resval1=(PurchaseProductModel) parent.getItemAtPosition(position);

                PurchaseProductModel resval1 = (PurchaseProductModel) parent.getItemAtPosition(position);
                addProductToPurchaseList(resval1);
            }

        });
/*************************************************************************************************************************/
/**************************End for Barcode***********************************************************************************************************************/
        sbtbtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {

              final String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
              SQLiteDatabase db = helper.getWritableDatabase();
              String invoicevalue = invoiceNo.getText().toString();

              if (autoCompleteTextView.getText().toString().trim().isEmpty()) {
                  Toast.makeText(getApplicationContext(), "Please Select Vendor Or Distributor Name", Toast.LENGTH_SHORT).show();
                  return;
              }
              if (productListAdapter.isEmpty()) {
                  Toast.makeText(getApplicationContext(), "No Product Selected", Toast.LENGTH_SHORT).show();

                  return;
              }
              helper.savePurchaseList(productListAdapter.getList(), invoiceNo.getText().toString(), autoCompleteTextView.getText().toString());
              helper.SaveGrandDataForPurchase(invoiceNo.getText().toString(), autoCompleteTextView.getText().toString(), GrandTotal.getText().toString());
              helper.SavePdfDetailForPurchase(invoiceNo.getText().toString(), autoCompleteTextView.getText().toString());
              Toast.makeText(getApplicationContext(), invoicevalue, Toast.LENGTH_SHORT).show();

              Intent syncIntent = new Intent(getApplicationContext(), com.mycompany.apps.Activitypurchase.class);
              syncIntent.putExtras(syncDataBundle);
              startActivityForResult(syncIntent, GoSyncActivity.INTENT_CODE);
              loadSyncLibrary();
              doSync(syncDataBundle);
          }
      }
        );
        HoldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
                SQLiteDatabase db = helper.getWritableDatabase();
                String invoicevalue = invoiceNo.getText().toString();

                if (autoCompleteTextView.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Select Vendor Or Distributor Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (productListAdapter.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "No Product Selected", Toast.LENGTH_SHORT).show();

                    return;
                }
                helper.saveHoldPurchaseList(productListAdapter.getList(), invoiceNo.getText().toString(), autoCompleteTextView.getText().toString());

                Toast.makeText(getApplicationContext(), invoiceNo.getText().toString(), Toast.LENGTH_SHORT).show();


                Intent syncIntent = new Intent(getApplicationContext(), com.mycompany.apps.Activitypurchase.class);
                syncIntent.putExtras(syncDataBundle);
                startActivityForResult(syncIntent, GoSyncActivity.INTENT_CODE);
                loadSyncLibrary();
                doSync(syncDataBundle);
            }
        });

        /**************************Alll clear screen ***********************************************************************************************************************/
        clrbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    productListAdapter.clearAllRows();
                    setSummaryRow();
                    invoiceNo.setText("");
                    autoCompleteTextView.setText("");
                    invoiceno();
                    autoCompleteTextView.removeTextChangedListener(mTextWatcher);
                    autoCompleteTextView.setEnabled(true);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                } finally {
                    autoCompleteTextView.setText("");
                }
            }
        });

    }
    final Handler requestFocusHandler = new Handler();
    private void setFocusOnBarcodeView() {
        requestFocusHandler.post(new Runnable() {

            @Override
            public void run() {
                ProductidorName.setFocusable(true);
                ProductidorName.setFocusableInTouchMode(true);
                ProductidorName.requestFocus();
            }
        });

        requestFocusHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                ProductidorName.setFocusable(true);
                ProductidorName.setFocusableInTouchMode(true);
                ProductidorName.requestFocus();
            }
        }, 1000);
    }



    private void addProductToPurchaseList(PurchaseProductModel resval1) {
        if (productListAdapter == null) {
            Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
            productListAdapter = new PurchaseproductlistAdapter(PurchaseActivity.this, new ArrayList<PurchaseProductModel>(), android.R.layout.simple_dropdown_item_1line, resval1);
            listView.setAdapter(productListAdapter);
        }
        productListAdapter.addProductToList(resval1);
        Log.i("&&&&&&&&", "Adding " + resval1 + " to Product List..");
        productListAdapter.notifyDataSetChanged();
        ProductidorName.setText("");
        setSummaryRow();
        setFocusOnBarcodeView();
        if (dropdownProductArrayList!=null) {
            return;
        } dropdownProductArrayList.clear();
        productDropdownAdapter.setList(dropdownProductArrayList);

    }
    public void SelectPurchaseAlertDialog() {

        LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.alertdailog_purchase, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);


        final Button NewPurchase = (Button) alertLayout.findViewById(R.id.NewPurchase);
        final Button OldPurchase = (Button) alertLayout.findViewById(R.id.Oldpurchasebutton);
        final Button Hold=(Button)alertLayout.findViewById(R.id.HoldButton);
        final Button submit = (Button) alertLayout.findViewById(R.id.submitbtn);
        final Button Cancel = (Button) alertLayout.findViewById(R.id.Cancelbtn);
        final Button SubmitForHold=(Button)alertLayout.findViewById(R.id.submitbtnForHold);
        /*final EditText LastInvoiceno=(EditText)alertLayout.findViewById(R.id.EnterInvoiceno);*/
        final Spinner spinner = (Spinner) alertLayout.findViewById(R.id.VendororDistributorName);
        final Spinner Last3InvoiceNo = (Spinner) alertLayout.findViewById(R.id.Last3InvoiceNo);
        final Spinner Holddata=(Spinner)alertLayout.findViewById(R.id.HoldPurchaseNo);
        // final Spinner spinner;
        Last3InvoiceNo.setVisibility(View.GONE);
        submit.setVisibility(View.INVISIBLE);
        spinner.setVisibility(View.GONE);
        Cancel.setVisibility(View.VISIBLE);
        Holddata.setVisibility(View.GONE);
        SubmitForHold.setVisibility(View.GONE);
        alert.setView(alertLayout);
        alert.setCancelable(true);
        alert.setTitle("                Select Purchase Option");
        final AlertDialog dialog = alert.create();

        OldPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(), "Old Purchase", Toast.LENGTH_SHORT).show();
                Last3InvoiceNo.setVisibility(View.VISIBLE);
                submit.setVisibility(View.VISIBLE);
                spinner.setVisibility(View.VISIBLE);
                NewPurchase.setVisibility(View.GONE);
                Hold.setVisibility(View.GONE);
                SubmitForHold.setVisibility(View.GONE);

              /*  LastInvoiceno.getText().toString();*/

            }
        });

        NewPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "New Purchase", Toast.LENGTH_SHORT).show();
                OldPurchase.setVisibility(View.GONE);
                NewPurchase.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);
                Hold.setVisibility(View.GONE);
                SubmitForHold.setVisibility(View.GONE);
                dialog.dismiss();
            }
        });
        Hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Holddata.setVisibility(View.VISIBLE);
                OldPurchase.setVisibility(View.GONE);
                NewPurchase.setVisibility(View.GONE);
                Last3InvoiceNo.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);
                spinner.setVisibility(View.GONE);
                SubmitForHold.setVisibility(View.VISIBLE);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               try{
                   String Spinnervalue = spinner.getSelectedItem().toString();
                String userTypedInvoiceno = Last3InvoiceNo.getSelectedItem().toString().trim();
                Log.e("!!!!!!!!!!!", "" + Last3InvoiceNo);
                ProductidorName.requestFocus();
                if (userTypedInvoiceno.equals("")) {
                    return;
                }
                OldPurchaseArraylist = helper.getAllOLDPurchaseList(PurchaseOrderNUmber);
                Log.d("!@#!@#!@#!@#", "Product arraylist size is " + OldPurchaseArraylist.size());

                if (OldPurchaseArraylist != null && OldPurchaseArraylist.size() > 0) {
                    if (productListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        productListAdapter = new PurchaseproductlistAdapter(PurchaseActivity.this, new ArrayList<PurchaseProductModel>(), android.R.layout.simple_dropdown_item_1line, null);
                        listView.setAdapter(productListAdapter);
                    }
                    for (PurchaseProductModel prod : OldPurchaseArraylist) {
                        productListAdapter.addProductToList(prod);
                    }
                    autoCompleteTextView.setText("" + spinner.getSelectedItem().toString() + "");
                    autoCompleteTextView.removeTextChangedListener(mTextWatcher);
                    autoCompleteTextView.setEnabled(false);
                    Log.i("&&&&&&&&!", "Adding " + OldPurchaseArraylist.get(0) + " to Product List..");
                    productListAdapter.notifyDataSetChanged();
                    setSummaryRow();
                    dialog.dismiss();
                }
                }catch (Exception e){
                   e.printStackTrace();
               }
            }
        });
        SubmitForHold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    String Spinnervalue = Holddata.getSelectedItem().toString();
                if (HoldPurchaseorderNo == null)
                {
                    Toast.makeText(getApplicationContext(), "No Hold Item Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                HoldPurchaseList = helper.getAllHoldPurchaseData(HoldPurchaseorderNo);
                if (HoldPurchaseList != null && HoldPurchaseList.size() > 0) {
                    if (productListAdapter == null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        productListAdapter = new PurchaseproductlistAdapter(PurchaseActivity.this, new ArrayList<PurchaseProductModel>(), android.R.layout.simple_dropdown_item_1line, null);
                        listView.setAdapter(productListAdapter);
                    }
                    for (PurchaseProductModel prod : HoldPurchaseList) {
                        productListAdapter.addProductToList(prod);

                    }
                    autoCompleteTextView.setText(HoldPurchaseList.get(0).getVendorName());
                    helper.replaceflag(HoldPurchaseorderNo);
                    autoCompleteTextView.removeTextChangedListener(mTextWatcher);
                    autoCompleteTextView.setEnabled(false);
                    Log.i("&&&&&&&&!", "Adding " + HoldPurchaseList.get(0) + " to Product List..");
                    productListAdapter.notifyDataSetChanged();
                    setSummaryRow();
                    dialog.dismiss();
                }
            }
        });

        ArrayList<VendorModel> vendors = helper.getVendors();
        ArrayAdapter<VendorModel> stringArrayAdapter =
                new ArrayAdapter<VendorModel>(PurchaseActivity.this, android.R.layout.simple_spinner_dropdown_item, vendors);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //  Toast.makeText(getApplicationContext(), "OnItemSelectedListener  :" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                String Spinnervalue = spinner.getSelectedItem().toString();
                Log.e("*************", Spinnervalue);


                ArrayList<PurchaseInvoiceDropDownModel> LastInvoices = helper.getLastInvoices(Spinnervalue);
                invoiceNOListAdapter = new PurchaseInvoiceNoListAdapter(PurchaseActivity.this, android.R.layout.simple_dropdown_item_1line, LastInvoices);
                Last3InvoiceNo.setAdapter(invoiceNOListAdapter);
                Last3InvoiceNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(getApplicationContext(), "OnItemSelectedListener  :" + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                        PurchaseInvoiceDropDownModel resval1 = (PurchaseInvoiceDropDownModel) parent.getItemAtPosition(position);
                        PurchaseOrderNUmber = resval1.getPurchaseOrderNo();
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


        ArrayList<PurchaseInvoiceDropDownModel> LastInvoices = helper.getInvoiceNumberForPurchase();
        invoiceNOListAdapter = new PurchaseInvoiceNoListAdapter(PurchaseActivity.this, android.R.layout.simple_dropdown_item_1line, LastInvoices);
        Holddata.setAdapter(invoiceNOListAdapter);
        Holddata.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PurchaseInvoiceDropDownModel resval1 = (PurchaseInvoiceDropDownModel) parent.getItemAtPosition(position);
                HoldPurchaseorderNo = resval1.getPurchaseOrderNo();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OldPurchase.setVisibility(View.VISIBLE);
                NewPurchase.setVisibility(View.VISIBLE);
                submit.setVisibility(View.INVISIBLE);
                spinner.setVisibility(View.INVISIBLE);
                Last3InvoiceNo.setVisibility(View.INVISIBLE);
                dialog.dismiss();
                Intent intent = new Intent(getApplicationContext(), Activitypurchase.class);
                startActivity(intent);

            }
        });
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public String toString() {
        return super.toString();
    }


    /**************************
     * Alll Calculation done here
     ***********************************************************************************************************************/

    public void setSummaryRow() {
        float Getval = productListAdapter.getGrandTotal();
        Log.d("&&&&&&&&", "" + Getval);
        String GrandVal = Float.toString(Getval);
        GrandTotal.setText(GrandVal);
        //  productListAdapter.notifyDataSetChanged();

        int Getitem = productListAdapter.getCount();
        int Allitem = Getitem;
        String GETITEM = Integer.toString(Allitem);
        Totalitems.setText(GETITEM);

       /* float GetValue=productListAdapter.TotalSaving();
        String Getsave=Float.toString(GetValue);
        Saving.setText(Getsave);
*/
    }

    public  void invoiceno() {
        Long Value = System.currentTimeMillis();
        final String result = Long.toString(Value);
        String invoicevalue = Billno.getText().toString();
        helper = new DBhelper(this);

        ArrayList<String> billno = helper.getimeino();
        for (String str : billno) {
            if (str.equals(invoicevalue))
            {
                ArrayList<String> imei = helper.getprefix(str);
                Log.e("%%%%%%%%%%%%%", imei.toString());
                x_imei=imei.toString();
                String x1=  x_imei.replace("[", "").replace("]","").concat(result);
                Log.e("X1_imei is :",x1);
                invoiceNo.setText(x1);
            } else {
                continue;
            }
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

            Intent i=new Intent(PurchaseActivity.this,Activitypurchase.class);
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
        DBhelper mydb = new DBhelper(PurchaseActivity.this);
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

        b.putString("d4s",                 "RS_Po");//D4SRS_Po

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
            PurchaseActivity.this.updateStatus(msg);
        }
    };

    class GBrowserNotifyHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            PurchaseActivity.this.updateNotify(msg.getData().getString("browsernotifymsg"));
        }
    };

    class GSyncResultHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            PurchaseActivity.this.updateResult(msg.getData().getString("syncstatusmsg"));
        }
    };

    class GSyncItemStatusHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            PurchaseActivity.this.updateItemStatus(msg.getData().getString("itemname"), msg.getData().getString("msg"));
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