package com.caknow.android.ui.fragment.register;

import android.content.Intent;
import android.view.View;

import com.caknow.android.R;
import com.caknow.android.ui.activity.MainPageActivity;
import com.caknow.android.ui.fragment.BaseFragment;

import butterknife.OnClick;

/**
 * Created by caroline on 2017/12/17.
 */

public class InputPasswordConfirmFragment extends BaseFragment implements View.OnClickListener {

    @Override
    protected int getContentView() {
        return R.layout.fragment_register_step_4;
    }

    @OnClick({R.id.register_next})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_next:
                Intent intent = new Intent(getBaseActivity(), MainPageActivity.class);
                getBaseActivity().startActivity(intent);
                getBaseActivity().finish();
                //getBaseActivity().replaceFragment(RegisterActivity.fragment_layout, new InputSmsConfirmFragment(), true);
                break;
            default:
                break;
        }

    }
}
