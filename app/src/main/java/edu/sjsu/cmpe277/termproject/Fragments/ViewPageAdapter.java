package edu.sjsu.cmpe277.termproject.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by emy on 11/29/15.
 */
public class ViewPageAdapter extends FragmentStatePagerAdapter {

    private CharSequence[] charSequence;
    private int no_of_tabs;

    public ViewPageAdapter(FragmentManager fragmentManager, CharSequence[] charSequence, int no_of_tabs) {
        super(fragmentManager);
        this.charSequence = charSequence;
        this.no_of_tabs = no_of_tabs;
    }
    @Override
    public Fragment getItem(int position) {
        if(position==0){
            Tab1 tab1 = new Tab1();
            return tab1;
        }
        else if(position==1){
            Tab2 tab2 = new Tab2();
            return tab2;
        }
        else{
            Tab3 tab3 = new Tab3();
            return tab3;
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return charSequence[position];
    }

    @Override
    public int getCount() {
        return no_of_tabs;
    }
}
