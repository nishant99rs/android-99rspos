package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mycompany.apps.Activity_Bill_Level;
import com.mycompany.apps.DBhelper;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.BillLevelModel;


public class BillLevelAdapter extends ArrayAdapter<BillLevelModel> {
    private final int layoutResourceId;
    private Context mcontext;
    Activity_Bill_Level activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;
    DBhelper mydb;
    ArrayList<BillLevelModel> list;

    public BillLevelAdapter(Activity_Bill_Level activity, int layoutResourceId, ArrayList<BillLevelModel> msalesreturnlist) {
        super(activity, layoutResourceId,  msalesreturnlist);
        this.activity = activity;
        this.salesreturnlist=  msalesreturnlist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public void setSalesReturnList(ArrayList<BillLevelModel> salesreturnlist) {
        this.salesreturnlist = salesreturnlist;
    }

    private ArrayList<Pojo.BillLevelModel> salesreturnlist;
    public int getCount() {
        if (salesreturnlist.size() < 0)
            return 1;
        return salesreturnlist.size();
    }


    public BillLevelModel getItem(int position) {
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

    public void setList(ArrayList<BillLevelModel> list) {
        this.list=list;
    }

    public static class ViewHolder {

        public TextView lastupdate;

        public EditText mBillNAme;
        public EditText mBilltype;


        public ImageButton Delete;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.display_customer_rejection, parent, false);

            holder.mBillNAme = (EditText) convertView.findViewById(R.id.rejectid);
            holder.mBilltype = (EditText) convertView.findViewById(R.id.reasonforreject);

            holder.lastupdate = (TextView) convertView.findViewById(R.id.lastupdate);
            holder.Delete = (ImageButton) convertView .findViewById(R.id.deleteButton);









            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolder) convertView.getTag();

        }
//
        holder.mBillNAme.setText(salesreturnlist.get(position).getmBillName());
        // holder.saledate.setText(salesreturnlist.get(position).getSaleDate());
        holder.mBilltype.setText(salesreturnlist.get(position).getmBillType());






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
    public void addProductToList( BillLevelModel product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");

         salesreturnlist.add(product);
    }

    public ArrayList<BillLevelModel> getList() {
        return salesreturnlist;
    }




    public ArrayList<BillLevelModel>getlist(){

        return list;
    }
}
