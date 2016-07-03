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

import com.mycompany.apps.CustomerRejection;
import com.mycompany.apps.DBhelper;

import Pojo.CustomerRejectModel;
import Pojo.Inventoryproductmodel;


import com.mycompany.apps.R;

import java.util.ArrayList;



public class CustomerRejectAdapter extends ArrayAdapter<CustomerRejectModel> {
    private final int layoutResourceId;
    private Context mcontext;
    CustomerRejection activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;
    DBhelper mydb;
    ArrayList<CustomerRejectModel> list;

    public CustomerRejectAdapter(CustomerRejection activity, int layoutResourceId, ArrayList<CustomerRejectModel> msalesreturnlist) {
        super(activity, layoutResourceId,  msalesreturnlist);
        this.activity = activity;
        this.salesreturnlist=  msalesreturnlist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public void setSalesReturnList(ArrayList<CustomerRejectModel> salesreturnlist) {
        this.salesreturnlist = salesreturnlist;
    }

    private ArrayList<Pojo.CustomerRejectModel> salesreturnlist;
    public int getCount() {
        if (salesreturnlist.size() < 0)
            return 1;
        return salesreturnlist.size();
    }


    public CustomerRejectModel getItem(int position) {
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

    public void setList(ArrayList<CustomerRejectModel> list) {
        this.list=list;
    }

    public static class ViewHolder {

        public TextView lastupdate;

        public EditText mId;
        public EditText mReasone;


        public ImageButton Delete;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.display_customer_rejection, parent, false);

            holder.mId = (EditText) convertView.findViewById(R.id.rejectid);
            holder.mReasone = (EditText) convertView.findViewById(R.id.reasonforreject);

            holder.lastupdate = (TextView) convertView.findViewById(R.id.lastupdate);
            holder.Delete = (ImageButton) convertView .findViewById(R.id.deleteButton);









            convertView.setTag(holder);
        }
        //holder.name.setText(mcustomerlist.get(position).getCustomername());
        else {
            holder = (ViewHolder) convertView.getTag();

        }
//
        holder.mId.setText(salesreturnlist.get(position).getmVId());
        // holder.saledate.setText(salesreturnlist.get(position).getSaleDate());
        holder.mReasone.setText(salesreturnlist.get(position).getmVReason());
        holder.lastupdate.setText(salesreturnlist.get(position).getLastupdate());

        // holder.mrp.setText(salesreturnlist.get(position).getSalemrp());
        // holder.reason.setText(salesreturnlist.get(position).);




        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salesreturnlist.remove(position);
                // activity.grandtotal();
                notifyDataSetChanged();
            }
        });

//       holder.name.setText(customerlist.get(position).getCustomername());
//        holder.mobile_no.setText(customerlist.get(position).getCustomermobileno());

        return convertView;


    }
    public void addProductToList( CustomerRejectModel product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
        //reasonarraylist.add(product);
       /* PurchaseProductModel productAlreadyInList = findProductInList(product);
        if(productAlreadyInList == null) {

        } else {
            productAlreadyInList.setProductQuantity( productAlreadyInList.getProductQuantity() + product.getProductQuantity());
        }
*/          salesreturnlist.add(product);
    }

    public ArrayList<CustomerRejectModel> getList() {
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

//    public float getGrandTotal() {
//        // ViewHolder holder=new ViewHolder();
//        float finalamount = 0.0f;
//        for (int listIndex = 0; listIndex < salesreturnlist.size(); listIndex++) {
//            try {
//                finalamount += (salesreturnlist.get(listIndex).getSaletotal());
//            } catch (Exception e) {
//                //ignore exeption for parse
//            }
//            Log.e("&&&&&^^^^^^^^", "$$$$$$$$" + finalamount);
//
//        }
//        Log.e("&&&&55555555&&&", "Total Price is:" + finalamount);
//        return finalamount;
//    }


    public ArrayList<CustomerRejectModel>getlist(){

        return list;
    }
}
