package com.example.pavanbora.movieshows;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    List<String> titlesList=new ArrayList <>(  );
    List<Fragment> fragmentList=new ArrayList <>(  );

    public MyPagerAdapter(FragmentManager fm) {
        super( fm );
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get( position );
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titlesList.get( position );
    }

    public void addFragment(Fragment fragment, String title){
        titlesList.add( title );
        fragmentList.add( fragment );

    }
}
