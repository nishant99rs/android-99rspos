package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.LocalProductPharmaReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportLocalProductPharmaModel;

//import com.mycompany.apps.R;


public class ReportLocalProductPharmaSearchAdapter extends ArrayAdapter<ReportLocalProductPharmaModel> {

    LocalProductPharmaReportActivity activity;
    private final int layoutResourceId;
    ArrayList<ReportLocalProductPharmaModel>list;
    LayoutInflater layoutInflater;

    public ReportLocalProductPharmaSearchAdapter(LocalProductPharmaReportActivity activity, int textViewResourceId, ArrayList<ReportLocalProductPharmaModel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }

    public void setList(ArrayList<ReportLocalProductPharmaModel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public ReportLocalProductPharmaModel getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView localproductName;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_search_localproductid_row,parent,false);
            holder.localproductName=(TextView)convertView.findViewById(R.id.localproductId);
            holder.localproductName.setText(list.get(position).getProd_Nm());
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
