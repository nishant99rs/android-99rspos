package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.ActivityProduct;
import com.mycompany.apps.ActivityVendor;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Product;
import Pojo.Vendor;

/**
 * Created by rspl-nishant on 10/3/16.
 */
public class VendorAdapter extends ArrayAdapter<Vendor>
{


    private final int layoutResourceId;
    private Context mcontext;
    ActivityVendor activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;

    public VendorAdapter(ActivityVendor activity, int layoutResourceId, ArrayList<Vendor> mvendorlist) {
        super(activity, layoutResourceId, mvendorlist);
        this.activity = activity;
        this.vendorlist = mvendorlist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFvendorlistR_SERVICE);


    }
    public void setVendorList(ArrayList<Vendor> VendorList) {
        this.vendorlist = VendorList;
    }

    private ArrayList<Vendor> vendorlist;
    public int getCount() {
        if (vendorlist.size() < 0)
            return 1;
        return vendorlist.size();
    }


    public Vendor getItem(int position) {
        return vendorlist.get(position);
    }


    public long getItemId(int position) {

        //.getCustomermobileno();
        return position;
    }

    public static class ViewHolder {

        public TextView VendorId;
        public TextView VendorName;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.productlist, parent, false);
            holder.VendorId = (TextView) convertView.findViewById(R.id.Productlist_id);
            holder.VendorName = (TextView) convertView.findViewById(R.id.Productlist_name);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.VendorId.setText(vendorlist.get(position).getVendorId());
        holder.VendorName.setText(vendorlist.get(position).getVendorname());

        return convertView;


    }





}
