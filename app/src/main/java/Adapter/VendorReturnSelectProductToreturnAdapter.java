package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.VendorReturnActivity;

import java.util.ArrayList;

import Pojo.VendorReturnModel;

/**
 * Created by rspl-rahul on 3/5/16.
 */
public class VendorReturnSelectProductToreturnAdapter extends ArrayAdapter<VendorReturnModel> {
    VendorReturnActivity activity;
    private final int layoutResourceId;
    ArrayList<VendorReturnModel> list;
    LayoutInflater layoutInflater;

    public VendorReturnSelectProductToreturnAdapter(VendorReturnActivity activity, int textViewResourceId, ArrayList<VendorReturnModel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }



    public void setList(ArrayList<VendorReturnModel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public VendorReturnModel getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView ProductName;
        TextView BatchNumber;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_vendor_return_product,parent,false);
            holder.ProductName=(TextView)convertView.findViewById(R.id.ProductNameVendorReturn);
            holder.BatchNumber=(TextView)convertView.findViewById(R.id.BatchNumberVendorreturn);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.ProductName.setText(list.get(position).getProductName());
        holder.BatchNumber.setText(list.get(position).getBatchno());
        return convertView;
    }
}
