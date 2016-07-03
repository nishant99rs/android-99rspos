package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.ProcurementReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.VendorReportModel;



public class PurchaseOrderListAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<VendorReportModel> list = new ArrayList<VendorReportModel>();
    ProcurementReportActivity activity;

    public PurchaseOrderListAdapter(ArrayList<VendorReportModel> list, ProcurementReportActivity activity) {
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

    public void addVendorToList(VendorReportModel vendor) {
        Log.e("&&&&&&&&", "Adding product " + vendor.toString() + " to product list");
        list.add(vendor);
    }

    public void setList(ArrayList<VendorReportModel> list) {
        this.list = list;
    }

    public static class ViewHolder {
        /*2
        layout created display _report _row which text view are used
        */
        TextView TVPO_NO;
        TextView TVVENDR_NM;
        TextView TVTOTAL;

    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //**** Inflate tabitem.xml file for each row ( Defined below ) ******
            convertView = layoutInflater.inflate(R.layout.display_purchase_order_row, null);
            holder = new ViewHolder();
            holder.TVPO_NO = (TextView) convertView.findViewById(R.id.rowtvOrder_No);
            holder.TVVENDR_NM = (TextView) convertView.findViewById(R.id.rowtvVendr_Nm);
            holder.TVTOTAL = (TextView) convertView.findViewById(R.id.rowtv_Total);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.TVPO_NO.setText(list.get(position).getPo_No());
        holder.TVVENDR_NM.setText(list.get(position).getVendor_Nm());
        holder.TVTOTAL.setText(list.get(position).getTotal());
        return convertView;
    }

    public ArrayList<VendorReportModel> getList() {
        return list;
    }
}
