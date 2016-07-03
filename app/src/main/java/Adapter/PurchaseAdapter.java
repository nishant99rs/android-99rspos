package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.PurchaseActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.VendorModel;

/**
 * Created by rspl-rahul on 10/3/16.
 */
public class PurchaseAdapter extends ArrayAdapter<VendorModel> {

    PurchaseActivity activity;
    private final int layoutResourceId;
    ArrayList<VendorModel>list;
    LayoutInflater layoutInflater;

    public PurchaseAdapter(PurchaseActivity activity, int textViewResourceId, ArrayList<VendorModel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }

    public void setList(ArrayList<VendorModel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
        return 1;
        return list.size();
    }
    public VendorModel getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

     public static class ViewHolder
    {
        TextView vendorName;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_purchase_vendorname_row,parent,false);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.vendorName=(TextView)convertView.findViewById(R.id.vendorName);
        holder.vendorName.setText(list.get(position).getVendorName());
    return convertView;
    }
}
