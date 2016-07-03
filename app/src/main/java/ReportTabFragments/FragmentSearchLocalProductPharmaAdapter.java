package ReportTabFragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportLocalProductPharmaModel;
import Pojo.ReportProductPharmaModel;

/**
 * Created by rspl-madhavi on 14/6/16.
 */
public class FragmentSearchLocalProductPharmaAdapter extends ArrayAdapter<ReportLocalProductPharmaModel> {

    ArrayList<ReportLocalProductPharmaModel>list;
    private Context context;
    private int resource;
    private View view;


    public FragmentSearchLocalProductPharmaAdapter(Context context, int resource, ArrayList<ReportLocalProductPharmaModel> objects)
    {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.list=objects;
    }


    public void setList(ArrayList<ReportLocalProductPharmaModel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public ReportLocalProductPharmaModel getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView localproductName;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            LayoutInflater  layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_search_localproductid_row,parent,false);
            holder.localproductName=(TextView)convertView.findViewById(R.id.localproductId);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.localproductName.setText(list.get(position).getProd_Nm());
        return convertView;
    }
}
