package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//import com.mycompany.apps.R;
import com.mycompany.apps.R;
import com.mycompany.apps.RetailCarddefineActivity;

import java.util.ArrayList;

import Pojo.RetailcardDefineModel;

/**
 * Created by rspl-nishant on 12/3/16.
 */
public class RetailcarddefineAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    RetailCarddefineActivity activity;
    ArrayList<RetailcardDefineModel> list= new ArrayList<RetailcardDefineModel>();

    public RetailcarddefineAdapter(RetailCarddefineActivity activity, ArrayList<RetailcardDefineModel> list) {
        this.activity = activity;
        this.list = list;
    }

    public long getItemId(int position)
    {
        Log.e("**********", "" + position);
        return position;
    }

    public int getCount() {
        if (list.size()<0)
            return 0;
        Log.e("**get Count***",list.toString());
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    public static class ViewHolder{
        /*
        layout created display _report _row which text view are used
        */
        TextView TVStore_ID;
        TextView TVID;
        TextView TVCardType;
        TextView TVPointsRange;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {

            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_retail_carddefine_row,null);


            holder = new ViewHolder();

            holder.TVStore_ID=(TextView)convertView.findViewById(R.id.rowtvstoreid);
            holder.TVID=(TextView)convertView.findViewById(R.id.rowtvid);
            holder.TVCardType=(TextView)convertView.findViewById(R.id.rowtvcardtype);
            holder.TVPointsRange=(TextView)convertView.findViewById(R.id.rowtvPointsRange);

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.TVStore_ID.setText(list.get(position).getSTORID());
        holder.TVID.setText(list.get(position).getID());
        holder.TVCardType.setText(list.get(position).getCARDTYPE());
        holder.TVPointsRange.setText(list.get(position).getPOINTSRANGE());

        return convertView;
    }
}
