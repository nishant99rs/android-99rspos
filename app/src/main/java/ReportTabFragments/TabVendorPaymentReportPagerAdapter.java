package ReportTabFragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


public class TabVendorPaymentReportPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;


    public TabVendorPaymentReportPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FragmentDirectCashReport();

            case 1:
                return new FragmentDirectChequeReport();

            case 2:
                return new FragmentIndirectCashReport();

            case 3:
                return new FragmentIndirectChequeReport();

        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
