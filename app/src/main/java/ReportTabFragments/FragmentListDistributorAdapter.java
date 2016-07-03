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

import Pojo.InventoryReportModel;
import Pojo.ReportDistributorModel;


public class FragmentListDistributorAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<ReportDistributorModel> list = new ArrayList<ReportDistributorModel>();
    private Context context;

    public FragmentListDistributorAdapter(ArrayList<ReportDistributorModel> list, Context context) {
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

    public void addDistributorList(ReportDistributorModel id) {
        Log.e("&&&&&&&&", "Adding product " + id.toString() + " to product list");
        list.add(id);
    }

    public void addProductToList( ReportDistributorModel product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
        list.add(product);

    }
    public static class ViewHolder {
        /*2
        layout created display _report _row which text view are used
        */
        TextView TVDSBTR_NM;
        TextView TVACTVE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //**** Inflate tabitem.xml file for each row ( Defined below ) ******
            convertView = layoutInflater.inflate(R.layout.display_distributor_row, null);
            holder = new ViewHolder();
            holder.TVDSBTR_NM=(TextView)convertView.findViewById(R.id.rowtvDstr_Nm);
            holder.TVACTVE=(TextView)convertView.findViewById(R.id.rowtvDstr_Act);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TVDSBTR_NM.setText(list.get(position).getDstr_Nm());
        holder.TVACTVE.setText(list.get(position).getActive());
        return convertView;
    }
    public ArrayList<ReportDistributorModel> getList() {
        return list;
    }
}


