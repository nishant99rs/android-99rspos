package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.Activity_Top_Product;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Topfullproductmodel;

/**
 * Created by Rahul on 5/20/2016.
 */
public class TopproductidandnameAdapter extends ArrayAdapter<Topfullproductmodel> {

    Activity_Top_Product activity;
    private final int layoutResourceId;
    ArrayList<Topfullproductmodel> list;
    LayoutInflater layoutInflater;

    public TopproductidandnameAdapter(Activity_Top_Product activity, int textViewResourceId, ArrayList<Topfullproductmodel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }

    public void setList(ArrayList<Topfullproductmodel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public Topfullproductmodel getItem(int position) {
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
            convertView=layoutInflater.inflate(R.layout.display_top_productidorname_autocomp,parent,false);
            holder.Product_Id=(TextView)convertView.findViewById(R.id.productid);
            holder.Product_Id.setText(list.get(position).getProductId());
            holder.Product_Name=(TextView)convertView.findViewById(R.id.topproductname);
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
