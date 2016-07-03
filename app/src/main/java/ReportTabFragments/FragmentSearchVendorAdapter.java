package ReportTabFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportDistributorModel;
import Pojo.ReportVendorModel;

/**
 * Created by rspl-madhavi on 14/6/16.
 */
public class FragmentSearchVendorAdapter extends ArrayAdapter<ReportVendorModel> {

    ArrayList<ReportVendorModel>vendorList;
    private Context context;
    private int resource;
    private View view;


    public FragmentSearchVendorAdapter(Context context, int resource, ArrayList<ReportVendorModel> objects)
    {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.vendorList=objects;
    }


    public void setList(ArrayList<ReportVendorModel> list) {
        this.vendorList = list;
    }

    public int getCount() {
        if(vendorList.size()<0)
            return 1;
        return vendorList.size();
    }
    public ReportVendorModel getItem(int position) {
        return vendorList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView vendorName;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            LayoutInflater  layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_search_vendorname_row,parent,false);
            holder.vendorName=(TextView)convertView.findViewById(R.id.vendorName);
            holder.vendorName.setText(vendorList.get(position).getVend_Nm());
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
