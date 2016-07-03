package Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mycompany.apps.R;


//import com.mycompany.apps.R;

/**
 * Created by rspl-nishant on 12/3/16.
 */
public class DiscountFragment extends android.support.v4.app.Fragment {
    TextView  fragmarquetv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_discount, container, false);
        fragmarquetv=(TextView) rootView.findViewById(R.id.frgmarque);
        fragmarquetv.setEllipsize(TextUtils.TruncateAt.START);

        fragmarquetv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        fragmarquetv.setMarqueeRepeatLimit(130);
        fragmarquetv.setSelected(true);
        fragmarquetv.setSingleLine(true);


        return rootView;
    }
}