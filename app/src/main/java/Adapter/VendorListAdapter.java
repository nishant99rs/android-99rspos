package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.VendorReportActivity;

import java.util.ArrayList;

import Pojo.ReportVendorModel;


public class VendorListAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<ReportVendorModel>list=new ArrayList<ReportVendorModel>();
    VendorReportActivity activity;

    public VendorListAdapter(ArrayList<ReportVendorModel> list, VendorReportActivity activity) {
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
        Log.e("**get Count***",list.toString());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public void addProductToList( ReportVendorModel product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
        list.add(product);

    }


    public static class ViewHolder{
        /*
        layout created display _report _row which text view are used
        */
        TextView TVVENDR_NM;
        TextView TVVENDR_ACTVE;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_vendor_row,null);
            holder = new ViewHolder();
            holder.TVVENDR_NM=(TextView)convertView.findViewById(R.id.rowtvVendr_Nm);
            holder.TVVENDR_ACTVE=(TextView)convertView.findViewById(R.id.rowtvVendr_Act);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TVVENDR_NM.setText(list.get(position).getVend_Nm());
        holder.TVVENDR_ACTVE.setText(list.get(position).getActive());
        return convertView;
    }
}
