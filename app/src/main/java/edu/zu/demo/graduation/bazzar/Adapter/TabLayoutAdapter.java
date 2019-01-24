package edu.zu.demo.graduation.bazzar.Adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class TabLayoutAdapter extends FragmentPagerAdapter {
   ArrayList<Fragment> fragments=new ArrayList<>();
   ArrayList<String> titleTab=new ArrayList<>();

    public TabLayoutAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return  fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleTab.get(position);
    }

    public void  addFragment(Fragment fragment,String title){

        this.fragments.add(fragment);
        this.titleTab.add(title);
    }
}
