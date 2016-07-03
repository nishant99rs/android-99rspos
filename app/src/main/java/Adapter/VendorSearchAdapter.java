package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.VendorReportActivity;

import java.util.ArrayList;

import Pojo.ReportVendorModel;


public class VendorSearchAdapter extends ArrayAdapter<ReportVendorModel> {

    VendorReportActivity activity;
    private final int layoutResourceId;
    ArrayList<ReportVendorModel>vendorList;
    LayoutInflater layoutInflater;

    public VendorSearchAdapter(VendorReportActivity activity, int textViewResourceId, ArrayList<ReportVendorModel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.vendorList=objects;
    }

    public void setList(ArrayList<ReportVendorModel> list) {
        this.vendorList = list;
    }

    public int getCount() {
        if(vendorList.size()<0)
            return 1;
        return vendorList.size();
    }
    public ReportVendorModel getItem(int position) {
        return vendorList.get(position);
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
            convertView=layoutInflater.inflate(R.layout.display_search_vendorname_row,parent,false);
            holder.vendorName=(TextView)convertView.findViewById(R.id.vendorName);
            holder.vendorName.setText(vendorList.get(position).getVend_Nm());
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
