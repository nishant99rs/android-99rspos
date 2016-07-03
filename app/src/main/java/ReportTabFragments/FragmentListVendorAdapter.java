package ReportTabFragments;

/**
 * Created by rspl-madhavi on 14/6/16.
 *
 *
 *
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportDistributorModel;
import Pojo.ReportVendorModel;


public class FragmentListVendorAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<ReportVendorModel> list = new ArrayList<ReportVendorModel>();

    private Context context;

    public FragmentListVendorAdapter(ArrayList<ReportVendorModel> list, Context context) {
        this.list = list;
        this.context = context;
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

    public void addVendorToList(ReportVendorModel id) {
        Log.e("&&&&&&&&", "Adding product " + id.toString() + " to product list");
        list.add(id);
    }

    public static class ViewHolder {
        /*2
        layout created display _report _row which text view are used
        */
        TextView TVVENDR_NM;
        TextView TVVENDR_ACTVE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //**** Inflate tabitem.xml file for each row ( Defined below ) ******
            convertView = layoutInflater.inflate(R.layout.display_vendor_row, null);
            holder = new ViewHolder();
            holder.TVVENDR_NM=(TextView)convertView.findViewById(R.id.rowtvVendr_Nm);
            holder.TVVENDR_ACTVE=(TextView)convertView.findViewById(R.id.rowtvVendr_Act);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TVVENDR_NM.setText(list.get(position).getVend_Nm());
        holder.TVVENDR_ACTVE.setText(list.get(position).getActive());
        return convertView;
    }
    public ArrayList<ReportVendorModel> getList() {
        return list;
    }
}


