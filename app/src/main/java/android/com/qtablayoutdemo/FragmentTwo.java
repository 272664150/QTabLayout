package android.com.qtablayoutdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentTwo extends Fragment {

    private boolean isPrepared;
    private boolean isVisible;
    private boolean isLoaded;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isVisible = isVisibleToUser;
            initData();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isPrepared = true;
        initData();
        return inflater.inflate(R.layout.fragment_two, container, false);
    }

    private void initData() {
        if (!isPrepared || !isVisible || isLoaded) {
            return;
        }
        isLoaded = true;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTabLayout = view.findViewById(R.id.inner_tab_layout);
        mViewPager = view.findViewById(R.id.inner_view_pager);
        mViewPager.setAdapter(new MyPagerAdapter());
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        isVisible = false;
        isLoaded = false;
    }

    private class MyPagerAdapter extends PagerAdapter {
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TextView tv = new TextView(getActivity());
            tv.setGravity(Gravity.CENTER);
            tv.setText("View: " + (position + 1));
            container.addView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return tv;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 6;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab" + (position + 1);
        }
    }
}
