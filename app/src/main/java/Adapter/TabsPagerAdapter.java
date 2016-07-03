package Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import Fragments.DiscountFragment;
import Fragments.FastMovingFragment;
import Fragments.PushAddsfragment;

public class TabsPagerAdapter extends FragmentPagerAdapter
{

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case  0:
                return new FastMovingFragment();
//            case 1:
//                return  new  DiscountFragment();
//            case 2:
//                return  new PushAddsfragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 1;
    }
}





