package android.com.qtablayoutdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentThree extends Fragment {

    private boolean isPrepared;
    private boolean isVisible;
    private boolean isLoaded;

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
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    private void initData() {
        if (!isPrepared || !isVisible || isLoaded) {
            return;
        }
        isLoaded = true;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isPrepared = false;
        isVisible = false;
        isLoaded = false;
    }
}
