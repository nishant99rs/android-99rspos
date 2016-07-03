package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Activityloyalityaccural extends Activity {
    TextView storeid;
    TextView sno;
    TextView cardtype;
    TextView pertonpointt;
    String Update;

    Button update;
    EditText basevalue;
    DBhelper mydb;
    ActionBar actionBar;
    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityloyalityaccural);

        mydb = new DBhelper(this);
        ArrayList<String> updatelist2 = mydb.getdata2();
        try
        {
        String Store_Id_Search = updatelist2.get(0);
        Bundle databundle = new Bundle();
        databundle.putString("Store_Id", Store_Id_Search);
        Intent i = getIntent();
        i.putExtras(databundle);
        //  startActivity(i);
        storeid=(TextView)findViewById(R.id.editstoreid);
        sno=(TextView)findViewById(R.id.editsno);
        cardtype=(TextView)findViewById(R.id.editcardtype);
        pertonpointt=(TextView)findViewById(R.id.editpertonpoint);
        basevalue=(EditText)findViewById(R.id.editbasevalue);
        actionBar=getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);

            update=(Button)findViewById(R.id.buttonupdate);

        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String Value = extras.getString("Store_Id");
            Cursor rs = mydb.getdatarule(Value);

            rs.moveToFirst();

            String editstoreid = rs.getString(rs.getColumnIndex(DBhelper.STORE_ID_RULE));
            storeid.setText(editstoreid);
            String editsno = rs.getString(rs.getColumnIndex(DBhelper.SNO));
            sno.setText(editsno);
            String editcardtype = rs.getString(rs.getColumnIndex(DBhelper.CARD_TYPE));
            cardtype.setText(editcardtype);
            String editpertonpoint=rs.getString(rs.getColumnIndex(DBhelper.PER_TON_POINTS));
            pertonpointt.setText(editpertonpoint);
            String basevalues=rs.getString(rs.getColumnIndex(DBhelper.BASE_VALUE));
            basevalue.setText(basevalues);


        }


        update.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                basevalue= (EditText) findViewById(R.id.editbasevalue);
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
//
                    String Value = extras.getString("Store_Id");
                    Update = Value;


                    mydb.updateContact2(Update, basevalue.getText().toString());
                    ;
                    Toast.makeText(getApplicationContext(), "LOYALITY UPDATED", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ActivityLoyality.class);
                    startActivity(intent);

                }

            }
        });
        }catch (CursorIndexOutOfBoundsException e){
            e.printStackTrace();
        }
        catch (IndexOutOfBoundsException  e){
            e.printStackTrace();
        }
        catch (NullPointerException  e) {
            e.printStackTrace();
        }
        Button Exit = (Button) findViewById(R.id.buttonexit);
        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlphaAnimation Buttonok = new AlphaAnimation(1F, 0.1F);
                view.startAnimation(Buttonok);
                Intent intent = new Intent(getApplicationContext(), ActivityLoyality.class);
                startActivity(intent);
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

            Intent i=new Intent(Activityloyalityaccural.this,ActivityLoyality.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
