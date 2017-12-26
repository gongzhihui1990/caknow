package com.caknow.android.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.caknow.android.R;
import com.caknow.android.ui.fragment.register.InputPhoneFragment;

/**
 * Created by caroline on 2017/12/15.
 */

public class RegisterActivity extends BaseActivity {
    public final static int fragment_layout = R.id.register_fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (savedInstanceState == null) {
            replaceFragment(fragment_layout, new InputPhoneFragment(), false);
        }
    }
}
