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

/**
 * Created by rspl-madhavi on 14/6/16.
 */
public class FragmentSearchDistributorAdapter extends ArrayAdapter<ReportDistributorModel> {

    ArrayList<ReportDistributorModel>distributorList;
    private Context context;
    private int resource;
    private View view;


    public FragmentSearchDistributorAdapter(Context context, int resource, ArrayList<ReportDistributorModel> objects)
    {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.distributorList=objects;
    }


    public void setList(ArrayList<ReportDistributorModel> list) {
        this.distributorList = list;
    }

    public int getCount() {
        if(distributorList.size()<0)
            return 1;
        return distributorList.size();
    }
    public ReportDistributorModel getItem(int position) {
        return distributorList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView DistributorName;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            LayoutInflater  layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_search_distributorname_row,parent,false);
            holder.DistributorName=(TextView)convertView.findViewById(R.id.DistributorName);
            holder.DistributorName.setText(distributorList.get(position).getDstr_Nm());
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }
}
