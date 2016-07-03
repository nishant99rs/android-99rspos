package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.ActivitySalesbill;
import com.mycompany.apps.R;

//import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Sales;

/**
 * Created by shilpa on 2/4/16.
 */
public class SalesProductNmAdaptor extends ArrayAdapter<Sales> {

    ActivitySalesbill activity;
    private final int layoutResourceId;
    ArrayList<Sales> ProductNamelist;
    LayoutInflater layoutInflater;

    public SalesProductNmAdaptor(ActivitySalesbill activity, int textViewResourceId, ArrayList<Sales> ProductNamelist) {
        super(activity, textViewResourceId, ProductNamelist);
        this.layoutResourceId = textViewResourceId;
        this.activity = activity;
        this.ProductNamelist = ProductNamelist;
    }


    // Sales SalesProductModel;

    public void setList(ArrayList<Sales> ProductNamelist)

    {
        this.ProductNamelist = ProductNamelist;
    }

    public int getCount() {
        if (ProductNamelist.size() < 0)
            return 1;
        return ProductNamelist.size();
    }

    public Sales getItem(int position) {
        return ProductNamelist.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView ProductName;
        public TextView BatchNo;
        public TextView Expiry;
        public TextView Quantity;
        public TextView Productid;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.display_productname_row, parent, false);
            holder.ProductName = (TextView) convertView.findViewById(R.id.productname);
           // holder.Productid = (TextView) convertView.findViewById(R.id.productid);

            holder.BatchNo = (TextView) convertView.findViewById(R.id.batchno);
            holder.Expiry = (TextView) convertView.findViewById(R.id.productexp);
            holder.Quantity = (TextView) convertView.findViewById(R.id.quantity);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        if (position > 0) {
            if (ProductNamelist.get(position).getProdid().trim().equals(ProductNamelist.get(position - 1).getProdid().trim())) {
                holder.ProductName.setVisibility(View.VISIBLE);
              //  holder.Productid.setVisibility(View.INVISIBLE);
                holder.Quantity.setVisibility(View.VISIBLE);
                holder.Expiry.setVisibility(View.VISIBLE);
            } else {
                holder.ProductName.setVisibility(View.VISIBLE);
               // holder.Productid.setVisibility(View.VISIBLE);
                holder.Quantity.setVisibility(View.VISIBLE);
                holder.Expiry.setVisibility(View.VISIBLE);
            }
        }

        holder.ProductName.setText(ProductNamelist.get(position).getProductName());

       // holder.Productid.setText(ProductNamelist.get(position).getProdid());
        holder.BatchNo.setText(ProductNamelist.get(position).getBatchNo());
        holder.Expiry.setText(ProductNamelist.get(position).getExpiry());
        holder.Quantity.setText(String.format("%.2f", ProductNamelist.get(position).getStockquant()));
        return convertView;

    }

}