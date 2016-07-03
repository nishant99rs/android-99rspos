package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.Activity_Salesreturn_withoutinvoiceno;
import com.mycompany.apps.R;

//import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Salesreturndetail;
import Pojo.SalesreturndetailWithoutPo;

/**
 * Created by shilpa on 2/4/16.
 */
public class SalesReturnProductAdapter extends ArrayAdapter<SalesreturndetailWithoutPo> {

    Activity_Salesreturn_withoutinvoiceno activity;
    private final int layoutResourceId;
    ArrayList<SalesreturndetailWithoutPo> ProductNamelist;
    LayoutInflater layoutInflater;

    public SalesReturnProductAdapter(Activity_Salesreturn_withoutinvoiceno activity, int textViewResourceId, ArrayList<SalesreturndetailWithoutPo> ProductNamelist) {
        super(activity, textViewResourceId, ProductNamelist);
        this.layoutResourceId = textViewResourceId;
        this.activity = activity;
        this.ProductNamelist = ProductNamelist;
    }


    // Sales SalesProductModel;

    public void setList(ArrayList<SalesreturndetailWithoutPo> ProductNamelist)

    {
        this.ProductNamelist = ProductNamelist;
    }

    public int getCount() {
        if (ProductNamelist.size() < 0)
            return 1;
        return ProductNamelist.size();
    }

    public SalesreturndetailWithoutPo getItem(int position) {
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
            convertView = layoutInflater.inflate(R.layout.display_salereturnproduct_row, parent, false);
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
            if (ProductNamelist.get(position).getSaleProdid().trim().equals(ProductNamelist.get(position - 1).getSaleProdid().trim())) {
                holder.ProductName.setVisibility(View.VISIBLE);
                //  holder.Productid.setVisibility(View.INVISIBLE);
                //holder.Quantity.setVisibility(View.INVISIBLE);
               // holder.Expiry.setVisibility(View.INVISIBLE);

            } else {
                holder.ProductName.setVisibility(View.VISIBLE);
                // holder.Productid.setVisibility(View.VISIBLE);
                //holder.Quantity.setVisibility(View.VISIBLE);
                //holder.Expiry.setVisibility(View.VISIBLE);

            }
        }

        holder.ProductName.setText(ProductNamelist.get(position).getSaleproductname());
        // holder.Productid.setText(ProductNamelist.get(position).getProdid());
       // holder.BatchNo.setText(ProductNamelist.get(position).getSalebatchno());
       // holder.Expiry.setText(ProductNamelist.get(position).getS());
       // holder.Quantity.setText(String.format("%d", ProductNamelist.get(position).getQuantity()));
        return convertView;

    }

}