package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.Activity_lineitem_discount;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.line_item_Product_Model;

/**
 * Created by rspl-gourav on 23/5/16.
 */
public class line_productId_Name_adapter  extends ArrayAdapter<line_item_Product_Model> {

    Activity_lineitem_discount activity;
    private final int layoutResourceId;
    ArrayList<line_item_Product_Model> list;
    LayoutInflater layoutInflater;

    public line_productId_Name_adapter(Activity_lineitem_discount activity, int textViewResourceId, ArrayList<line_item_Product_Model> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }

    public void setList(ArrayList<line_item_Product_Model> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public line_item_Product_Model getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView Product_Id;
        TextView Product_Name;

    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_lineitem_productid_name,parent,false);
            holder.Product_Id=(TextView)convertView.findViewById(R.id.lineproductid);
            holder.Product_Id.setText(list.get(position).getProductId());
            holder.Product_Name=(TextView)convertView.findViewById(R.id.lineproductname);
            holder.Product_Name.setText(list.get(position).getProductname());


            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
