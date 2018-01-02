package com.caknow.android.ui.fragment.register;

import android.view.View;

import com.caknow.android.R;
import com.caknow.android.ui.activity.RegisterActivity;
import com.caknow.android.ui.fragment.BaseFragment;

import butterknife.OnClick;

/**
 * Created by caroline on 2017/12/17.
 */

public class InputContactMessageFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getContentView() {
        return R.layout.fragment_register_step_3;
    }

    @OnClick(R.id.register_next)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_next:
                getBaseActivity().replaceFragment(RegisterActivity.fragment_layout, new InputPasswordConfirmFragment(), true);
                break;
            default:
                break;
        }
    }
}
