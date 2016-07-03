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

import com.mycompany.apps.R;
import com.mycompany.apps.RuleDefinationActivity;

import java.util.ArrayList;

import Pojo.RuleDefinationModel;

/**
 * Created by rspl-nishant on 12/3/16.
 */
public class RuleDefinationAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    RuleDefinationActivity activity;
    ArrayList<RuleDefinationModel> list= new ArrayList<RuleDefinationModel>();

    public RuleDefinationAdapter(ArrayList<RuleDefinationModel> list, RuleDefinationActivity activity) {
        this.list = list;
        this.activity = activity;
    }

    public int getCount() {
        if(list.size()<0)
            return 0;
        Log.e("**get Count***", list.toString());
        return list.size();
    }


    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        Log.e("**********", "" + position);
        return position;
    }
    public static  class ViewHolder{

        TextView StoreID;
        TextView SNO;
        TextView CARD_TYPE;
        TextView BaseValue;
        TextView PerTONPOINTS;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView==null)
        {

            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_rule_defination_row,null);


            holder = new ViewHolder();

            // holder.StoreID=(TextView)convertView.findViewById(R.id.rowtv);
            holder.SNO=(TextView)convertView.findViewById(R.id.rowtvSNO);
            holder.CARD_TYPE=(TextView)convertView.findViewById(R.id.rowCard_type);
            holder.BaseValue=(TextView)convertView.findViewById(R.id.rowtvBasevalue);
            holder.PerTONPOINTS=(TextView)convertView.findViewById(R.id.rowtvPer_ton_points);

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        /*holder.StoreID.setText(list.get(position).getStoreID());*/
        holder.SNO.setText(list.get(position).getSno());
        holder.CARD_TYPE.setText(list.get(position).getCARD_TYPE());
        holder.BaseValue.setText(list.get(position).getBASEVALUE().toString());
        holder.PerTONPOINTS.setText(list.get(position).getPerTONPOINTS().toString());

        return convertView;

    }
}
