package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.ProductCpgReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportProductCpgModel;


public class ReportProductCpgSearchAdapter extends ArrayAdapter<ReportProductCpgModel> {

    ProductCpgReportActivity activity;
    private final int layoutResourceId;
    ArrayList<ReportProductCpgModel>list;
    LayoutInflater layoutInflater;

    public ReportProductCpgSearchAdapter(ProductCpgReportActivity activity, int textViewResourceId, ArrayList<ReportProductCpgModel> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }

    public void setList(ArrayList<ReportProductCpgModel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public ReportProductCpgModel getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView productName;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_search_productid_row,parent,false);
            holder.productName=(TextView)convertView.findViewById(R.id.productId);
            holder.productName.setText(list.get(position).getProd_Nm());
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
