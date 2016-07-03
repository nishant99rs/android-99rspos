package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.ReportSalesReturnWithoutInvoiceActivity;

import java.util.ArrayList;

import Pojo.SalesReturnReportModel;


public class ReportWithoutInvoiceListAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<SalesReturnReportModel> list=new ArrayList<SalesReturnReportModel>();
    ReportSalesReturnWithoutInvoiceActivity activity;

    public ReportWithoutInvoiceListAdapter(ArrayList<SalesReturnReportModel> list, ReportSalesReturnWithoutInvoiceActivity activity) {
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

    public void addIdToList( SalesReturnReportModel id ) {
        Log.e("&&&&&&&&", "Adding Id " + id.toString() + " to list");
        list.add(id);
    }

    public void setList(ArrayList<SalesReturnReportModel> list) {
        this.list = list;
    }

    public static class ViewHolder{

        TextView TV_GRANDTOTAL;
        TextView TV_REASON;
        TextView TV_TRANSCID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_salesreturn_withoutinvoice_row,null);
            holder = new ViewHolder();
            holder.TV_GRANDTOTAL=(TextView)convertView.findViewById(R.id.rowtvGrand_Total);
            holder.TV_REASON=(TextView)convertView.findViewById(R.id.rowtvReason);
            holder.TV_TRANSCID=(TextView)convertView.findViewById(R.id.rowtvId);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TV_GRANDTOTAL.setText(list.get(position).getGrnTotl());
        holder.TV_REASON.setText(list.get(position).getReason());
        holder.TV_TRANSCID.setText(list.get(position).getTransId());
        return convertView;
    }

    public ArrayList<SalesReturnReportModel> getList() {
        return list;
    }
}
