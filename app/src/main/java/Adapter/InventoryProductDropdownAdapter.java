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
import com.mycompany.apps.activity_inventorywithpo;

import java.util.ArrayList;

import Pojo.PurchaseProductModel;
import Pojo.PurchaseProductModelwithpo;

/**
 * Created by rspl-rahul on 27/5/16.
 */
public class InventoryProductDropdownAdapter extends ArrayAdapter<PurchaseProductModelwithpo> {
    activity_inventorywithpo activity;

    private final int layoutResourceId;
    ArrayList<PurchaseProductModelwithpo> list;
    LayoutInflater layoutInflater;
    public InventoryProductDropdownAdapter(activity_inventorywithpo activity, int textViewResourceId, ArrayList<PurchaseProductModelwithpo> list) {
        super(activity, textViewResourceId, list);
        this.layoutResourceId=textViewResourceId;
        this.activity=activity;
        this.list=list;
    }




    PurchaseProductModelwithpo purchaseProductModel;

    public void setList(ArrayList<PurchaseProductModelwithpo> list)
    {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public PurchaseProductModelwithpo getItem(int position)
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
            convertView=layoutInflater.inflate(R.layout.display_inventory_dropdown_row,parent,false);
            // holder.PurchaseId=(TextView)convertView.findViewById(R.id.PurchaseproductId);
            holder.PurchaseName=(TextView)convertView.findViewById(R.id.InventoryproductName);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }
        try {
            holder.PurchaseName.setText(list.get(position).getProductName());
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return convertView;
    }
}