package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import Pojo.BlinkingModel;
import com.mycompany.apps.BlinkinglogoActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Rahul on 2/21/2016.
 */
public class BlinkinglogoAdapter extends BaseAdapter {

    private static LayoutInflater layoutInflater;
    BlinkingModel blinkingModel;
    List<BlinkingModel> list =new ArrayList<BlinkingModel>();
    BlinkinglogoActivity activity;
    Context context;

    public BlinkinglogoAdapter(BlinkinglogoActivity activity, ArrayList <BlinkingModel>arrayList) {
        //super(activity, arrayList);

        this.activity=activity;
        this.list=arrayList;
    }
    public long getItemId(int position)
    {
        Log.e("**********", "" + position);
        return position;
    }

    public int getCount() {
        if (list.size()<=0)
            return 1;
        Log.e("**get Count***",list.toString());
        return list.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public static class ViewHolder{
        /*
        layout created display _report _row which text view are used
        */
        TextView TVBlinkingAd_Main_Id;
       /* TextView TVBlinkingStoreId;
        TextView TVBlinkingAd_Start_Date;
        TextView TVBlinkingAd_End_Date;*/
        TextView TVBlinkingAd_Cst_Slb1;
        TextView TVBlinkingAd_Cst_Slb2;
        TextView TVBlinkingAd_Cst_Slb3;
        TextView TVBlinkingAd_Desc;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {

            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_blinkinglogo_row,null);
            holder = new ViewHolder();
            holder.TVBlinkingAd_Main_Id=(TextView)convertView.findViewById(R.id.rowBlinkingtvAd_Main_Id);
           /* holder.TVStoreId=(TextView)convertView.findViewById(R.id.rowtvStoreId);*/
            /*holder.TVAd_Start_Date=(TextView)convertView.findViewById(R.id.rowtvAd_Start_Date);
            holder.TVAd_End_Date=(TextView)convertView.findViewById(R.id.rowtvAd_End_Date);*/
            holder.TVBlinkingAd_Cst_Slb1=(TextView)convertView.findViewById(R.id.rowBlinkingtvAd_Cst_Slb1);
            holder.TVBlinkingAd_Cst_Slb2=(TextView)convertView.findViewById(R.id.rowBlinkingtvAd_Cst_Slb2);
            holder.TVBlinkingAd_Cst_Slb3=(TextView)convertView.findViewById(R.id.rowBlinkingtvAd_Cst_Slb3);
            holder.TVBlinkingAd_Desc=(TextView)convertView.findViewById(R.id.rowBlinkingAd_Desc);

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        try {


        holder.TVBlinkingAd_Main_Id.setText(list.get(position).getAD_MAIN_ID());
        holder.TVBlinkingAd_Desc.setText(list.get(position).getAD_DESC());
              /*  holder.TVStoreId.setText(list.get(position).getSTR_ID());*/
               /* holder.TVAd_Start_Date.setText(list.get(position).getAD_START_DATE());
                holder.TVAd_End_Date.setText(list.get(position).getAD_END_DATE());*/
        holder.TVBlinkingAd_Cst_Slb1.setText(list.get(position).getAD_CST_SLB1());
        holder.TVBlinkingAd_Cst_Slb2.setText(list.get(position).getAD_CST_SLB2());
        holder.TVBlinkingAd_Cst_Slb3.setText(list.get(position).getAD_CST_SLB3());
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
        return convertView;
    }
}
