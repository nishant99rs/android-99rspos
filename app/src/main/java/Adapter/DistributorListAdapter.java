package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.DistributorReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportDistributorModel;


public class DistributorListAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<ReportDistributorModel>list=new ArrayList<ReportDistributorModel>();
    DistributorReportActivity activity;

    public DistributorListAdapter(ArrayList<ReportDistributorModel> list, DistributorReportActivity activity) {
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

    public void addProductToList( ReportDistributorModel product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
        list.add(product);

    }

    public static class ViewHolder{
        /*
        layout created display _report _row which text view are used
        */
        TextView TVDSBTR_NM;
        TextView TVACTVE;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_distributor_row,null);
            holder = new ViewHolder();
            holder.TVDSBTR_NM=(TextView)convertView.findViewById(R.id.rowtvDstr_Nm);
            holder.TVACTVE=(TextView)convertView.findViewById(R.id.rowtvDstr_Act);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TVDSBTR_NM.setText(list.get(position).getDstr_Nm());
        holder.TVACTVE.setText(list.get(position).getActive());
        return convertView;
    }
}
