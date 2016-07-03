package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.ReportSaleActivity;

import java.util.ArrayList;

import Pojo.SaleReportModel;


public class GrandTotalListAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<SaleReportModel> list=new ArrayList<SaleReportModel>();
    ReportSaleActivity activity;

    public GrandTotalListAdapter(ArrayList<SaleReportModel> list, ReportSaleActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    public long getItemId(int position)
    {
        Log.e("**********", "" + position);
        return position;
    }

    public int getCount() {
        if (list.size()<0)
            return 1;
        Log.e("**get Count***", list.toString());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    public void addIdToList( SaleReportModel id ) {
        Log.e("&&&&&&&&", "Adding Id " + id.toString() + " to list");
        list.add(id);
    }

    public void setList(ArrayList<SaleReportModel> list) {
        this.list = list;
    }

    public static class ViewHolder{

        TextView TV_GRANDTOTAL;
        TextView TV_UOM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_grandtotal_row,null);
            holder = new ViewHolder();
            holder.TV_GRANDTOTAL=(TextView)convertView.findViewById(R.id.rowtvGrand_Total);
            holder.TV_UOM=(TextView)convertView.findViewById(R.id.rowtvUOM);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TV_GRANDTOTAL.setText(list.get(position).getGrnTotl());
        holder.TV_UOM.setText(list.get(position).getUom());
        return convertView;
    }

    public ArrayList<SaleReportModel> getList() {
        return list;
    }
}
