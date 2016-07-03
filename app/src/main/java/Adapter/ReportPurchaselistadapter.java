package Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.ShowPurchaselistActivity;

import java.util.ArrayList;

import Pojo.PurchaseProductReportModel;


/**
 * Created by rspl-rahul on 11/3/16.
 */
public class ReportPurchaselistadapter extends BaseAdapter
{

  ShowPurchaselistActivity activity;
    ArrayList<PurchaseProductReportModel> list;
    private final int layoutResourceId;
    LayoutInflater layoutInflater;
    PurchaseProductReportModel purchaseProductModel;
    boolean isUserEditingQuantityTextView = true;


    public ReportPurchaselistadapter(ShowPurchaselistActivity activity, ArrayList<PurchaseProductReportModel> list, int layoutResourceId, PurchaseProductReportModel purchaseProductModel) {
        Log.e("AAAAAAAA&&&&&&&&&", "in fullproductAdapterclass inside constructor");
        this.activity = activity;
        this.list = list;
        this.layoutResourceId = layoutResourceId;
        this.purchaseProductModel = purchaseProductModel;

    }

    public long getItemId(int position)
    {
        Log.e("**********", "" + position);
        return position;
    }

    public int getCount() {
        if (list.size()<0)
            return 1;
        Log.e("**get Count***", list.toString());
        return list.size();
    }
    public PurchaseProductReportModel getItem(int position) {

        return list.get(position);
    }

    public static class ViewHolder
    {
        public TextView PurchaseName;
        public TextView Purchaseprice;
        public TextView EtQty;
        public TextView Total;
        public TextView UOM;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;

        if (convertView==null)
        {

            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_full_purchaseprocurement_product_row,parent,false);
            holder.Purchaseprice=(TextView)convertView.findViewById(R.id.rowtv_Price);
            holder.PurchaseName=(TextView)convertView.findViewById(R.id.rowtvNm);
            holder.EtQty =(TextView)convertView.findViewById(R.id.rowtv_Qty);
            holder.Total=(TextView)convertView.findViewById(R.id.rowtv_Total);
            holder.UOM=(TextView)convertView.findViewById(R.id.rowtv_Unit);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.PurchaseName.setText(list.get(position).getProductName());
        if( (holder.Purchaseprice.getTag() != null)  && (holder.Purchaseprice.getTag() instanceof TextWatcher) ) {
            holder.Purchaseprice.removeTextChangedListener((TextWatcher) holder.Purchaseprice.getTag() );
        }
        holder.Purchaseprice.setText(String.format("%.2f", list.get(position).getProductPrice()));

        if( (holder.EtQty.getTag() != null)  && (holder.EtQty.getTag() instanceof TextWatcher) ) {
            holder.EtQty.removeTextChangedListener((TextWatcher) holder.EtQty.getTag() );
        }
        holder.EtQty.setText(String.format("%.2f", list.get(position).getProductQuantity()));
        holder.UOM.setText(list.get(position).getUom());
        holder.Total.setText(String.format("%.2f", list.get(position).getTotal()));

        holder.Total.setText(String.valueOf(Double.parseDouble(holder.Purchaseprice.getText().toString()) * Double.parseDouble(holder.EtQty.getText().toString())));
        TextWatcher quantityTextChangeWatcher = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {

                    if (holder.EtQty.getText().toString().equals("")) {
                        Log.w("&&&&&&&&", "Quantity string was NULL hence returning ....");
                        return;
                    }
                    holder.Total.setText(String.valueOf(Double.parseDouble(holder.Purchaseprice.getText().toString()) * Double.parseDouble(holder.EtQty.getText().toString())));
                    list.get(position).setProductQuantity(Float.parseFloat(holder.EtQty.getText().toString()));
                    list.get(position).setProductPrice(Float.parseFloat(holder.Purchaseprice.getText().toString()));
                    Log.e("&&&total&&&&", "$$$$$" + holder.Total.getText().toString());

                } catch (Exception e) {
                    Log.e("&&&&&&&&", "Exception " + e + " while parsing double");
                    e.printStackTrace();
                }
            }
        };

        holder.EtQty.addTextChangedListener(quantityTextChangeWatcher);
        holder.EtQty.setTag(quantityTextChangeWatcher);
        holder.Purchaseprice.addTextChangedListener(quantityTextChangeWatcher);
        holder.Purchaseprice.setTag(quantityTextChangeWatcher);
        return convertView;

    }

    public float getGrandTotal()
    {
        ViewHolder holder=new ViewHolder();
        float finalamount=0.0f;
        for (int listIndex = 0; listIndex < list.size(); listIndex++) {
            try {
                finalamount += (list.get(listIndex).getTotal());
            } catch (Exception e) {

            }
            Log.e("&&&&&^^^^^^^^", "$$$$$$$$" + finalamount);
        }
        Log.e("&&&&55555555&&&", "Total Price is:" + finalamount);
        return finalamount;

    }
}
