package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.ActivityLocalProduct;
import com.mycompany.apps.ActivityVendor;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.LocalProduct;
import Pojo.Vendor;

/**
 * Created by rspl-nishant on 10/3/16.
 */
public class localproductadapter  extends ArrayAdapter<LocalProduct>{


    private final int layoutResourceId;
    private Context mcontext;
    ActivityLocalProduct activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;

    public localproductadapter(ActivityLocalProduct activity, int layoutResourceId, ArrayList<LocalProduct> mlocalproductlist) {
        super(activity, layoutResourceId, mlocalproductlist);
        this.activity = activity;
        this.localproductlist = mlocalproductlist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFvendorlistR_SERVICE);


    }
    public void setLocalProductList(ArrayList<LocalProduct> LocalProductList) {
        this.localproductlist= LocalProductList;
    }

    private ArrayList<LocalProduct> localproductlist;
    public int getCount() {
        if (localproductlist.size() < 0)
            return 1;
        return localproductlist.size();
    }


    public LocalProduct getItem(int position) {
        return localproductlist.get(position);
    }


    public long getItemId(int position) {

        //.getCustomermobileno();
        return position;
    }

    public static class ViewHolder {

        public TextView LocalprodId;
        public TextView LocalprodName;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.productlist, parent, false);
           holder.LocalprodId = (TextView) convertView.findViewById(R.id.Productlist_id);
            holder.LocalprodName = (TextView) convertView.findViewById(R.id.Productlist_name);

            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }
       holder.LocalprodName.setText(localproductlist.get(position).getProductname());
         holder.LocalprodId.setText(localproductlist.get(position).getProductid());
        return convertView;


    }




















}
