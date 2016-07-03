package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.ProductCpgReportActivity;
import com.mycompany.apps.ProductPharmaReportActivity;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.ReportProductCpgModel;
import Pojo.ReportProductPharmaModel;


public class ReportProductCpgListAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    ArrayList<ReportProductCpgModel>list=new ArrayList<ReportProductCpgModel>();
    ProductCpgReportActivity activity;

    public ReportProductCpgListAdapter(ArrayList<ReportProductCpgModel> list, ProductCpgReportActivity activity) {
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
        return null;
    }

    public void addProductToList(ReportProductCpgModel product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
        list.add(product);

    }

    public void setList(ArrayList<ReportProductCpgModel> list) {
        this.list = list;
    }

    public static class ViewHolder{
        /*
        layout created display _report _row which text view are used
        */
        TextView TVPRODCT_NM;
        TextView TVBAR_CODE;
        TextView TV_MRP;
        TextView TV_SPRICE;
        TextView TV_PPRICE;
        TextView TV_PRICE;
        TextView TV_ACTVE;
        TextView TV_MARGIN;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_report_product_cpg_row,null);
            holder = new ViewHolder();
            holder.TVPRODCT_NM=(TextView)convertView.findViewById(R.id.rowtvProdct_Nm);
            holder.TVBAR_CODE=(TextView)convertView.findViewById(R.id.rowtvBar_Code);
            holder.TV_MRP=(TextView)convertView.findViewById(R.id.rowtv_Mrp);
            holder.TV_SPRICE=(TextView)convertView.findViewById(R.id.rowtvS_Price);
            holder.TV_PPRICE=(TextView)convertView.findViewById(R.id.rowtvP_Price);
            holder.TV_ACTVE=(TextView)convertView.findViewById(R.id.rowtv_Actve);
            holder.TV_MARGIN=(TextView)convertView.findViewById(R.id.rowtvP_Margin);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.TVPRODCT_NM.setText(list.get(position).getProd_Nm());
        holder.TVBAR_CODE.setText(list.get(position).getBarCode());
        holder.TV_MRP.setText(list.get(position).getMRP());
        holder.TV_SPRICE.setText(list.get(position).getS_Price());
        holder.TV_PPRICE.setText(list.get(position).getP_Price());
        holder.TV_ACTVE.setText(list.get(position).getActive());
        holder.TV_MARGIN.setText(list.get(position).getProfitMargin());
        return convertView;
    }
}
