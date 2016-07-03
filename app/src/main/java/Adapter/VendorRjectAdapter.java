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

import com.mycompany.apps.DBhelper;

import Pojo.Inventoryproductmodel;
import Pojo.VendorRejectModel;
import com.mycompany.apps.VendorRejection;

import com.mycompany.apps.R;

//import com.mycompany.apps.R;

import java.util.ArrayList;



public class VendorRjectAdapter extends ArrayAdapter<VendorRejectModel> {
    private final int layoutResourceId;
    private Context mcontext;
    VendorRejection activity;
    private LayoutInflater mInflater;

    DBhelper mydb;


    public VendorRjectAdapter(VendorRejection activity, int layoutResourceId, ArrayList<VendorRejectModel> msalesreturnlist) {
        super(activity, layoutResourceId,  msalesreturnlist);
        this.activity = activity;
        this.salesreturnlist=  msalesreturnlist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public void setSalesReturnList(ArrayList<VendorRejectModel> salesreturnlist) {
        this.salesreturnlist = salesreturnlist;
    }

    private ArrayList<Pojo.VendorRejectModel> salesreturnlist;
    public int getCount() {
        if (salesreturnlist.size() < 0)
            return 1;
        return salesreturnlist.size();
    }


    public VendorRejectModel getItem(int position) {
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

    public void setList(ArrayList<VendorRejectModel> list) {
        this.salesreturnlist=list;
    }

    public static class ViewHolder {

        public TextView Transid;

        public EditText mId;
        public EditText mReasone;


        public TextView total;

        public ImageButton Delete;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.display_vendor_rejection, parent, false);

            holder.mId = (EditText) convertView.findViewById(R.id.rejectid);
            holder.mReasone = (EditText) convertView.findViewById(R.id.reasonforreject);


            holder.Delete = (ImageButton) convertView .findViewById(R.id.deleteButton);









            convertView.setTag(holder);
        }
        //holder.name.setText(mcustomerlist.get(position).getCustomername());
        else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.mId.setText(salesreturnlist.get(position).getmVId());

        holder.mReasone.setText(salesreturnlist.get(position).getmVReason());





        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salesreturnlist.remove(position);
               // activity.grandtotal();
                notifyDataSetChanged();
            }
        });


        return convertView;


    }
    public void addProductToList( VendorRejectModel product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
        //reasonarraylist.add(product);
       /* PurchaseProductModel productAlreadyInList = findProductInList(product);
        if(productAlreadyInList == null) {

        } else {
            productAlreadyInList.setProductQuantity( productAlreadyInList.getProductQuantity() + product.getProductQuantity());
        }
*/          salesreturnlist.add(product);
    }

    public ArrayList<VendorRejectModel> getList() {
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
               // finalamount += (salesreturnlist.get(listIndex).getSaletotal());
            } catch (Exception e) {
                //ignore exeption for parse
            }
            Log.e("&&&&&^^^^^^^^", "$$$$$$$$" + finalamount);

        }
        Log.e("&&&&55555555&&&", "Total Price is:" + finalamount);
        return finalamount;
    }



}
