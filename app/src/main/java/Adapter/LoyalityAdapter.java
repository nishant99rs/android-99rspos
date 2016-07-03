package Adapter;

/**
 * Created by rspl-nishant on 12/3/16.
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.LoyaltyReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.LoyalityModel;

/**
 * Created by rspl-rahul on 7/3/16.
 */
public class LoyalityAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<LoyalityModel> list=new ArrayList<LoyalityModel>();
    LoyaltyReportActivity activity;
    public LoyalityAdapter(ArrayList<LoyalityModel> list, LoyaltyReportActivity activity) {
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
        return list.size();
    }

    public static class ViewHolder{
        /*
        layout created display _report _row which text view are used
        */
        TextView TVCUST_ID;
        TextView TVMOBILE_NO;
        TextView TVNAME;
        TextView TVPOINTSEARNED;
        TextView TVPOINTSREDEEMED;
        TextView TVPOINTSAVAILABLE;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {

            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_loyalityreport_row,null);


            holder = new ViewHolder();

            holder.TVCUST_ID=(TextView)convertView.findViewById(R.id.rowtvCust_Id);
            holder.TVMOBILE_NO=(TextView)convertView.findViewById(R.id.rowtvMobile_No);
            holder.TVNAME=(TextView)convertView.findViewById(R.id.rowtvName);
            holder.TVPOINTSEARNED=(TextView)convertView.findViewById(R.id.rowtvPointsEarned);
            holder.TVPOINTSREDEEMED=(TextView)convertView.findViewById(R.id.rowtvpointsRedeemed);
            holder.TVPOINTSAVAILABLE=(TextView)convertView.findViewById(R.id.rowtvpointsavailable);
            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.TVCUST_ID.setText(list.get(position).getCust_id());
        holder.TVMOBILE_NO.setText(list.get(position).getMobile_No());
        holder.TVNAME.setText(list.get(position).getName());
        holder.TVPOINTSEARNED.setText(list.get(position).getPoints_Earned().toString());
        holder.TVPOINTSREDEEMED.setText(list.get(position).getPoints_Redeemed().toString());
        holder.TVPOINTSAVAILABLE.setText(list.get(position).getPoints_Available().toString());
        return convertView;
    }
}
