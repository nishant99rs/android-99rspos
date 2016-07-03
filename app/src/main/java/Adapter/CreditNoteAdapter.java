package Adapter;



import android.content.Context;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mycompany.apps.ActivitySalesbill;
import com.mycompany.apps.ActivitySalesreturn;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.CreditNote;
import Pojo.Customer;
import Pojo.Sales;
import Pojo.Salesreturndetail;

/**
     * Created by shilpa on 30/4/16.
     */
    public class CreditNoteAdapter extends ArrayAdapter<CreditNote> {
        private final int layoutResourceId;

        ActivitySalesbill activity;
        private LayoutInflater mInflater;



        public CreditNoteAdapter(ActivitySalesbill activity, int layoutResourceId, ArrayList<CreditNote> mcreditnotelist) {
            super(activity, layoutResourceId, mcreditnotelist);
            this.layoutResourceId = layoutResourceId;
            this.CreditNotelist = mcreditnotelist;
            this.activity = activity;

            this.mInflater = mInflater;
        }





    public void setCreditNotelist(ArrayList<CreditNote> creditNotelist) {
        this.CreditNotelist = creditNotelist;
    }
    private ArrayList<CreditNote> CreditNotelist;
    public int getCount() {
            if (CreditNotelist.size() < 0)
                return 1;
            return CreditNotelist.size();
        }

        public CreditNote getItem(int position) {
            return CreditNotelist.get(position);
        }



        public long getItemId(int position) {

            //.getCustomermobileno();
            return position;
        }




        public static class ViewHolder {


            public TextView billno;
            public TextView creditnotevalue;


        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder;

            if (convertView == null) {

                holder = new ViewHolder();


                LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = mInflater.inflate(R.layout.display_creditnote_row, parent, false);

                holder.billno = (TextView) convertView.findViewById(R.id.billno);

              //  holder.creditnotevalue = (TextView) convertView.findViewById(R.id.creditamount_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();

            }

           holder.billno.setText(CreditNotelist.get(position).getReturnInvoiceno());
            //  holder.creditnotevalue.setText(CreditNotelist.get(position).getCreditnotevalue());

            return convertView;
        }
    }


