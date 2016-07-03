package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mycompany.apps.R;
import com.mycompany.apps.ReportVendorReturnActivity;
import java.util.ArrayList;

import Pojo.ReportVendorReturnModel;

public class ReportSearchReturnIdAdapter extends ArrayAdapter<ReportVendorReturnModel> {

    ReportVendorReturnActivity activity;
    private final int layoutResourceId;
    ArrayList<ReportVendorReturnModel>idList;
    LayoutInflater layoutInflater;

    public ReportSearchReturnIdAdapter(ReportVendorReturnActivity activity, int textViewResourceId, ArrayList<ReportVendorReturnModel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.idList=objects;
    }

    public void setList(ArrayList<ReportVendorReturnModel> list) {
        this.idList = list;
    }

    public int getCount() {
        if(idList.size()<0)
            return 1;
        return idList.size();
    }
    public ReportVendorReturnModel getItem(int position) {
        return idList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView returnId;
        TextView vendorNm;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.report_display_search_retrnid_row,parent,false);
            holder.returnId=(TextView)convertView.findViewById(R.id.ReturnId);
            holder.vendorNm=(TextView)convertView.findViewById(R.id.VendNm);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.returnId.setText(idList.get(position).getVendrId());
        holder.vendorNm.setText(idList.get(position).getVendrNm());
        return convertView;
    }
}
