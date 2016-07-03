package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.ReportSalesReturnWithoutInvoiceActivity;

import java.util.ArrayList;


public class TransactionIdWithoutInvoiceSearchAdapter extends ArrayAdapter<String> {

    ReportSalesReturnWithoutInvoiceActivity activity;
    private final int layoutResourceId;
    ArrayList<String> idList;
    LayoutInflater layoutInflater;

    public TransactionIdWithoutInvoiceSearchAdapter(ReportSalesReturnWithoutInvoiceActivity activity, int textViewResourceId, ArrayList<String> objects) {
        super(activity, textViewResourceId, objects);
        this.activity=activity;
        this.layoutResourceId=textViewResourceId;
        this.idList=objects;
    }

    public void setList(ArrayList<String> list) {
        this.idList = list;
    }

    public int getCount() {
        if(idList.size()<0)
            return 1;
        return idList.size();
    }
    public String getItem(int position) {
        return idList.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView transactionId;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_search_transid_withoutinvoice_row,parent,false);
            holder.transactionId=(TextView)convertView.findViewById(R.id.TranscId);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.transactionId.setText(idList.get(position).toString());
        return convertView;
    }
}
