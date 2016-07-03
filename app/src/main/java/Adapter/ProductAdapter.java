package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.mycompany.apps.ActivityCustomer;
import com.mycompany.apps.ActivityProduct;
import com.mycompany.apps.R;

import java.util.ArrayList;
import java.util.List;

import Pojo.Customer;
import Pojo.Product;

/**
 * Created by rspl-nishant on 10/3/16.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    private final int layoutResourceId;
    private Context mcontext;
    ActivityProduct activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;

    public ProductAdapter(ActivityProduct activity, int layoutResourceId, ArrayList<Product> mProductlist) {
        super(activity, layoutResourceId, mProductlist);
        this.activity = activity;
        this.Productlist = mProductlist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    public void setProductList(ArrayList<Product> ProductList)
    {
        this.Productlist = ProductList;
    }

    private ArrayList<Product> Productlist;
    public int getCount() {
        if (Productlist.size() < 0)
            return 1;
        return Productlist.size();
    }


    public Product getItem(int position) {
        return Productlist.get(position);
    }


    public long getItemId(int position) {

        //.getCustomermobileno();
        return position;
    }

    public static class ViewHolder {

        public TextView ProductId;
        public TextView ProductName;


    }

    public View getView(int position, View convertView, ViewGroup parent) {

      final  ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();
            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.productlist, parent, false);
            holder.ProductId = (TextView) convertView.findViewById(R.id.Productlist_id);
            holder.ProductName = (TextView) convertView.findViewById(R.id.Productlist_name);
            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.ProductId.setText(Productlist.get(position).getProductId());
        holder.ProductName.setText(Productlist.get(position).getProductName());
        return convertView;


    }


}
