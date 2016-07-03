package Adapter;

/**
 * Created by rspl-nishant on 8/3/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.mycompany.apps.ActivityCustomer;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Customer;

/**
 * Created by w7 on 2/16/2016.
 */
public class CustomerAdapter extends ArrayAdapter<Customer> {
    private final int layoutResourceId;
    private Context mcontext;
    ActivityCustomer activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;

    public CustomerAdapter(ActivityCustomer activity, int layoutResourceId, ArrayList<Customer> mcustomerlist) {
        super(activity, layoutResourceId, mcustomerlist);
        this.activity = activity;
        this.customerlist = mcustomerlist;
        this.layoutResourceId = layoutResourceId;


    }

        public void setCustomerList(ArrayList<Customer> customerList) {
            this.customerlist = customerList;
        }

    private ArrayList<Customer> customerlist;
    public int getCount() {
        if (customerlist.size() < 0)
            return 1;
        return customerlist.size();
    }


    public Customer getItem(int position) {
        return customerlist.get(position);
    }


    public long getItemId(int position) {

        //.getCustomermobileno();
        return position;
    }

    public static class ViewHolder {

        public TextView name;
        public TextView mobile_no;
        public TextView email;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.customeritems, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.Customerlist_name);
            holder.mobile_no = (TextView) convertView.findViewById(R.id.Customerlist_mobile);
            convertView.setTag(holder);
        }
       
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(customerlist.get(position).getCustomername());
        holder.mobile_no.setText(customerlist.get(position).getCustomermobileno());

        return convertView;


    }
}