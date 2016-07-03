package ReportTabFragments;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.DirectVendorPaymentModel;
import Pojo.IndirectVendorPaymentModel;

public class FragmentIndirectCashListAdapter extends BaseAdapter{

    LayoutInflater layoutInflater;
    ArrayList<IndirectVendorPaymentModel> list = new ArrayList<IndirectVendorPaymentModel>();
    private  Context context;

    public FragmentIndirectCashListAdapter(ArrayList<IndirectVendorPaymentModel> list, Context context) {
        super();
        this.list = list;
        this.context = context;
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
        return position;
    }

    public void addIdToList( IndirectVendorPaymentModel id ) {
        Log.e("&&&&&&&&", "Adding Id " + id.toString() + " to list");
        list.add(id);
    }

    public void setList(ArrayList<IndirectVendorPaymentModel> list) {
        this.list = list;
    }

    public static class ViewHolder{

        TextView TVVENDOR_NM;
        TextView TVAMNT_PAID;
        TextView TVAMNT_RECVD;
        TextView TVAMNT_DUE;
        TextView TV_REASON;
        TextView TVLAST_UPDATE;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_report_indirect_pay_by_cash,null);
            holder = new ViewHolder();
            holder.TVVENDOR_NM=(TextView)convertView.findViewById(R.id.reportVend_Nm);
            holder.TVAMNT_PAID=(TextView)convertView.findViewById(R.id.reportAmnt_Paid);
            holder.TVAMNT_RECVD=(TextView)convertView.findViewById(R.id.reportAmnt_Recvd);
            holder.TVAMNT_DUE=(TextView)convertView.findViewById(R.id.reportAmnt_Due);
            holder.TV_REASON=(TextView)convertView.findViewById(R.id.reportReasn_Pay);
            holder.TVLAST_UPDATE=(TextView)convertView.findViewById(R.id.reportLast_Update);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TVVENDOR_NM.setText(list.get(position).getVendorNm());
        holder.TVAMNT_PAID.setText(list.get(position).getAmountPaid());
        holder.TVAMNT_RECVD.setText(list.get(position).getAmountRcvd());
        holder.TVAMNT_DUE.setText(list.get(position).getAmountDue());
        holder.TV_REASON.setText(list.get(position).getReasonOfPay());
        holder.TVLAST_UPDATE.setText(list.get(position).getLastUpdate());
        return convertView;
    }

    public ArrayList<IndirectVendorPaymentModel> getList() {
        return list;
    }








}
