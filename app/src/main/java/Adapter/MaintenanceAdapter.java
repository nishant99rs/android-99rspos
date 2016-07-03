package Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mycompany.apps.R;
import com.mycompany.apps.maintainenecereport;
import Pojo.ShowModel;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Rahul on 2/22/2016.
 */
public class MaintenanceAdapter extends BaseAdapter {
    private static LayoutInflater layoutInflater;
    ShowModel showModel;
    maintainenecereport activity;
    List<ShowModel> list =new ArrayList<ShowModel>();

    public MaintenanceAdapter(maintainenecereport activity, ArrayList<ShowModel> arraylist) {
    this.activity=activity;
        this.list=arraylist;

    }


    public long getItemId(int position)
    {
        Log.e("**********", "" + position);
        return position;
}

    public int getCount() {
        if (list.size()<=0)
            return 1;
        Log.e("**get Count***",list.toString());
        return list.size();
    }


    public Object getItem(int position) {
        return position;
    }

    public static class ViewHolder{
        /*
        layout created display _report _row which text view are used
        */
        TextView TVMaintenanceTicket_Id;
        TextView TVMaintenanceSupportticketstatus;
        TextView TVMaintenanceSubjectDesc;
        TextView TVMaintenanceTeammember;
        TextView TVTimeStamp;

    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null)
        {

            layoutInflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /***** Inflate tabitem.xml file for each row ( Defined below ) *******/
            convertView =layoutInflater.inflate(R.layout.display_showmaintenance_row,null);
            holder = new ViewHolder();
            holder.TVMaintenanceTicket_Id=(TextView)convertView.findViewById(R.id.rowMaintenanceTicket_Id);
            holder.TVMaintenanceSupportticketstatus=(TextView)convertView.findViewById(R.id.rowmaintenanceSupport_Ticket_Status);
            holder.TVMaintenanceSubjectDesc=(TextView)convertView.findViewById(R.id.rowMaintenanceSubject_desc);
            holder.TVMaintenanceTeammember=(TextView)convertView.findViewById(R.id.rowMaintenaceTeamMember);
            holder.TVTimeStamp=(TextView)convertView.findViewById(R.id.rowMaintenaceDate);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            holder.TVMaintenanceTicket_Id.setText(list.get(position).getTICKET_ID());
            holder.TVMaintenanceSubjectDesc.setText(list.get(position).getSUBJECT_DESC());
            holder.TVMaintenanceSupportticketstatus.setText(list.get(position).getSUPPORT_TICKET_STATUS());
            holder.TVMaintenanceTeammember.setText(list.get(position).getTEAM_MEMBER());
            String input = list.get(position).getTimeStamp();
            String output = input.substring(0, 10);
            holder.TVTimeStamp.setText(output.toString());

        }catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }catch (NullPointerException ex){
            ex.printStackTrace();
        }
        return convertView;
    }
}
