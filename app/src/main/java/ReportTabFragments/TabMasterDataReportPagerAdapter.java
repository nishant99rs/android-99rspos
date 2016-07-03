package ReportTabFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TabMasterDataReportPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;


    public TabMasterDataReportPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
              return new FragmentDistributorReport();

            case 1:
                return  new FragmentVendorReport();

            case 2:
                return new FragmentProductPharmaReport();


            case 3:
                return new FragmentLocalProductPharmaReport();


            case 4:
                return new FragmentProductCPGReport();


            case 5:
                return new FragmentLocalProductCPGReport();




        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}
