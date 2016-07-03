package Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompany.apps.R;

/**
 * Created by rspl-nishant on 12/3/16.
 */

    public class PushAddsfragment extends android.support.v4.app.Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_pushadds, container, false);

            return rootView;


        }


    }
