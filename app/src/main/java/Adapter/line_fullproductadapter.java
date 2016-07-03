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

import com.mycompany.apps.Activity_lineitem_discount;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.line_item_Product_Model;

/**
 * Created by rspl-gourav on 23/5/16.
 */
public class line_fullproductadapter  extends BaseAdapter {
    TextWatcher quantityTextChangeWatcher;
    Activity_lineitem_discount activity;
    public ArrayList<line_item_Product_Model> list;
    private final int layoutResourceId;
    LayoutInflater layoutInflater;
    line_item_Product_Model ProductModel;

    public line_fullproductadapter(Activity_lineitem_discount activity, ArrayList<line_item_Product_Model> list, int layoutResourceId,  line_item_Product_Model Model) {
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
        if (list.size() > 3)

            return 3;
        Log.e("**get Count***", list.toString());

        return list.size();

    }
    public line_item_Product_Model getItem(int position) {
        return list.get(position);
    }


    public static class ViewHolder{
        public TextView PurchaseName;
        public EditText discount;
         public ImageButton DeleteButton;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView==null)
        {
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_line_item_fullproduct,parent,false);
            holder.PurchaseName=(TextView)convertView.findViewById(R.id.PurchaseproductName);
            holder.discount=(EditText)convertView.findViewById(R.id.purchasediscountamount);

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


        if ((holder.discount.getTag() != null) && (holder.discount.getTag() instanceof TextWatcher)) {
            holder.discount.removeTextChangedListener((TextWatcher) holder.discount.getTag());
        }
        holder.discount.setText(list.get(position).getDiscountitem());
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
                list.get(position).setDiscountitem(holder.discount.getText().toString());


            }
        };

        holder.discount.addTextChangedListener(quantityTextChangeWatcher);
        holder.discount.setTag(quantityTextChangeWatcher);


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
    public void addProductToList( line_item_Product_Model product ) {
        Log.e("&&&&&&&&", "Adding product " + product.toString() + " to product list");
        line_item_Product_Model productAlreadyInList = findProductInList(product);
        if(productAlreadyInList == null) {
            list.add(product);
        }
    }


    public ArrayList<line_item_Product_Model> getList() {

        return list;
    }



    private line_item_Product_Model findProductInList(line_item_Product_Model product) {
        line_item_Product_Model returnProductVal = null;
        for( line_item_Product_Model productInList : list) {
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
