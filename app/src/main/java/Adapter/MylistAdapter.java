package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import Pojo.ListModel;
import com.mycompany.apps.R;
import com.mycompany.apps.ReportActivity;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Rahul on 2/15/2016.
 */
public class MylistAdapter extends BaseAdapter{

    private static LayoutInflater layoutInflater;
    ListModel listModel;
    List<ListModel> list =new ArrayList<ListModel>();
   ReportActivity activity;
    Context context;

    public MylistAdapter(ReportActivity activity, ArrayList <ListModel>arrayList) {
        //super(activity, arrayList);

        this.activity=activity;
        this.list=arrayList;
    }


    /*public void add(ListModel listModel) {
        Log.e("***List*",list.toString());

        list.add(listModel);
    }
*/
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

    @Override
    public Object getItem(int position) {
        return null;
    }

    public static class ViewHolder{
        /*
        layout created display _report _row which text view are used
        */
        TextView TVAd_Main_Id;
        TextView TVStoreId;
        TextView TVAd_Start_Date;
        TextView TVAd_End_Date;
        TextView TVAd_Cst_Slb1;
        TextView TVAd_Cst_Slb2;
        TextView TVAd_Cst_Slb3;
        TextView TVAd_Text;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView==null)
        {

            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.diplay_report_row,null);


            holder = new ViewHolder();

            holder.TVAd_Main_Id=(TextView)convertView.findViewById(R.id.rowtvAd_Main_Id);
           /* holder.TVStoreId=(TextView)convertView.findViewById(R.id.rowtvStoreId);*/
            /*holder.TVAd_Start_Date=(TextView)convertView.findViewById(R.id.rowtvAd_Start_Date);
            holder.TVAd_End_Date=(TextView)convertView.findViewById(R.id.rowtvAd_End_Date);*/
            holder.TVAd_Cst_Slb1=(TextView)convertView.findViewById(R.id.rowtvAd_Cst_Slb1);
            holder.TVAd_Cst_Slb2=(TextView)convertView.findViewById(R.id.rowtvAd_Cst_Slb2);
            holder.TVAd_Cst_Slb3=(TextView)convertView.findViewById(R.id.rowtvAd_Cst_Slb3);
            holder.TVAd_Text=(TextView)convertView.findViewById(R.id.rowAd_Text);

            convertView.setTag(holder);

        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
                try {


                    holder.TVAd_Main_Id.setText(list.get(position).getAD_MAIN_ID());
                    holder.TVAd_Text.setText(list.get(position).getAD_TEXT());
              /*  holder.TVStoreId.setText(list.get(position).getSTR_ID());*/
               /* holder.TVAd_Start_Date.setText(list.get(position).getAD_START_DATE());
                holder.TVAd_End_Date.setText(list.get(position).getAD_END_DATE());*/
                    holder.TVAd_Cst_Slb1.setText(list.get(position).getAD_CST_SLB1());
                    holder.TVAd_Cst_Slb2.setText(list.get(position).getAD_CST_SLB2());
                    holder.TVAd_Cst_Slb3.setText(list.get(position).getAD_CST_SLB3());
                }catch (IndexOutOfBoundsException ex){
                    ex.printStackTrace();
                }
        return convertView;
    }
}
