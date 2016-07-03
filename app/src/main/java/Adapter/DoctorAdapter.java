package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mycompany.apps.ActivityCustomer;
import com.mycompany.apps.Activity_Doctor;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.Customer;
import Pojo.DoctorPojo;

/**
 * Created by rspl-nishant on 28/5/16.
 */



public class DoctorAdapter extends ArrayAdapter<DoctorPojo> {
    private final int layoutResourceId;
    private Context mcontext;
    Activity_Doctor activity;
    private LayoutInflater mInflater;
    TextView tv;
    TextView v;

    public DoctorAdapter(Activity_Doctor activity, int layoutResourceId, ArrayList<DoctorPojo> mdoctorlist) {
        super(activity, layoutResourceId, mdoctorlist);
        this.activity = activity;
        this.doctorlist= mdoctorlist;
        this.layoutResourceId = layoutResourceId;
//        mInflater = (LayoutInflater) activity
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    public void setDoctorList(ArrayList<DoctorPojo> doctorList) {
        this.doctorlist = doctorList;
    }

    private ArrayList<DoctorPojo> doctorlist;
    public int getCount() {
        if (doctorlist.size() < 0)
            return 1;
        return doctorlist.size();
    }


    public DoctorPojo getItem(int position) {
        return doctorlist.get(position);
    }


    public long getItemId(int position) {

        //.getCustomermobileno();
        return position;
    }

    public static class ViewHolder {

        public TextView name;
        public TextView mobile_no;
        public TextView email;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {

            holder = new ViewHolder();


            LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.doctoritems, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.doctorlist_name);
         //   holder.mobile_no = (TextView) convertView.findViewById(R.id.Customerlist_mobile);
            convertView.setTag(holder);
        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(doctorlist.get(position).getDoctorName());
//        holder.mobile_no.setText(doctorlist.get(position).getD());

        return convertView;


    }
}