package ReportTabFragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.mycompany.apps.CustomAuto;
import com.mycompany.apps.DBhelper;
import com.mycompany.apps.R;

import java.util.ArrayList;

import Pojo.IndirectVendorPaymentModel;


public class FragmentIndirectChequeReport extends android.support.v4.app.Fragment{

    ListView listview;
    AutoCompleteTextView idTextView;
    private TextWatcher idTextWatcher;
    FragmentIndirectChequeListAdapter paychequeListAdapter;
    ArrayList<IndirectVendorPaymentModel> searchIdList;
    ArrayList<IndirectVendorPaymentModel> arrayPayChequeList;
    ActionBar actionBar;
    FragmentIndirectChequeSearchAdapter searchIdAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_report_indirect_pay_by_cheque, container, false);
        final String dbName = "/data/data/" + getClass().getPackage().getName() + "/sync.bdb.db";
        final DBhelper helper = new DBhelper(getContext());
        helper.getReadableDatabase();

       /* actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff9033")));
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setIcon(R.drawable.w);*/

        listview = (ListView) view.findViewById(R.id.lv_indirectChequeReport);
        Log.e("***********Lt1*******", listview.toString());
        idTextView = (CustomAuto) view.findViewById(R.id.IndirectChequeIdTextView);
        idTextView.setThreshold(1);

        //******************************** grn id selected from here********************************************************************************************/
        idTextView.setThreshold(1);
        idTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (idTextView.getText().toString().matches("")) {
                    Toast.makeText(getContext(), "Please select the Grn Id", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("Debuging", "After text changed called ");
                if (idTextView.isPerformingCompletion()) {
                    Log.d("Debuging", "Performing completion ");
                    return;
                }
                String userTypedString = idTextView.getText().toString().trim();
                if (userTypedString.equals("")) {
                    return;
                }
                searchIdList = helper.getGrnIndirectId(userTypedString);
                if (searchIdList != null) {
                    if (searchIdAdapter == null) {
                        searchIdAdapter = new FragmentIndirectChequeSearchAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, searchIdList);
                        searchIdAdapter.setList(searchIdList);
                        idTextView.setAdapter(searchIdAdapter);
                        idTextView.setThreshold(1);
                    } else {
                        searchIdAdapter.setList(searchIdList);
                        searchIdAdapter.notifyDataSetChanged();
                    }
                }
            }
        };
        idTextView.addTextChangedListener(idTextWatcher);
        idTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Value = parent.getItemAtPosition(position).toString();
                arrayPayChequeList = helper.getIndirectPayByChequeReport(Value);
                if (paychequeListAdapter == null) {
                    Log.e("&&&&&&&&", "Product Adapter was null and HENCE Creating.....");
                    paychequeListAdapter = new FragmentIndirectChequeListAdapter(new ArrayList<IndirectVendorPaymentModel>(), getContext());
                    paychequeListAdapter.setList(arrayPayChequeList);
                    listview.setAdapter(paychequeListAdapter);
                    idTextView.setText("");
                } else {
                    paychequeListAdapter.setList(arrayPayChequeList);
                    paychequeListAdapter.notifyDataSetChanged();
                    idTextView.setText("");
                }
            }
        });


        return view;

    }

}
