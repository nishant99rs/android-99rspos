package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.DistributorReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportDistributorModel;


public class DistributorSearchAdapter extends ArrayAdapter<ReportDistributorModel> {

    DistributorReportActivity activity;
    private final int layoutResourceId;
    ArrayList<ReportDistributorModel>distributorList;
    LayoutInflater layoutInflater;

    public DistributorSearchAdapter(DistributorReportActivity activity, int textViewResourceId, ArrayList<ReportDistributorModel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.distributorList=objects;
    }

    public void setList(ArrayList<ReportDistributorModel> list) {
        this.distributorList = list;
    }

    public int getCount() {
        if(distributorList.size()<0)
            return 1;
        return distributorList.size();
    }
    public ReportDistributorModel getItem(int position) {
        return distributorList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView DistributorName;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_search_distributorname_row,parent,false);
            holder.DistributorName=(TextView)convertView.findViewById(R.id.DistributorName);
            holder.DistributorName.setText(distributorList.get(position).getDstr_Nm());
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
