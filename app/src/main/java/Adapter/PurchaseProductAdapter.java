package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.mycompany.apps.PurchaseActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.PurchaseProductModel;

/**
 * Created by rspl-rahul on 10/3/16.
 */
public class PurchaseProductAdapter extends ArrayAdapter<PurchaseProductModel> {
    PurchaseActivity activity;
    private final int layoutResourceId;
    ArrayList<PurchaseProductModel> list;
    LayoutInflater layoutInflater;
    public PurchaseProductAdapter(PurchaseActivity activity, int textViewResourceId, ArrayList<PurchaseProductModel> list) {
        super(activity, textViewResourceId, list);
        this.layoutResourceId=textViewResourceId;
        this.activity=activity;
        this.list=list;
    }




    PurchaseProductModel purchaseProductModel;

    public void setList(ArrayList<PurchaseProductModel> list)
    {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public PurchaseProductModel getItem(int position)
    {
        return list.get(position);
    }

    public long getItemId(int position)
    {
        return position;
    }

    public static class ViewHolder
    {
        TextView PurchaseId;
        TextView PurchaseName;
        public   TextView Purchaseprice;
        public EditText EtQty;
        public   TextView Total;
        public   TextView UOM;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null)
        {
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_purchase_product_row,parent,false);
           // holder.PurchaseId=(TextView)convertView.findViewById(R.id.PurchaseproductId);
            holder.PurchaseName=(TextView)convertView.findViewById(R.id.PurchaseproductName);

            holder.Purchaseprice=(TextView)convertView.findViewById(R.id.PurchaseproductPrice);
            holder.EtQty =(EditText)convertView.findViewById(R.id.editText);
            holder.Total=(TextView)convertView.findViewById(R.id.we);
            holder.UOM=(TextView)convertView.findViewById(R.id.UOM);


            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
      //  holder.PurchaseId.setText(list.get(position).getProductId());
        holder.PurchaseName.setText(list.get(position).getProductName());
        return convertView;
    }
}
