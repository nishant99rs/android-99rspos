package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.ActivityProduct;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Product;
import Pojo.ProductAuto;

/**
 * Created by rspl-nishant on 21/3/16.
 */
public class ProductAutoAdapter extends ArrayAdapter<ProductAuto> {


    private final int layoutResourceId;
    private Context mcontext;
    ActivityProduct activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;

    public ProductAutoAdapter(ActivityProduct activity, int layoutResourceId, ArrayList<ProductAuto> mproductAutoArraylist) {
        super(activity, layoutResourceId, mproductAutoArraylist);
        this.activity = activity;
        this.productAutoArraylist = mproductAutoArraylist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    public void setproductAutoArrayList(ArrayList<ProductAuto> productAutoArrayList) {
        this.productAutoArraylist = productAutoArrayList;
    }

    private ArrayList<ProductAuto> productAutoArraylist;
    public int getCount() {
        if (productAutoArraylist.size() < 0)
            return 1;
        return productAutoArraylist.size();
    }


    public ProductAuto getItem(int position) {
        return productAutoArraylist.get(position);
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

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.activity_activity_product, parent, false);
            holder.ProductId = (TextView) convertView.findViewById(R.id.product_Prodid);
            holder.ProductName = (TextView) convertView.findViewById(R.id.product_Desc);
            //  holder.email = (TextView) convertView.findViewById(R.id.textView12);
//            holder.ProductId.setText(Productlist.get(position).getProductId());
//            holder.ProductName.setText(Productlist.get(position).getProductName());
            //holder.email.setText(customerlist.get(position).getCustomeremail());
            convertView.setTag(holder);
        }
        //holder.name.setText(mcustomerlist.get(position).getCustomername());
        else {
            holder = (ViewHolder) convertView.getTag();
        }
       holder.ProductId.setText(productAutoArraylist.get(position).getAutoProductId());
        holder.ProductName.setText(productAutoArraylist.get(position).getAutoProductname());

        return convertView;


    }


























}
