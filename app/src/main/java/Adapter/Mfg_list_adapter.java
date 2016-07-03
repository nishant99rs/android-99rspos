package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.Activity_Mfg_btl;
import com.mycompany.apps.R;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Pojo.Mfglistmodel;

/**
 * Created by Rahul on 5/5/2016.
 */
public class Mfg_list_adapter  extends BaseAdapter {

    //    private android.app.DatePickerDialog DatePickerDialog;
//    private SimpleDateFormat dateFormat;
    public Pattern pattern;
    public Matcher matcher;

    Activity_Mfg_btl activity;

    ArrayList<Mfglistmodel> list;
    private final int layoutResourceId;
    LayoutInflater layoutInflater;
    Mfglistmodel purchaseProductModel;

    public Mfg_list_adapter(Activity_Mfg_btl activity,  int layoutResourceId, ArrayList<Mfglistmodel> list) {
        this.activity = activity;
        this.list = list;
        this.layoutResourceId = layoutResourceId;
        this.purchaseProductModel = purchaseProductModel;
    }



    public long getItemId(int position)
    {

        Log.e("**********", "" + position);
        return position;
    }

    public int getCount() {
        if (list.size()<0)
            return 1;
        Log.e("**get Count***", list.toString());
        return list.size();

    }
    public Mfglistmodel getItem(int position) {
        return list.get(position);
    }

    public static class ViewHolder
    {


        //TextView PurchaseId;
        public TextView mfgName;
        public TextView targetamount;
        public TextView btldesc;
        public TextView targetvalue;
        public TextView startdate;
        public TextView enddate;


    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final         ViewHolder holder;
        if (convertView==null)
        {
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_mfg_btl_list,parent,false);
            //  holder.PurchaseId=(TextView)convertView.findViewById(R.id.PurchaseproductId);
            holder.mfgName=(TextView)convertView.findViewById(R.id.editmfgname);
            holder.targetamount=(TextView)convertView.findViewById(R.id.editmfgtargetamount);
            holder.btldesc=(TextView)convertView.findViewById(R.id.editmfgbtldesc);
            holder.targetvalue=(TextView)convertView.findViewById(R.id.editmfgtargetvalue);
            holder.startdate=(TextView)convertView.findViewById(R.id.editmfgstartdate);
            holder.enddate =(TextView)convertView.findViewById(R.id.editmfgenddate);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }


//        holder.PurchaseId.setText(list.get(position).getProductId());
        holder.mfgName.setText(list.get(position).getMfgname());
        holder.targetamount.setText(list.get(position).getTargetamount());
        holder.btldesc.setText(list.get(position).getBTLdesc());
        holder.targetvalue.setText(list.get(position).getTargetvalue());
        holder.startdate.setText(list.get(position).getStartdate());

        holder.enddate.setText(list.get(position).getEnddate());

        return convertView;

    }








    public void setList(ArrayList<Mfglistmodel> list) {
        this.list = list;
    }



    public ArrayList<Mfglistmodel> getList() {
        return list;

    }

}
