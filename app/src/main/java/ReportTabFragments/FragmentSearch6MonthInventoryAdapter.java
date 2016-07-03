package ReportTabFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.InventoryReportModel;


public class FragmentSearch6MonthInventoryAdapter extends ArrayAdapter<InventoryReportModel> {



    ArrayList<InventoryReportModel> idList;
    private Context context;
    private int resource;
    private View view;


    public FragmentSearch6MonthInventoryAdapter(Context context, int resource, ArrayList<InventoryReportModel> objects)
    {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.idList=objects;
    }




    public void setList(ArrayList<InventoryReportModel> list) {
        this.idList = list;
    }

    public int getCount() {
        if(idList.size()<0)
            return 1;
        return idList.size();
    }
    public InventoryReportModel getItem(int position) {
        return idList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView productId;
        TextView productNm;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            LayoutInflater  layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.report_display_search_prodid_row,parent,false);
            holder.productId=(TextView)convertView.findViewById(R.id.ProdId);
            holder.productNm=(TextView)convertView.findViewById(R.id.ProdNm);
            holder.productId.setText(idList.get(position).getProd_Id());
            holder.productNm.setText(idList.get(position).getProd_Nm());
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }







}
