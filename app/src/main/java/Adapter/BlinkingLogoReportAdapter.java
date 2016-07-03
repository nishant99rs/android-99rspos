package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.AdTickerReportActivity;
import com.mycompany.apps.BlinkingLogoReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.AdTickerReportModel;
import Pojo.BlinkingLogoReportModel;


public class BlinkingLogoReportAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<BlinkingLogoReportModel> list = new ArrayList<BlinkingLogoReportModel>();
    BlinkingLogoReportActivity activity;

    public BlinkingLogoReportAdapter(ArrayList<BlinkingLogoReportModel> list, BlinkingLogoReportActivity activity) {
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

    public void addVendorToList(BlinkingLogoReportModel vendor) {
        Log.e("&&&&&&&&", "Adding product " + vendor.toString() + " to product list");
        list.add(vendor);
    }

    public void setList(ArrayList<BlinkingLogoReportModel> list) {
        this.list = list;
    }

    public static class ViewHolder {
        /*2
        layout created display _report _row which text view are used
        */
        TextView TVAD_ID;
        TextView TVAD_DESC;
        TextView TVAD_START;
        TextView TVAD_END;
        TextView TVAD_COST1;
        TextView TVAD_COST2;
        TextView TVAD_COST3;

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //**** Inflate tabitem.xml file for each row ( Defined below ) ******
            convertView = layoutInflater.inflate(R.layout.display_blinkinglogo_report_row, null);
            holder = new ViewHolder();
            holder.TVAD_ID = (TextView) convertView.findViewById(R.id.rowtvAd_Id);
            holder.TVAD_DESC = (TextView) convertView.findViewById(R.id.rowtvAd_Desc);
            holder.TVAD_START = (TextView) convertView.findViewById(R.id.rowtvAd_Start);
            holder.TVAD_END = (TextView) convertView.findViewById(R.id.rowtvAd_End);
            holder.TVAD_COST1 = (TextView) convertView.findViewById(R.id.rowtv_AdCost1);
            holder.TVAD_COST2 = (TextView) convertView.findViewById(R.id.rowtv_AdCost2);
            holder.TVAD_COST3 = (TextView) convertView.findViewById(R.id.rowtv_AdCost3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.TVAD_ID.setText(list.get(position).getAD_MAIN_ID());
        holder.TVAD_DESC.setText(list.get(position).getAD_DESC());
        holder.TVAD_COST1.setText(list.get(position).getAD_CST_SLB1());
        holder.TVAD_COST2.setText(list.get(position).getAD_CST_SLB2());
        holder.TVAD_COST3.setText(list.get(position).getAD_CST_SLB3());

        try {
            String input = list.get(position).getAD_START_DATE();
            String output = input.substring(0, 10);
            holder.TVAD_START.setText(output.toString());

        }catch(NullPointerException ex)
        {
            ex.printStackTrace();
        }

        try {
            String input = list.get(position).getAD_END_DATE();
            String output = input.substring(0, 10);
            holder.TVAD_END.setText(output.toString());

        }catch(NullPointerException ex)
        {
            ex.printStackTrace();
        }

        return convertView;
    }

    public ArrayList<BlinkingLogoReportModel> getList() {
        return list;
    }
}
