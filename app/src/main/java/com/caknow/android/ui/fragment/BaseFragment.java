package com.caknow.android.ui.fragment;

import com.caknow.android.ui.activity.BaseActivity;

import net.gtr.framework.app.activity.RxBaseActivity;
import net.gtr.framework.app.fragment.RxBaseFragment;

/**
 * Created by caroline on 2017/12/17.
 */

public class BaseFragment extends RxBaseFragment {
    @Override
    protected BaseActivity getBaseActivity() {
        return (BaseActivity)super.getBaseActivity();
    }
}
