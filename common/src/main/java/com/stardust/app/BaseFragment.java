package com.stardust.app;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stardust.util.ViewUtil;

/**
 * Created by Stardust on 2017/1/30.
 */

public abstract class BaseFragment extends androidx.fragment.app.Fragment {

    private View mView;

    @NonNull
    public View getView() {
        return mView;
    }

    public <T extends View> T $(int id) {
        return ViewUtil.$(mView, id);
    }

    public void $(int resId, View.OnClickListener onClickListener) {
        View clickView = findViewById(resId);
        if (clickView != null && onClickListener != null) {
            clickView.setOnClickListener(onClickListener);
        }
    }

    public View findViewById(int id) {
        return mView.findViewById(id);
    }

    public View getActivityContentView() {
        return getActivity().getWindow().getDecorView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutRes(), container, false);
        findView();
        setUpViews();
        return mView;
    }

    protected void setUpViews() {

    }

    protected void findView(){

    }


    @Nullable
    public abstract int getLayoutRes();

    public <T extends BaseFragment> T arg(String ars, boolean value){
        return (T)this;
    }
    public <T extends BaseFragment> T arg(String ars, String value){ return (T)this;}
    public <T extends BaseFragment> T arg(String ars, int value){ return (T)this;}
}
