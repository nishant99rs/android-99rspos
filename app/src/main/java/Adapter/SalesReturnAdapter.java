package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.mycompany.apps.ActivitySalesreturn;
import com.mycompany.apps.DBhelper;
import com.mycompany.apps.R;
//import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.PurchaseProductModel;
import Pojo.SalesReturn;
import Pojo.Salesreturndetail;

/**
 * Created by rspl-nishant on 1/4/16.
 */
public class SalesReturnAdapter extends ArrayAdapter<Salesreturndetail> {
    private final int layoutResourceId;
    private Context mcontext;
    ActivitySalesreturn activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;
    DBhelper mydb;

    public SalesReturnAdapter(ActivitySalesreturn activity, int layoutResourceId, ArrayList<Salesreturndetail> msalesreturnlist) {
        super(activity, layoutResourceId,  msalesreturnlist);
        this.activity = activity;
        this.salesreturnlist=  msalesreturnlist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public void setSalesReturnList(ArrayList<Salesreturndetail> salesreturnlist) {
        this.salesreturnlist = salesreturnlist;
    }

    private ArrayList<Pojo.Salesreturndetail> salesreturnlist;
    public int getCount() {
        if (salesreturnlist.size() < 0)
            return 1;
        return salesreturnlist.size();
    }


    public Salesreturndetail getItem(int position) {
        return salesreturnlist.get(position);
    }


    public long getItemId(int position) {

        //.getCustomermobileno();
        return position;
    }

    public void clearAllRows() {
        salesreturnlist.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder {

        public TextView Transid;
       // public TextView Billno;
        public TextView Expdate;
        public TextView BatchNo;
       // public TextView mrp;
        public TextView quantity;
        public TextView uom;
        public TextView productname;
        public TextView sellingprice;
        public TextView total;
      //  public TextView saledate;
       // public Spinner  reason;
        public ImageButton Delete;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.display_sales_return, parent, false);
           // holder.Transid = (TextView) convertView.findViewById(R.id.transid);
           // holder.Billno = (TextView) convertView.findViewById(R.id.billno);
            holder.Expdate = (TextView) convertView.findViewById(R.id.expdate);
            holder.BatchNo = (TextView) convertView.findViewById(R.id.batchno);
          // holder.reason = (EditText) convertView.findViewById(R.id.reason);
            holder.quantity= (TextView) convertView.findViewById(R.id.quty);
           // holder. reason = (Spinner) convertView.findViewById(R.id.reasonreturn);
            holder.uom = (TextView) convertView.findViewById(R.id.uom);
         //   holder.saledate = (TextView)convertView.findViewById(R.id.saledate);
            holder.productname = (TextView) convertView.findViewById(R.id.prodname);
            holder.sellingprice = (TextView) convertView.findViewById(R.id.selling);
            holder.total= (TextView) convertView.findViewById(R.id.totalpt);
            holder.Delete = (ImageButton) convertView .findViewById(R.id.deleteButton);

          //  holder.mrp = (TextView) convertView.findViewById(R.id.mrp);







            convertView.setTag(holder);
        }
        //holder.name.setText(mcustomerlist.get(position).getCustomername());
        else {
            holder = (ViewHolder) convertView.getTag();

        }
//            holder.Transid.setText(salesreturnlist.get(position).getSaleTransid());
           // holder.Billno.setText(salesreturnlist.get(position).getSaleBillno());
            holder.productname.setText(salesreturnlist.get(position).getSaleproductname());
            holder.Expdate.setText(salesreturnlist.get(position).getSaleexpiry());
       // holder.saledate.setText(salesreturnlist.get(position).getSaleDate());
            holder.BatchNo.setText(salesreturnlist.get(position).getSalebatchno());

           // holder.mrp.setText(salesreturnlist.get(position).getSalemrp());
            // holder.reason.setText(salesreturnlist.get(position).);

            holder.quantity.setText(String.format("%.2f", salesreturnlist.get(position).getSaleqty()));
            holder.uom.setText(salesreturnlist.get(position).getSaleuom());
            holder.sellingprice.setText(String.format("%.2f", salesreturnlist.get(position).getSalesellingprice()));
            holder.total.setText(String.format("%.2f",salesreturnlist.get(position).getSaletotal()));

        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salesreturnlist.remove(position);
                activity.grandtotal();
                notifyDataSetChanged();
            }
        });

//       holder.name.setText(customerlist.get(position).getCustomername());
//        holder.mobile_no.setText(customerlist.get(position).getCustomermobileno());

        return convertView;


    }
    public void addProductToList( Salesreturndetail product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
       // salesreturnlist.add(product);
       /* PurchaseProductModel productAlreadyInList = findProductInList(product);
        if(productAlreadyInList == null) {

        } else {
            productAlreadyInList.setProductQuantity( productAlreadyInList.getProductQuantity() + product.getProductQuantity());
        }
*/          salesreturnlist.add(product);
    }

    public ArrayList<Salesreturndetail> getList() {
        return salesreturnlist;
    }

   /* private PurchaseProductModel findProductInList(PurchaseProductModel product) {
        PurchaseProductModel returnProductVal = null;

        for( Salesreturndetail productInList : salesreturnlist) {
            if( productInList.getProductId().trim().equals(product.getProductId().trim()) ) {
                returnProductVal = productInList;
            }
        }


        return returnProductVal;
    }*/

    public float getGrandTotal() {
        // ViewHolder holder=new ViewHolder();
        float finalamount = 0.0f;
        for (int listIndex = 0; listIndex < salesreturnlist.size(); listIndex++) {
            try {
                finalamount += (salesreturnlist.get(listIndex).getSaletotal());
            } catch (Exception e) {
                //ignore exeption for parse
            }
            Log.e("&&&&&^^^^^^^^", "$$$$$$$$" + finalamount);

        }
        Log.e("&&&&55555555&&&", "Total Price is:" + finalamount);
        return finalamount;
    }
}
