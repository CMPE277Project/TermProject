package edu.sjsu.cmpe277.termproject.tabs;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import edu.sjsu.cmpe277.termproject.Fragments.EventTabFragment;
import edu.sjsu.cmpe277.termproject.Fragments.RecordTabFragment;

/**
 * Created by emy on 11/28/15.
 */
public class TabsPageAdapter extends FragmentPagerAdapter {
    protected Context context;

    public TabsPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                return new EventTabFragment();
            case 1:
                return new RecordTabFragment();

        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
