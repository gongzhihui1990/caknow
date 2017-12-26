package com.caknow.android.ui.fragment.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.caknow.android.R;
import com.caknow.android.ui.activity.RegisterActivity;
import com.caknow.android.ui.fragment.BaseFragment;

import butterknife.OnClick;

/**
 * Created by caroline on 2017/12/17.
 */

public class InputPhoneFragment extends BaseFragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_register_step_1, container, false);
        return super.onCreateView(inflater, view, savedInstanceState);
    }

    @OnClick({R.id.register_next})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_next:
                getBaseActivity().replaceFragment(RegisterActivity.fragment_layout, new InputSmsConfirmFragment(), true);
                break;
        }

    }
}
