package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.PurchaseActivity;
import com.mycompany.apps.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Pojo.PurchaseInvoiceDropDownModel;

/**
 * Created by rspl-nishant on 21/5/16.
 */
public class PurchaseInvoiceNoListAdapter extends BaseAdapter {
    PurchaseActivity activity;
    private final int layoutResourceId;
    ArrayList<PurchaseInvoiceDropDownModel> list;
    LayoutInflater layoutInflater;

    public PurchaseInvoiceNoListAdapter(PurchaseActivity activity, int textViewResourceId, ArrayList<PurchaseInvoiceDropDownModel> objects) {
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.list=objects;
    }



    public void setList(ArrayList<PurchaseInvoiceDropDownModel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public PurchaseInvoiceDropDownModel getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView PoNumber;
        TextView TimeStamp;
        TextView Flag;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_ponumberdropdown_row,parent,false);
            holder.PoNumber=(TextView)convertView.findViewById(R.id.PurchasePoNumber);
            holder.TimeStamp=(TextView)convertView.findViewById(R.id.Purchasetimestamp);
            holder.Flag=(TextView)convertView.findViewById(R.id.PurchaseFlag);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.PoNumber.setText(list.get(position).getPurchaseOrderNo());
        try {
            String input = list.get(position).getLastUpdate();
            String output = input.substring(0, 10);
            holder.TimeStamp.setText(output.toString());
            holder.Flag.setText(list.get(position).getFlag());
        }catch(NullPointerException ex)
        {
            ex.printStackTrace();
        }return convertView;
    }
}
