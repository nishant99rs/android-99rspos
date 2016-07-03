package Fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.LinearLayout;

import com.mycompany.apps.ActivitySalesbill;
import com.mycompany.apps.DBhelper;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Sales;

/**
 * Created by rspl-nishant on 12/3/16.
 */
public class FastMovingFragment extends android.support.v4.app.Fragment {

    Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bt8, bt9, bt10, bt11, bt12, bt13, bt14, bt15;
    DBhelper db;
    public static Bundle myBundle = new Bundle();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_fastmoving, container, false);

        db = new DBhelper(getContext());
        try {

            ArrayList<Sales> buttonno = db.gettopproducts();

            final String values = buttonno.get(0).getProductshortName();
            bt1 = (Button) rootView.findViewById(R.id.product_update_butt);
            bt1.setText(values);
            Log.e("@@@@", bt1.toString());

            bt1 = (Button) rootView.findViewById(R.id.product_update_butt);
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt1.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values);
                        myactivity.fragmentdata();


                    }

                }

            });

            final String values1 = buttonno.get(1).getProductshortName();
            bt2 = (Button) rootView.findViewById(R.id.product_button2);
            bt2.setText(values1);


            bt2 = (Button) rootView.findViewById(R.id.product_button2);
            bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt2.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));

                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values1);
                        myactivity.fragmentdata();


                    }
                }

            });


            final String values2 = buttonno.get(2).getProductshortName();
            bt3 = (Button) rootView.findViewById(R.id.product_button3);
            bt3.setText(values2);
            bt3= (Button) rootView.findViewById(R.id.product_button3);
            bt3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        Activity activity = getActivity();
                        if (activity instanceof ActivitySalesbill) {
                            ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                            bt3.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                            FastMovingFragment fragobj = new FastMovingFragment();
                            fragobj.myBundle.putString("id_User", values2);
                            myactivity.fragmentdata();


                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });


            final String values3 = buttonno.get(3).getProductshortName();
            bt4 = (Button) rootView.findViewById(R.id.product_button4);
            bt4.setText(values3);

            bt4= (Button) rootView.findViewById(R.id.product_button4);
            bt4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        Activity activity = getActivity();
                        if (activity instanceof ActivitySalesbill) {
                            ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                            bt4.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                            FastMovingFragment fragobj = new FastMovingFragment();
                            fragobj.myBundle.putString("id_User", values3);
                            myactivity.fragmentdata();


                        }
                    } catch (NullPointerException ex) {
                        ex.printStackTrace();
                    }
                }

            });

            final String values4 = buttonno.get(4).getProductshortName();
            bt5 = (Button) rootView.findViewById(R.id.product_button5);
            bt5.setText(values4);
            bt5= (Button) rootView.findViewById(R.id.product_button5);
            bt5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt5.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values4);
                        myactivity.fragmentdata();


                    }
                }

            });


            final String values5 = buttonno.get(5).getProductshortName();
            bt6 = (Button) rootView.findViewById(R.id.product_button6);
            bt6.setText(values5);

            bt6= (Button) rootView.findViewById(R.id.product_button6);
            bt6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt6.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values5);
                        myactivity.fragmentdata();


                    }
                }

            });



            final String values6 = buttonno.get(6).getProductshortName();
            bt7 = (Button) rootView.findViewById(R.id.product_button7);
            bt7.setText(values6);

            bt7= (Button) rootView.findViewById(R.id.product_button7);
            bt7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt7.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values6);
                        myactivity.fragmentdata();


                    }
                }

            });



            final String values7 = buttonno.get(7).getProductshortName();
            bt8 = (Button) rootView.findViewById(R.id.product_button8);
            bt8.setText(values7);

            bt8= (Button) rootView.findViewById(R.id.product_button8);
            bt8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt8.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values7);
                        myactivity.fragmentdata();


                    }
                }

            });



            final String values8 = buttonno.get(8).getProductshortName();
            bt9 = (Button) rootView.findViewById(R.id.product_button9);
            bt9.setText(values8);

            bt9= (Button) rootView.findViewById(R.id.product_button9);
            bt9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt9.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values8);
                        myactivity.fragmentdata();


                    }
                }

            });

            final String values9 = buttonno.get(9).getProductshortName();
            bt10 = (Button) rootView.findViewById(R.id.product_button10);
            bt10.setText(values9);



            bt10= (Button) rootView.findViewById(R.id.product_button10);
            bt10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt10.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values9);
                        myactivity.fragmentdata();


                    }
                }

            });




            final String values10 = buttonno.get(10).getProductshortName();
            bt11 = (Button) rootView.findViewById(R.id.product_button11);
            bt11.setText(values10);


            bt11= (Button) rootView.findViewById(R.id.product_button11);
            bt11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt11.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values10);
                        myactivity.fragmentdata();


                    }
                }

            });


            final String values11 = buttonno.get(11).getProductshortName();
            bt12 = (Button) rootView.findViewById(R.id.product_button12);
            bt12.setText(values11);

            bt12= (Button) rootView.findViewById(R.id.product_button12);
            bt12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt12.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values11);
                        myactivity.fragmentdata();


                    }
                }

            });



            final String values12 = buttonno.get(12).getProductshortName();
            bt13 = (Button) rootView.findViewById(R.id.product_button13);
            bt13.setText(values12);

            bt13= (Button) rootView.findViewById(R.id.product_button13);
            bt13.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt13.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values12);
                        myactivity.fragmentdata();


                    }
                }

            });




            final String values13 = buttonno.get(13).getProductshortName();
            bt14 = (Button) rootView.findViewById(R.id.product_button14);
            bt14.setText(values13);

            bt14= (Button) rootView.findViewById(R.id.product_button14);
            bt14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt14.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values13);
                        myactivity.fragmentdata();


                    }
                }

            });



            final String values14 = buttonno.get(14).getProductshortName();
            bt15 = (Button) rootView.findViewById(R.id.product_button15);
            bt15.setText(values14);

            bt15= (Button) rootView.findViewById(R.id.product_button15);
            bt15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Activity activity = getActivity();
                    if (activity instanceof ActivitySalesbill) {
                        ActivitySalesbill myactivity = (ActivitySalesbill) activity;
                        bt15.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_background));
                        FastMovingFragment fragobj = new FastMovingFragment();
                        fragobj.myBundle.putString("id_User", values14);
                        myactivity.fragmentdata();


                    }
                }

            });





        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }
}
