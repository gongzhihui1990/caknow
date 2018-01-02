package com.caknow.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.android.ui.activity.BaseActivity;

import net.gtr.framework.app.fragment.RxBaseFragment;


/**
 * @author heisenberg
 * @date 2018/1/2
 * gongzhihui1990@gmail.com
 */

public abstract class BaseFragment extends RxBaseFragment {

    protected View mContentView;

    @Override
    protected BaseActivity getBaseActivity() {
        return (BaseActivity) super.getBaseActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mContentView =  inflater.inflate(getContentView(), container, false);
        return super.onCreateView(inflater, mContentView, savedInstanceState);
    }

    /**
     * @return onCreateView's container
     */
    @LayoutRes
    protected abstract int getContentView();

}
