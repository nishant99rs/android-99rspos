package com.mycompany.apps;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import Adapter.CreditNoteAdapter;
import Adapter.CustomerSalesAdapter;
import Adapter.HoldBillAdapter;
import Adapter.PurchaseproductlistAdapter;
import Adapter.SalesAdapter;
import Adapter.SalesProductNmAdaptor;
import Adapter.TabsPagerAdapter;
import Fragments.FastMovingFragment;
import Pojo.CreditNote;
import Pojo.Customer;
import Pojo.DoctorPojo;
import Pojo.PurchaseProductModel;
import Pojo.Sales;
import Pojo.Salesreturndetail;


public class ActivitySalesbill extends FragmentActivity implements View.OnClickListener,
        ActionBar.TabListener,GSyncable {
    EditText BarcodeScan;
    DBhelper db;
    LinearLayout container;
    ArrayList<Sales> salearrayList;
    ArrayList<Sales>GetAllhold;
    SalesAdapter adapter;
    ListView listView;
    TextView enteramt;
    Spinner doctor;
    String item;


    public static final int MIN_LENGTH_OF_BARCODE_STRING = 13;
    public static final String BARCODE_STRING_PREFIX = "@";
    Handler mHandler;

    private TextWatcher salesTextWatcher;
    private TextWatcher barcodeTextWatcher;
    private TextWatcher  ProductNameTextWatcher;
    AutoCompleteTextView ProductName;
    SalesProductNmAdaptor productNmAdaptor;
    ArrayList<Sales>ProductNameArrayList;
    ArrayList<Sales>SalesBarcodearraylist;
    ArrayList<Sales>holdarraylist;
    CustomerSalesAdapter customerSalesAdapter;
    ArrayList<Customer>customerlist;
    EditText reciveamt;
    HoldBillAdapter holdBillAdapter;
    TextView expectedchang,creditnoteretrieve;
    CreditNoteAdapter creditNoteAdapter;
    private TextWatcher cTextWatcher;
    ArrayList<CreditNote>CreditNotelist;
    ArrayList<Sales> holdinvoicelist;
    String Store_Id;
    TextView fragmarquetv;
    String x_imei;
    TextView Totalsavings;

    Bundle syncDataBundle = null;
    public static final String GCM_PROJECT_ID = "407176891585";//only if you need GCM notifications

    private boolean 	syncInProgress = false;
    private boolean 	didSyncSucceed = false;
    private GSyncStatusHandler gSyncStatusHandler = new GSyncStatusHandler();
    private GBrowserNotifyHandler gBrowserNotifyHandler = new GBrowserNotifyHandler();
    private GSyncResultHandler gSyncResultNotifyHandler = new GSyncResultHandler();
    private GSyncItemStatusHandler gSyncItemStatusHandler = new GSyncItemStatusHandler();

    Button search,clear,hold;


    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    Button cashbt, chequebt, cardbt, creditnote;

    private TextView GrandTotal,Transid,Billno,PosCust;
    private  TextView Totalitems;
    private  Button paybycash;
    TelephonyManager tel;
    TextView Sellinprice;
    Spinner holdspinner;
    TextView Mrp;
    EditText Quantity;
    // Tab titles
    private String[] tabs = {"Fast Moving"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_salesbill);
      /*  fragmarquetv=(TextView)this.findViewById(R.id.comingsoon);
        fragmarquetv.setEllipsize(TextUtils.TruncateAt.START);

        fragmarquetv.setEllipsize(TextUtils.TruncateAt.MARQUEE);

        fragmarquetv.setMarqueeRepeatLimit(1000000);
        fragmarquetv.setSelected(true);
        fragmarquetv.setSingleLine(true);

//*/
        Billno = (TextView)findViewById(R.id.sales_billno);
        TelephonyManager tel = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = tel.getDeviceId();
        Log.e("imei is :", device_id);

        Billno.setText(device_id);
        db = new DBhelper(this);
        listView = (ListView) findViewById(R.id.listView);

        Totalitems=(TextView)findViewById(R.id.totalitem_textt);
        GrandTotal=(TextView)findViewById(R.id.grandtotal_textt);
        PosCust = (TextView)findViewById(R.id.sale_customer);
        paybycash=(Button)findViewById(R.id.paycash);
        reciveamt=(EditText)findViewById(R.id.reciveamount);
        expectedchang=(TextView)findViewById(R.id.expectedchange);
        Transid = (TextView)findViewById(R.id.sales_invoiceno);
        ProductName = (CustomAuto) findViewById(R.id.autoProductName);
        creditnoteretrieve=(TextView)findViewById(R.id.creditnote);
        Sellinprice=(TextView)findViewById(R.id.sprice);
        Mrp=(TextView)findViewById(R.id.mrp);
        Quantity=(EditText)findViewById(R.id.quantity);
         hold = (Button) findViewById(R.id.hold);
        Totalsavings=(TextView)findViewById(R.id.totalsavings_textt);
        container = (LinearLayout) findViewById(R.id.container);
        creditnote = (Button)findViewById(R.id.creditnotesbutton);
        doctor = (Spinner)findViewById(R.id.doctorname);
        holdspinner = (Spinner)findViewById(R.id.holdsalebill);
        clear = (Button)findViewById(R.id.clear);
        actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);
    
        
        invoiceno();
//        this.mHandler = new Handler();
//        m_Runnable.run();

        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);
        db = new DBhelper(this);
        clear.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        v.startAnimation(Buttonok);

                        try {


                            invoiceno();
                            expectedchang.setText("");
                            adapter.clearAllRows();
                            GrandTotal.setText("");
                            Totalitems.setText("");
                            reciveamt.setText("");
                            setSummaryRow();
                        } catch (NullPointerException ex) {
                            ex.printStackTrace();
                        }
                    }
                });


        listView.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        Log.v("&&&&&&&&&&", "AutoCompletetextview for Productname initilised");
        ProductName.setThreshold(1);
////////////////////***************Search Through ProductName********************///////////////

        db = new DBhelper(this);
        ProductNameTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {


                Log.i("&&&&&&&&", "After text changed called and text value is" + s.toString());
                if (ProductName.isPerformingCompletion()) {
                    Log.i("&&&&&&&&", "Performing completion and hence drop down will not be shown ");
                    return;
                }
                String userTypedString = ProductName.getText().toString().trim();
                if (userTypedString.equals("")) {
                    return;
                }if(userTypedString.startsWith(BARCODE_STRING_PREFIX) ) {
                    if(ProductNameArrayList!= null) {
                        ProductNameArrayList.clear();
                    }
                    //this is a barcode generated string
                    if(userTypedString.length() <= MIN_LENGTH_OF_BARCODE_STRING) {
                        //ignore all strings of length < 13
                        return;
                    }
                    ProductNameArrayList = db.getProductNamedata(userTypedString.substring(1));
                    //dropdownProductArrayList = helper.getProductdata(userTypedString);
                    if(ProductNameArrayList.size() == 1) {
                        //we have found the product
                        AddSalesProducttoList(ProductNameArrayList.get(0));
                        return;
                    } else if( ProductNameArrayList.size() < 1) {
                        //no product found
                     //   Toast.makeText(ActivitySalesbill.this, "No Product found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    ProductNameArrayList = db.getProductNamedata(userTypedString);
                    if( ProductNameArrayList.size() < 1) {
                        //no product found
                       // Toast.makeText(ActivitySalesbill.this, "No Product found", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (ProductNameArrayList != null) {
                    if (productNmAdaptor == null) {
                        productNmAdaptor = new SalesProductNmAdaptor(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, ProductNameArrayList);
                        productNmAdaptor.setList(ProductNameArrayList);
                        ProductName.setAdapter(productNmAdaptor);
                        ProductName.setThreshold(1);
                    } else {
                        productNmAdaptor.setList(ProductNameArrayList);
                        productNmAdaptor.notifyDataSetChanged();
                    }
                }

            }
        };

        ProductName.addTextChangedListener(ProductNameTextWatcher);
        ProductName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //  Cursor curral=(Cursor)parent.getItemAtPosition(position);
                // PurchaseProductModel resval1=(PurchaseProductModel) parent.getItemAtPosition(position);
                Sales result = (Sales) parent.getItemAtPosition(position);
                AddSalesProducttoList(result);
            }
        });
        creditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreditnotesAlertDialog();

            }
        });
        final DBhelper mydb=new DBhelper(ActivitySalesbill.this);
        final ArrayList<String>doctorname= mydb.getAllDoctorsforsale();
        final ArrayAdapter<String > stringArrayAdapter =
                new ArrayAdapter<String>(ActivitySalesbill.this, android.R.layout.simple_spinner_dropdown_item,doctorname);
       doctor.setAdapter(stringArrayAdapter);
        doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                item = adapterView.getItemAtPosition(position).toString();

                // Toast.makeText(getApplicationContext(),
                //    "report : " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }

        });

        final DBhelper db=new DBhelper(ActivitySalesbill.this);
        final ArrayList<String>holdbilltransaction= mydb.getTransidofholdbill();
        final ArrayAdapter<String > holdbilladapter =
                new ArrayAdapter<String>(ActivitySalesbill.this, android.R.layout.simple_spinner_dropdown_item,holdbilltransaction);
        holdspinner.setAdapter(holdbilladapter);

        holdspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  String holdspinneritem = holdspinner.getSelectedItem().toString();
                  final ArrayList<String>holdbilltransaction= mydb.getTransidofholdbill();
                  for (String str : holdbilltransaction) {
                  if (str.equals(holdspinneritem)) {

                      holdinvoicelist = mydb.getallholdinvoicedata(holdspinneritem);

                      if (holdinvoicelist != null && holdinvoicelist.size() > 0) {
                          if (adapter == null) {
                              Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                              adapter = new SalesAdapter(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, holdinvoicelist);
                              listView.setAdapter(adapter);
                          }
                          for (Sales prod : holdinvoicelist) {
                              adapter.addProductToList(prod);
                          }
                          adapter.notifyDataSetChanged();
                          setSummaryRow();
                      }
                  }
              }
          }
              @Override
              public void onNothingSelected(AdapterView<?> parent) {


              }
          });


      /*  barcodeTextWatcher = new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Sudhee", "on text changed called" + s);
            }

            @Override
            public void afterTextChanged(Editable s) {

                Log.i("&&&&&&&&", "After text changed called and text value is " + s.toString());
                String barcodeFromScanner = BarcodeScan.getText().toString().trim();
                if (barcodeFromScanner.equals("")) {
                    return;
                }
                SalesBarcodearraylist= db.getSalesDetailsFromBarcode(barcodeFromScanner);
                Log.d("Sudhee", "Product arraylist size is " + SalesBarcodearraylist.size());
                if (SalesBarcodearraylist != null && SalesBarcodearraylist.size() == 1) {
                    if (adapter== null) {
                        Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                        adapter = new SalesAdapter(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, new ArrayList<Sales>());
                        listView.setAdapter(adapter);
                    }
                    adapter.addProductToList(SalesBarcodearraylist.get(0));
                    Log.i("&&&&&&&&!", "Adding " + SalesBarcodearraylist.get(0) + " to Product List..");
                    adapter.notifyDataSetChanged();
                    setSummaryRow();
                    BarcodeScan.setText("");
                    BarcodeScan.setVisibility(View.VISIBLE);
                    setFocusOnBarcodeView();

                }
            }
        };
        BarcodeScan.addTextChangedListener(barcodeTextWatcher);
*/

        salesTextWatcher=new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        reciveamt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    double recieveamount = Double.parseDouble(reciveamt.getText().toString());
                    if (recieveamount == 0.0f) {
                        expectedchang.setText("");
                        return;
                    }

                    Editable editableText1 = reciveamt.getText();
                    double value1 = 0, result, total;


                    if (editableText1 != null && editableText1.length() >= 1)
                        value1 = Double.parseDouble(editableText1.toString());
                    float Getval = adapter.getGrandTotal();
                    Log.d("&&&&&&&&", "" + Getval);

                    DecimalFormat f=new DecimalFormat("##.0");
                    String GrandVal = f.format(Getval);

                    GrandTotal.setText(GrandVal);

                    Log.d("**!!!!!!!*****", "" + GrandVal);
                    Log.d("***!!!!!!****", "" + value1);
                    result = value1 - Getval;
                    Log.d("*******", "" + result);
                    expectedchang.setText(Double.toString(result));
                    Log.d("$$$$$$$$$$$", "" + expectedchang);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



        //hold
        paybycash.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        view.startAnimation(Buttonok);
                        String Valuetrans = Transid.getText().toString();

                        try {
                            double selling = Double.parseDouble(reciveamt.getText().toString());
                            double purchase = Double.parseDouble(GrandTotal.getText().toString());
                            if (selling < purchase) {
                                Toast.makeText(getApplicationContext(), "Please enter Amount Received > = Sales Value", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            if (adapter.getList().isEmpty()) {
                                Toast.makeText(getApplicationContext(), "Please fill field", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            db.savesalesListdetail(Valuetrans, adapter.getList());
                            db.insertdetailsifpaybaycash(Valuetrans, GrandTotal.getText().toString());
                            db.updateStockQty(adapter.getList());
                            db.insertdataIntosendMailforSales(Transid.getText().toString(), PosCust.getText().toString());
                            Toast.makeText(getApplicationContext(), Transid.getText().toString(), Toast.LENGTH_SHORT).show();
                            invoiceno();
                            expectedchang.setText("");
                            adapter.clearAllRows();
                            GrandTotal.setText("");
                            Totalitems.setText("");
                            reciveamt.setText("");
                            doctorname.clear();
                            PosCust.setText("");
                            final DBhelper mydb=new DBhelper(ActivitySalesbill.this);
                            final ArrayList<String>doctorname= mydb.getAllDoctorsforsale();
                            final ArrayAdapter<String > stringArrayAdapter =
                                    new ArrayAdapter<String>(ActivitySalesbill.this, android.R.layout.simple_spinner_dropdown_item,doctorname);
                            doctor.setAdapter(stringArrayAdapter);
                            setSummaryRow();
                            loadSyncLibrary();
                            doSync(syncDataBundle);
                        } catch (IndexOutOfBoundsException ex) {
                            ex.printStackTrace();
                        } catch (NullPointerException ex) {
                            ex.printStackTrace();
                        } catch (NumberFormatException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

       hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                   final String Valuetrans = Transid.getText().toString();
//
//                   // LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                   // final View addView = layoutInflater.inflate(R.layout.button_sales, null);
////                    final String receivedata = Transid.getText().toString();
////                    String output = receivedata.substring(13,16);
                   db.tempsavesalesListdetail(Valuetrans, adapter.getList());
                    db.tempsavesalesmaster(Valuetrans,GrandTotal.getText().toString());
//
////                    Button buttonRemove = (Button) addView.findViewById(R.id.remove);
////                    buttonRemove.setText(Valuetrans);
                  invoiceno();
////                    GetAllhold = new ArrayList<Sales>();
////                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
////                    adapter = new SalesAdapter(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, GetAllhold);
////                    listView.setAdapter(adapter);
                    GrandTotal.setText("");
                    Totalitems.setText("");
                    adapter.clearAllRows();
                    recreate();
//
//
////                    buttonRemove.setOnClickListener(new View.OnClickListener() {
////
////                        @Override
////
////                        public void onClick(View v) {
////                            ((LinearLayout) addView.getParent()).removeView(addView);
////
    //                       ArrayList<String> transid = db.getTransidofholdbill();
////                            for (String str : transid) {
////                                if (str.equals(Valuetrans)) {
//                                holdarraylist = db.getallholdinvoicedata(Valuetrans);
////                                    Log.d("nishantsinghhold", "Product arraylist size is " + holdarraylist.size());
////                                    adapter = new SalesAdapter(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, holdarraylist);
////                                    listView.setAdapter(adapter);
////
////                                } else  {
////
////                                    break;
////                                }
////
////                            }   adapter.notifyDataSetChanged();
////
////                        }
////                    });
////                    container.addView(addView);
////                    db = new DBhelper(ActivitySalesbill.this);
//                    //  db.tempsavesalesListdetail(Valuetrans, adapter.getList());
                   db.updateStockQty(adapter.getList());
//                    // Toast.makeText(getApplicationContext(), "Data Inserted", Toast.LENGTH_SHORT).show();
//
//                    loadSyncLibrary();
//                    doSync(syncDataBundle);
                }catch (NullPointerException ex){
                    ex.printStackTrace();
                }

            }});
        search = (Button) findViewById(R.id.search_button);
        /*add.setOnClickListener(this);*/
        /*chequebt = (Button) findViewById(R.id.chequebutton);
        chequebt.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                ChequeAlertDialog();

            }


        });
*/
        /*cashbt = (Button) findViewById(R.id.cashbutton);
        cashbt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CashAlertDialog();

                    }
                });*/


       /* cardbt = (Button) findViewById(R.id.cardbutton);
        cardbt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CardAlertDialog();

                    }
                });*/

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);
                SearchCustomerDialog();


            }
        });
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    private void AddSalesProducttoList(Sales result) {
        if (adapter == null) {
            Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
            adapter = new SalesAdapter(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, new ArrayList<Sales>());
            listView.setAdapter(adapter);
        }
        adapter.addProductToList(result);
        Log.i("&&&&&&&&", "Adding " + result + " to Product List..");
        adapter.notifyDataSetChanged();
        ProductName.setText("");
        setSummaryRow();
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
//            case R.id.addtolist_button:
//                Intent intent = new Intent(this, Additems.class);
//                startActivity(intent);
//                break;
//            case R.id.search_button:
//                Intent intent1 = new Intent(this,ActivityCustomerSales.class);
//                startActivity(intent1);
//                break;


        }

    }
//    private final Runnable m_Runnable = new Runnable()
//    {
//        public void run()
//
//        {
//            Toast.makeText(ActivitySalesbill.this,"in runnable",Toast.LENGTH_SHORT).show();
//
//            ActivitySalesbill.this.mHandler.postDelayed(m_Runnable,20000);
//        }
//
//    };//runnable

    public  void invoiceno() {
        Long Value = System.currentTimeMillis();
        final String result = Long.toString(Value);
        String invoicevalue = Billno.getText().toString();
        db = new DBhelper(this);

        ArrayList<String> billno = db.getimeino();
        for (String str : billno) {
            if (str.equals(invoicevalue))
            {
                ArrayList<String> imei = db.getprefix(str);
                Log.e("%%%%%%%%%%%%%", imei.toString());
                x_imei=imei.toString();
                String x1=  x_imei.replace("[", "").replace("]","").concat(result);
                Log.e("X1_imei is :",x1);
                Transid.setText(x1);
            } else {
                continue;
            }
        }
    }

    public void  CustomerAlertDialog()
    {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.alertcustomer_sales, null);
    }
    private void CreditnotesAlertDialog() {


        LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.creditnote_dialog, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(ActivitySalesbill.this);
        final Button cleardialog = (Button) alertLayout.findViewById(R.id.clear_dialog_button);
        final Button addtopay = (Button) alertLayout.findViewById(R.id.addtopayment_button);
        final TextView creditvalue = (TextView)alertLayout.findViewById(R.id.creditamount_tv);
        final AutoCompleteTextView billno = (CustomAuto) alertLayout.findViewById(R.id.invoiceno);
        final AlertDialog dialog = alert.create();
        dialog.setView(alertLayout);
        dialog.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        dialog.show();



        db = new DBhelper(this);
        billno.setThreshold(1);
        cTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//        addtopay.setOnClickListener(new View.OnClickLi
//
// stener() {
//            @Override
//            public void onClick(View v) {
                try {
                    if (billno.isPerformingCompletion()) {
                        Log.d("Debuging", "Performing completion ");
                        return;
                    }
                    String userTypedInvoiceno = billno.getText().toString().trim();
                    Log.e("!!!!!!!!!!!", "" + billno.toString());
                    if (userTypedInvoiceno.equals("")) {
                        return;
                    }
                    CreditNotelist = db.getcreditno(userTypedInvoiceno);
                    Log.d("!@#!@#!@#!@#", "creditnote arraylist size is " + CreditNotelist.size());

                    if (CreditNotelist != null && CreditNotelist.size() > 0) {
                        if (creditNoteAdapter == null) {
                            Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                            creditNoteAdapter = new CreditNoteAdapter(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, CreditNotelist);
                            creditNoteAdapter.setCreditNotelist(CreditNotelist);

                            billno.setAdapter(creditNoteAdapter);
                            billno.setThreshold(1);

                        } else {
                            creditNoteAdapter.setCreditNotelist(CreditNotelist);
                            creditNoteAdapter.notifyDataSetChanged();
                            billno.setAdapter(creditNoteAdapter);
                            billno.setThreshold(1);
                        }
                        }
                    }catch(NullPointerException ex){
                        ex.printStackTrace();
                    }
                }

        };
        billno.removeTextChangedListener(cTextWatcher);
        billno.addTextChangedListener(cTextWatcher);
        billno.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                Log.d("Debuging", "On click called ");

                final DBhelper mydb = new DBhelper(ActivitySalesbill.this);

                CreditNote value = (CreditNote) parent.getItemAtPosition(pos);
                creditvalue.setText(value.getCreditnotevalue());

                billno.setText("");


            }
        });
        addtopay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crt = creditvalue.getText().toString();
                creditnoteretrieve.setText(crt);
                dialog.dismiss();
                calculatecreditnote();
            }
        });
        cleardialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }



    public  void calculatecreditnote(){

        try {
            Float result;
            Float Getval = Float.parseFloat(creditnoteretrieve.getText().toString());
            Float value = Float.parseFloat(GrandTotal.getText().toString());
            result = value - Getval;
            GrandTotal.setText(result.toString());
        }catch (NumberFormatException ex)
        {ex.printStackTrace();
        }catch (NullPointerException ex)
        {ex.printStackTrace();
        }
    }
    private void SearchCustomerDialog() {

        LayoutInflater inflater = getLayoutInflater();
        final View alertLayout = inflater.inflate(R.layout.search_customer_dialog, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(ActivitySalesbill.this);
        final AutoCompleteTextView searchauto = (CustomAuto) alertLayout.findViewById(R.id.myautocomplete);
        final Button addnewcustomer = (Button) alertLayout.findViewById(R.id.Cust_ok_button);
        final LinearLayout hidden = (LinearLayout) alertLayout.findViewById(R.id.Hiddenlayout);
        final TextView CustomerMobile = (TextView) alertLayout.findViewById(R.id.Cust_mobileno1);
        final Button update = (Button) alertLayout.findViewById(R.id.Cust_update_button);
        final Button clear = (Button) alertLayout.findViewById(R.id.Cust_clear_button);
        final Button exit = (Button) alertLayout.findViewById(R.id.Cust_Exit_button);
        final Button exitcust=(Button)alertLayout.findViewById(R.id.exit);
        final LinearLayout hidden2 = (LinearLayout) alertLayout.findViewById(R.id.Hiddenlayout1);
        final LinearLayout hidden3 = (LinearLayout) alertLayout.findViewById(R.id.autolayout);
        final LinearLayout buttonlayout = (LinearLayout) alertLayout.findViewById(R.id.buttons_layout);


        final TextView CUSTOMERNAME = (TextView) alertLayout.findViewById(R.id.Cust_name);
        final TextView CUSTOMERMOBILE = (TextView) alertLayout.findViewById(R.id.Cust_mobileno);
        final TextView CUSTOMEREMAIL = (TextView) alertLayout.findViewById(R.id.Cust_email);


        final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);
        final AlertDialog dialog = alert.create();
        buttonlayout.setVisibility(View.INVISIBLE);

        dialog.setView(alertLayout);
        dialog.setCanceledOnTouchOutside(false);
        alert.setCancelable(false);
        dialog.show();


        db = new DBhelper(this);
        // final AutoCompleteTextView autoCompleteTextView = (CustomAuto) alertLayout.findViewById(R.id.myautocomplete);
        searchauto.setThreshold(1);


        TextWatcher mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("Debuging", "After text changed called ");
                if (searchauto.isPerformingCompletion()) {
                    Log.d("Debuging", "Performing completion ");
                    return;
                }
                String userTypedString = searchauto.getText().toString().trim();
                if (userTypedString.equals("")) {
                    return;
                }
                customerlist = db.getAllCustomers(userTypedString);

                if (customerlist != null) {
                    if (customerSalesAdapter == null) {
                        customerSalesAdapter = new CustomerSalesAdapter(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, customerlist);
                        customerSalesAdapter.setCustomerList(customerlist);
                        searchauto.setAdapter(customerSalesAdapter);
                        searchauto.setThreshold(1);

                    } else {

                        customerSalesAdapter.setCustomerList(customerlist);
                        customerSalesAdapter.notifyDataSetChanged();
                        searchauto.setAdapter(customerSalesAdapter);
                        searchauto.setThreshold(1);
                    }
                }
            }
        };
        searchauto.removeTextChangedListener(mTextWatcher);
        searchauto.addTextChangedListener(mTextWatcher);
        searchauto.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                Log.d("Debuging", "On click called ");

                final DBhelper mydb = new DBhelper(ActivitySalesbill.this);

                Customer value = (Customer) parent.getItemAtPosition(pos);


               // Customername.setText(value.getCustomername());
                CustomerMobile.setText(value.getCustomername());
                String res = CustomerMobile.getText().toString();
                PosCust.setText(res);
                searchauto.setText("");
                customerlist.remove(pos);
                customerSalesAdapter.notifyDataSetChanged();

                dialog.dismiss();
                // doTheAutoRefresh();

            }
        });
//        customerSalesAdapter.notifyDataSetChanged();




        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(Buttonok);
                CUSTOMEREMAIL.setText("");
                CUSTOMERNAME.setText("");
                CUSTOMERMOBILE.setText("");

            }
        });

        exitcust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBhelper db = new DBhelper(ActivitySalesbill.this);

                String res = CUSTOMERNAME.getText().toString();
                PosCust.setText(res);


                if (CUSTOMERMOBILE.getText().toString().matches("")) {
                    Toast toast = Toast.makeText(ActivitySalesbill.this, "PLEASE FILL THE FIELD", Toast.LENGTH_SHORT);
                    toast.show();
                    return;

                }

                if (db.CheckIsDataAlreadyInDBorNot(CUSTOMERMOBILE.getText().toString()))

                {
                    Toast toast1 = Toast.makeText(ActivitySalesbill.this, "MOBILE NO ALREADY REGISTERED", Toast.LENGTH_SHORT);
                    toast1.show();
                    return;
                }
 //               else if (db.insertCustomer(new Customer(CUSTOMERMOBILE.getText().toString(),
//                        CUSTOMERNAME.getText().toString(), CUSTOMEREMAIL.getText().toString())))
//
//                {
//                    Toast toast = Toast.makeText(ActivitySalesbill.this, "CUSTOMER ADDED", Toast.LENGTH_SHORT);
//                    toast.show();
//                    dialog.dismiss();
//                    loadSyncLibrary();
//                    doSync(syncDataBundle);
//
//
//                }

            }


        });


        addnewcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.startAnimation(Buttonok);

                hidden2.setVisibility(View.INVISIBLE);
                hidden.setVisibility(View.VISIBLE);
                hidden3.setVisibility(View.INVISIBLE);
                buttonlayout.setVisibility(View.VISIBLE);
            }

        });


    }
    public void CashAlertDialog() {

        LayoutInflater inflater = getLayoutInflater();
        View alertlayout = inflater.inflate(R.layout.cashpayment_dialog,null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setView(alertlayout);
        final AlertDialog dialog = alert.create();
        dialog.show();
        setcashdetail();

    }

    public void setcashdetail() {


        float Getval = adapter.getGrandTotal();
        Log.d("&&&&&&&&", "" + Getval);
        String GrandVal = Float.toString(Getval);
        // TextView textView = (TextView) findViewById(R.id.enteramount);
        //   textView.setText(GrandVal);


    }




    public void CardAlertDialog() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.cardpayment_dialog, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        DatePicker picker = new DatePicker(this);
        picker.setCalendarViewShown(true);

        alert.setView(alertLayout);
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    public void ChequeAlertDialog() {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.checquepayment_dialog, null);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        DatePicker picker = new DatePicker(this);
        picker.setCalendarViewShown(true);

        alert.setView(alertLayout);
        AlertDialog dialog = alert.create();
        dialog.show();
    }
    public void setSummaryRow() {

        DecimalFormat f=new DecimalFormat("##.0");
        float Getval = adapter.getGrandTotal();
        Log.d("&&&&&&&&", "" + Getval);
        String GrandVal = f.format(Getval);
        GrandTotal.setText(GrandVal);
        Log.d("@@@@@@@@@@", "" + GrandTotal);



        reciveamt.setText("");
        expectedchang.setText("");
        int Getitem=adapter.getCount();
        int Allitem=Getitem;
        String GETITEM=Integer.toString(Allitem);
        Totalitems.setText(GETITEM);

        float Gettotalsavings = adapter.gettotalsavings();
        Log.d("&&&&&&&&", "" + Getval);
        String Grandtotalsaving = f.format(Gettotalsavings);
        Totalsavings.setText(Grandtotalsaving);
        Log.d("@@@@@@@@@@", "" + Totalsavings);


    }





    public void fragmentdata() {
/* String shilpa="Hello";
 textView.setText(shilpa);*/
        String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";

        DBhelper db = new DBhelper(this);

        FastMovingFragment fragobj = new FastMovingFragment();

        String value = (String) fragobj.myBundle.get("id_User");
        try {

            String userTypedCaption = value.toString();
            Log.e("!!!!!!!!!!!", "" + value.toString());
            if (userTypedCaption.equals("")) {
                return;
            }


            ArrayList<Sales> fragmentdatalist = db.getAllTopProductDetails(userTypedCaption);
            if (fragmentdatalist != null) {
                if (adapter == null) {
                    adapter = new SalesAdapter(ActivitySalesbill.this, android.R.layout.simple_dropdown_item_1line, fragmentdatalist);
                    //adapter.setsalearrayList(fragmentdatalist);
                    listView.setAdapter(adapter);

                }

                adapter.addProductToList(fragmentdatalist.get(0));
                //adapter.setsalearrayList(fragmentdatalist);
                adapter.notifyDataSetChanged();
                setSummaryRow();

            }
        }catch (Exception npe){
            npe.printStackTrace();
        }

    }




    /*final Handler requestFocusHandler = new Handler();
    private void setFocusOnBarcodeView() {
        requestFocusHandler.post( new Runnable() {

            @Override
            public void run() {
                ProductName.setFocusable(true);
                ProductName.setFocusableInTouchMode(true);
                ProductName.requestFocus();
            }
        });

        requestFocusHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                ProductName.setFocusable(true);
                ProductName.setFocusableInTouchMode(true);
                ProductName.requestFocus();
            }
        }, 1000);
    }*/


    protected void onStart()
    {
        syncDataBundle = getSyncBudle();//get sync parameters as bundle
        //these can be obtained from a form
        //setSyncParamView(syncDataBundle);//display the parameters
        super.onStart();
    }




    protected Bundle getSyncBudle()
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
//        DBhelper mydb = new DBhelper(ActivitySalesbill.this);
//        ArrayList<String> value = mydb.getStoreid();
//        String filterstoreid = value.toString().replace("[", "").replace("]", "");
        b.putString("dbName", dbName);

        b.putString("syncServerUsername",  "");//sync user name
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

        b.putString("d4s",                 "Synctype=RS_Sales");//D4S

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
    @Override
    public void syncStatusEvent(GSyncStatusEvt statEvt)
    {
        String msg = statEvt.getMsg();
        if (msg == null)
            msg = "";
        Bundle b = new Bundle();
        b.putString("syncstatusmsg", 	msg);
        b.putInt("status1", 			statEvt.getTStatus1());
        b.putInt("status2max", 			statEvt.getStatus2Max());
        b.putInt("status2", 			statEvt.getStatus2());
        b.putInt("status3max", 			statEvt.getStatus3Max());
        b.putInt("status3", 			statEvt.getStatus3());
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
         //   Toast.makeText(this, "Sync Success", Toast.LENGTH_LONG).show();
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
        b.putString("msg", 		Msg);
        m.setData(b);
        m.sendToTarget();
        Log.i("GSync", "onItemStatus " + ItemName + " : " + Msg);
    }


    private void updateStatus(Message msg)
    {
        //if(status==null) return;
        Bundle bundle = msg.getData();
        int s1 		= bundle.getInt("status1");
        int s2 		= bundle.getInt("status2");
        int s2max 	= bundle.getInt("status2max");
        int s3 		= bundle.getInt("status3");
        int s3max 	= bundle.getInt("status3max");
//
//		spb1.setMax(5);//currently fixed at 5
//		spb1.setProgress(s1);
//
//		//Log.i("GSync", "status1 " + s1);
//
//		spb2.setMax(s2max);
//		spb2.setProgress(s2);
//
//		spb3.setMax(s3max);
//		spb3.setProgress(s3);

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
//		String t = statusEditText.getText().toString();
//		if(t==null)t="";
//		statusEditText.append(msg + " " + itemName + "\n");
//		scrollView.post(new Runnable()
//		{
//			public void run()
//			{
//				scrollView.smoothScrollTo(0, statusEditText.getBottom());
//			}
//		});
        Log.i("GSync", "->" + itemName + " " + msg + "\n");
    }


    class GSyncStatusHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            ActivitySalesbill.this.updateStatus(msg);
        }
    };

    class GBrowserNotifyHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            ActivitySalesbill.this.updateNotify(msg.getData().getString("browsernotifymsg"));
        }
    };

    class GSyncResultHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg)
        {
            ActivitySalesbill.this.updateResult(msg.getData().getString("syncstatusmsg"));
        }
    };

    class GSyncItemStatusHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            ActivitySalesbill.this.updateItemStatus(msg.getData().getString("itemname"), msg.getData().getString("msg"));
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
        setResult(RESULT_OK,	returnIntent);
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

            Intent i=new Intent(ActivitySalesbill.this,ActivitySales.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
