package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mycompany.apps.Activity_lineitem_discount;
import com.mycompany.apps.DBhelper;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.line_item_Product_Model;


/**
 * Created by rspl-richa on 23/6/16.
 */
public class LineItemDiscount_DropDown extends BaseAdapter{
    LayoutInflater layoutInflater;
    Activity_lineitem_discount activity;
    ArrayList<line_item_Product_Model> list;
    DBhelper helper;

    public LineItemDiscount_DropDown(ArrayList<line_item_Product_Model> list, Activity_lineitem_discount activity) {
        this.list = list;
        this.activity = activity;
    }

    public long getItemId(int position) {
        Log.e("**********", "" + position);
        return position;
    }

    public int getCount() {
        if (list.size() < 0)
            return 1;
        Log.e("**get Count***", list.toString());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    public void addVendorToList(line_item_Product_Model vendor) {
        Log.e("&&&&&&&&", "Adding product " + vendor.toString() + " to product list");
        list.add(vendor);
    }

    public void setList(ArrayList<line_item_Product_Model> list) {
        this.list = list;
    }

    public static class ViewHolder {

        TextView Product_Name;
        ImageButton DeleteBtn;

    }

    @Override

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.drop_down_lineitemdiscount, null);
            holder = new ViewHolder();

            holder.Product_Name=(TextView)convertView.findViewById(R.id.rowtvOrder_No);
            holder.Product_Name.setText(list.get(position).getProductname());
            holder.DeleteBtn=(ImageButton)convertView.findViewById(R.id.deleteButton) ;


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.Product_Name.setText(list.get(position).getProductname());
        holder.DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper=new DBhelper(activity);
                helper.DeleteRecordLineItem(list.get(position).getProductname());
                list.remove(position);
                notifyDataSetChanged();


            }
        });


        return convertView;
    }

    public ArrayList<line_item_Product_Model> getList() {
        return list;
    }
}