package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//import com.mycompany.apps.R;
import com.mycompany.apps.R;
import com.mycompany.apps.activity_inventorywithpo;

import java.util.ArrayList;

import Pojo.PurchaseProductModelwithpo;

/**
 * Created by Rahul on 3/25/2016.
 */
public class Inventorywithpoadapter extends ArrayAdapter<PurchaseProductModelwithpo> {

    activity_inventorywithpo activity;
    private final int layoutResourceId;
    ArrayList<PurchaseProductModelwithpo> list;
    LayoutInflater layoutInflater;

    public Inventorywithpoadapter(activity_inventorywithpo activity, int textViewResourceId, ArrayList<PurchaseProductModelwithpo> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }

    public void setList(ArrayList<PurchaseProductModelwithpo> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public PurchaseProductModelwithpo getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView vendorname;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_purchase_vendornamewithpo_row,parent,false);
            holder.vendorname=(TextView)convertView.findViewById(R.id.vendersearch);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.vendorname.setText(list.get(position).getVendorName());
        return convertView;
    }
}
