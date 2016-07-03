
        package Adapter;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

        import com.mycompany.apps.R;
        import com.mycompany.apps.activity_inventory;

        import java.util.ArrayList;

        import Pojo.Inventoryproductmodel;

/**
 * Created by Abhigyan on 26-03-2016.
 */
public class Inventoryproductadapter  extends ArrayAdapter<Inventoryproductmodel> {
    activity_inventory activity;
    private final int layoutResourceId;
    ArrayList<Inventoryproductmodel> list;
    LayoutInflater layoutInflater;
    public Inventoryproductadapter(activity_inventory activity, int textViewResourceId, ArrayList<Inventoryproductmodel> objects) {
        super(activity, textViewResourceId, objects);
        this.layoutResourceId=textViewResourceId;
        this.activity=activity;
        this.list=objects;
    }


    public Inventoryproductadapter(activity_inventory activity, int textViewResourceId, Inventoryproductmodel InventoryProductModel) {
        super(activity, textViewResourceId);
        this.inventoryproductmodel =  InventoryProductModel;
        this.layoutResourceId=textViewResourceId;

    }

    Inventoryproductmodel inventoryproductmodel;

    public void setList(ArrayList<Inventoryproductmodel> list) {
        this.list = list;
    }

    public int getCount() {
        if(list.size()<0)
            return 1;
        return list.size();
    }
    public Inventoryproductmodel getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder
    {
        TextView PurchaseId;
        TextView PurchaseName;

        /*  TextView purchaseBatchno;
          TextView purchaseExpdate;
        */  TextView purchaseMrp;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {
            holder= new ViewHolder();
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.display_inventory_product,parent,false);
            holder.PurchaseId=(TextView)convertView.findViewById(R.id.PurchaseproductId);
            holder.PurchaseName=(TextView)convertView.findViewById(R.id.PurchaseproductName);
          /*  holder.purchaseBatchno=(TextView)convertView.findViewById(R.id.Purchaseproductbatch);
            holder.purchaseExpdate=(TextView)convertView.findViewById(R.id.Purchaseproductexpdate);
*/
            holder.purchaseMrp=(TextView)convertView.findViewById(R.id.PurchaseproductMRP);

            // holder.Purchasemrp=(TextView)convertView.findViewById(R.id.purchaseMRP);


            holder.PurchaseId.setText(list.get(position).getProductId());
            holder.PurchaseName.setText(list.get(position).getProductName());
          /*  holder.purchaseBatchno.setText(list.get(position).getBatchno());

            holder.purchaseExpdate.setText(list.get(position).getExpdate());
*/
            //  holder.purchaseMrp.setText(list.get(position).getMrp());
            holder.purchaseMrp.setText(String.format("%.2f", list.get(position).getMrp()));


            convertView.setTag(holder);
        }
        else
        {
            holder=(ViewHolder)convertView.getTag();
        }

        return convertView;
    }
}


