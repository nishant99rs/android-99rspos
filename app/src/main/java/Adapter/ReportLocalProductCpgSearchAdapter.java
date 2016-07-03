package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.LocalProductCpgReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportLocalProductCpgModel;

//import com.mycompany.apps.R;


public class ReportLocalProductCpgSearchAdapter extends ArrayAdapter<ReportLocalProductCpgModel> {

    LocalProductCpgReportActivity activity;
    private final int layoutResourceId;
    ArrayList<ReportLocalProductCpgModel>list;
    LayoutInflater layoutInflater;

    public ReportLocalProductCpgSearchAdapter(LocalProductCpgReportActivity activity, int textViewResourceId, ArrayList<ReportLocalProductCpgModel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }

    public void setList(ArrayList<ReportLocalProductCpgModel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public ReportLocalProductCpgModel getItem(int position) {
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
