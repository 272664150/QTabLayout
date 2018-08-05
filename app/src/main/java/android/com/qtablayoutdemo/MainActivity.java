package android.com.qtablayoutdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.view_pager);
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentOne());
        fragmentList.add(new FragmentTwo());
        fragmentList.add(new FragmentThree());
        myPagerAdapter.setFragments(fragmentList);
        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setOffscreenPageLimit(fragmentList.size() - 1);

        mTabLayout = (TabLayout) LayoutInflater.from(this).inflate(R.layout.view_tab_layout, null);
        mTabLayout.setupWithViewPager(mViewPager);

        getSupportActionBar().setCustomView(mTabLayout, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList != null ? mFragmentList.get(position) : null;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page" + (position + 1);
        }

        @Override
        public int getCount() {
            return mFragmentList != null ? mFragmentList.size() : 0;
        }

        public void setFragments(List<Fragment> fragments) {
            mFragmentList = fragments;
        }
    }
}
