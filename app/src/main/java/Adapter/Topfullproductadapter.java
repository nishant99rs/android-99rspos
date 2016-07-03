package Adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mycompany.apps.Activity_Top_Product;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Topfullproductmodel;

/**
 * Created by Rahul on 5/20/2016.
 */
public class Topfullproductadapter  extends BaseAdapter {
    TextWatcher quantityTextChangeWatcher;
    Activity_Top_Product activity;
    public ArrayList<Topfullproductmodel> list;
    private final int layoutResourceId;
    LayoutInflater layoutInflater;
    Topfullproductmodel ProductModel;
    public Topfullproductadapter(Activity_Top_Product activity, ArrayList<Topfullproductmodel> list, int layoutResourceId,  Topfullproductmodel Model) {
        this.activity = activity;
        this.list = list;
        this.layoutResourceId = layoutResourceId;
        this.ProductModel = Model;
    }
    public long getItemId(int position)
    {
        Log.e("**********", "" + position);
        return position;
    }


    public int getCount() {
        if (list.size() > 15)


            return 15;
        Log.e("**get Count***", list.toString());
        return list.size();
    }
    public Topfullproductmodel getItem(int position) {
        return list.get(position);
    }


    public static class ViewHolder{
        public TextView PurchaseName;
        public EditText shortname;
       /* public   TextView Purchasemrp;
        public   TextView Sellingprice;
        public TextView pprice;
        public TextView measure;
      */  public ImageButton DeleteButton;
        }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null)
        {
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.displaytopfullproduct,parent,false);
            holder.PurchaseName=(TextView)convertView.findViewById(R.id.PurchaseproductName);
            holder.shortname=(EditText)convertView.findViewById(R.id.purchaseshortname);

          /*  holder.Purchasemrp=(TextView)convertView.findViewById(R.id.productmrp);
            holder.Sellingprice=(TextView)convertView.findViewById(R.id.productsprice);
            holder.pprice=(TextView)convertView.findViewById(R.id.topproductprice);
            holder.measure=(TextView)convertView.findViewById(R.id.productsellingofunit);
          */  holder.DeleteButton=(ImageButton)convertView.findViewById(R.id.deleteButton);


            convertView.setTag(holder);
        }



        else
        {
            holder=(ViewHolder)convertView.getTag();
        }


        if ((holder.shortname.getTag() != null) && (holder.shortname.getTag() instanceof TextWatcher)) {
            holder.shortname.removeTextChangedListener((TextWatcher) holder.shortname.getTag());
        }
        holder.shortname.setText(list.get(position).getShortname());

//


        holder.PurchaseName.setText(list.get(position).getProductname());
      /*  holder.Purchasemrp.setText(list.get(position).getMrp());
        holder.Sellingprice.setText(list.get(position).getSprice());
        holder.pprice.setText(list.get(position).getPrice());
        holder.measure.setText(list.get(position).getSou());
*/
        quantityTextChangeWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                list.get(position).setShortname(holder.shortname.getText().toString());


            }
        };

        holder.shortname.addTextChangedListener(quantityTextChangeWatcher);
        holder.shortname.setTag(quantityTextChangeWatcher);


        holder.DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
               // activity.setSummaryRow();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
    public void addProductToList( Topfullproductmodel product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
        Topfullproductmodel productAlreadyInList = findProductInList(product);
        if(productAlreadyInList == null) {
            list.add(product);
        }
    }


    public ArrayList<Topfullproductmodel> getList() {

        return list;
    }



    private Topfullproductmodel findProductInList(Topfullproductmodel product) {
        Topfullproductmodel returnProductVal = null;
        for( Topfullproductmodel productInList : list) {
            if( productInList.getProductname().trim().equals(product.getProductname().trim()) ) {
                returnProductVal = productInList;
            }
        }
        return returnProductVal;
    }


    public void clearAllRows() {
        list.clear();
        notifyDataSetChanged();
    }
    /*public float getGrandTotal()
    {
        float finalamount=0.0f;
        for (int listIndex = 0; listIndex < list.size(); listIndex++) {
            try {
                Log.e("Get Total inside fn", "Total Price is:" + list.get(listIndex).getTotal());
                finalamount += (list.get(listIndex).getTotal());

                Log.e("GetGrandTotal", "Total Price is:" + finalamount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.e("GetGrandTotal o/p", "Total Price is:" + finalamount);
        return finalamount;
    }*/



}
