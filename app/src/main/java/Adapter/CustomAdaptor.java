package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.ActivityTax;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Tax;

/**
 * Created by Shilpa on 2/16/2016.
 */
public class CustomAdaptor extends BaseAdapter {
    // List list = new ArrayList();
    private Context context;

    ActivityTax activity;
    CustomAdaptor customAdaptor;
    Tax tax;

    public CustomAdaptor(ActivityTax activity, ArrayList<Tax> taxArrayList) {
        this.activity = activity;
        this.taxArrayList = taxArrayList;
    }

   private ArrayList<Tax> taxArrayList;

    @Override
    public int getCount() {
        if (taxArrayList.size() <= 0)
            return 1;
        Log.e("**get Count***", taxArrayList.toString());
        return taxArrayList.size();
    }

    @Override
    public Object getItem(int position)

    {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {

        TextView textview_desc;
        TextView textView_rate;
    }

    @Override
    public View getView(int position, View contentview, ViewGroup viewGroup) {
        // View v = View.inflate(context, R.layout.listitem, null);
        //TextView textdesc = (TextView) v.findViewById(R.id.text);
        //TextView textrate = (TextView) v.findViewById(R.id.text1);
        //textdesc.setText(taxArrayList.get(position).getTAX_DESCRIPTION());
        //textrate.setText(taxArrayList.get(position).getTAX_RATE());
        // return null;
        // }
//}

        ViewHolder holder;

        if (contentview == null) {
         LayoutInflater   inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            contentview = inflater.inflate(R.layout.listitem, null);

            holder = new ViewHolder();
            holder.textview_desc = (TextView) contentview.findViewById(R.id.text);
            holder.textView_rate = (TextView) contentview.findViewById(R.id.text1);

            contentview.setTag(holder);

        } else
        {
            holder = (ViewHolder) contentview.getTag();
        }

            holder.textview_desc.setText(taxArrayList.get(position).getTAX_DESCRIPTION());
            holder.textView_rate.setText(taxArrayList.get(position).getTAX_RATE());

            return contentview;
    }

}