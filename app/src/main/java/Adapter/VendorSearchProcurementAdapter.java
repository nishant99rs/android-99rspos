package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.ProcurementReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;


public class VendorSearchProcurementAdapter extends ArrayAdapter<String> {

    ProcurementReportActivity activity;
    private final int layoutResourceId;
    ArrayList<String> vendorList;
    LayoutInflater layoutInflater;

    public VendorSearchProcurementAdapter(ProcurementReportActivity activity, int textViewResourceId, ArrayList<String> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.vendorList=objects;
    }

    public void setList(ArrayList<String> list) {
        this.vendorList = list;
    }

    public int getCount() {
        if(vendorList.size()<0)
            return 1;
        return vendorList.size();
    }
    public String getItem(int position) {
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

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.vendorName.setText(vendorList.get(position).toString());
        return convertView;
    }
}
