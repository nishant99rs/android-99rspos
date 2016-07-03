package com.mycompany.apps;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Activityloyalitydefine extends Activity {

    TextView storeid;
    TextView Id;
    TextView cardtype;
    Button update;
    EditText pointsrange;
    DBhelper mydb;
    String Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityloyalitydefine);
        ActionBar actionBar;
        mydb = new DBhelper(this);



        ArrayList<String> updatelist1 = mydb.getdata1();
        try {
            String Store_Id_Search = updatelist1.get(0);
            Bundle databundle = new Bundle();
            databundle.putString("Store_Id", Store_Id_Search);
            Intent i = getIntent();
            i.putExtras(databundle);
            actionBar = getActionBar();
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(true);
            actionBar.setIcon(R.drawable.w);

            //  startActivity(i);
            storeid = (TextView) findViewById(R.id.editstoreid);
            Id = (TextView) findViewById(R.id.editid);
            cardtype = (TextView) findViewById(R.id.editcardtype);

            pointsrange = (EditText) findViewById(R.id.editpointrange);
            update = (Button) findViewById(R.id.buttonupdate);

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String Value = extras.getString("Store_Id");
                Cursor rs = mydb.getdata(Value);

                rs.moveToFirst();

                String editstoreid = rs.getString(rs.getColumnIndex(DBhelper.STORE_ID_CARD));
                storeid.setText(editstoreid);
                String editid = rs.getString(rs.getColumnIndex(DBhelper.ID));
                Id.setText(editid);
                String editcardtype = rs.getString(rs.getColumnIndex(DBhelper.CARD_TYPE));
                cardtype.setText(editcardtype);
                String pointrange = rs.getString(rs.getColumnIndex(DBhelper.POINTS_RANGE));
                pointsrange.setText(pointrange);


            }

        update.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                pointsrange = (EditText) findViewById(R.id.editpointrange);
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
//
                    String Value = extras.getString("Store_Id");
                    Update = Value;


                    mydb.updateContact(Update, pointsrange.getText().toString());
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
                Intent intent = new Intent(getApplicationContext(),ActivityLoyality.class);
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

            Intent i=new Intent(Activityloyalitydefine .this,ActivityLoyality.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
