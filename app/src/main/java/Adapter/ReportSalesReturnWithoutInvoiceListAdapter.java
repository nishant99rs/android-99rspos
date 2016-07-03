package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.ShowSalesReturnWithoutInvoiceListActivity;

import java.util.ArrayList;

import Pojo.SalesReturnReportModel;


public class ReportSalesReturnWithoutInvoiceListAdapter extends BaseAdapter
{
    ShowSalesReturnWithoutInvoiceListActivity activity;
    ArrayList<SalesReturnReportModel> list;
    private final int layoutResourceId;
    LayoutInflater layoutInflater;
    SalesReturnReportModel purchaseProductModel;
    boolean isUserEditingQuantityTextView = true;


    public ReportSalesReturnWithoutInvoiceListAdapter(ShowSalesReturnWithoutInvoiceListActivity activity, ArrayList<SalesReturnReportModel> list, int layoutResourceId, SalesReturnReportModel purchaseProductModel) {
        Log.e("AAAAAAAA&&&&&&&&&", "in fullproductAdapterclass inside constructor");
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
    public SalesReturnReportModel getItem(int position) {

        return list.get(position);
    }

    public static class ViewHolder
    {
        public TextView ProdNm;
        public TextView Batch;
        public TextView Exp;
        public TextView SPrice;
        public TextView Qty;
        public TextView Mrp;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder holder;

        if (convertView==null)
        {
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_full_sale_withoutinvoice_list_row,parent,false);
            holder.ProdNm=(TextView)convertView.findViewById(R.id.rowtvProdNm);
            holder.Batch=(TextView)convertView.findViewById(R.id.rowtvBatch);
            holder.Exp =(TextView)convertView.findViewById(R.id.rowtvExp);
            holder.SPrice=(TextView)convertView.findViewById(R.id.rowtvPrice);
            holder.Qty=(TextView)convertView.findViewById(R.id.rowtvQty);
            holder.Mrp=(TextView)convertView.findViewById(R.id.rowtvMrp);
            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        holder.ProdNm.setText(list.get(position).getProdNm());
        holder.Batch.setText(list.get(position).getBatch());
        holder.Exp.setText(list.get(position).getExp());
        holder.SPrice.setText(list.get(position).getPrice());
        holder.Qty.setText(list.get(position).getQty());
        holder.Mrp.setText(list.get(position).getMrp());
        return convertView;

    }
}
