package com.caknow.android.ui.fragment.register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.caknow.android.R;
import com.caknow.android.ui.activity.RegisterActivity;
import com.caknow.android.ui.fragment.BaseFragment;
import com.jkb.vcedittext.VerificationCodeEditText;

import butterknife.BindView;

/**
 * Created by caroline on 2017/12/17.
 */

public class InputSmsConfirmFragment extends BaseFragment {

    @BindView(R.id.et_register_code)
    VerificationCodeEditText et_register_code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_register_step_2, container, false);
        return super.onCreateView(inflater, view, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_register_code.setOnVerificationCodeChangedListener(new VerificationCodeEditText
                .OnVerificationCodeChangedListener() {

            @Override
            public void onVerCodeChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onInputCompleted(CharSequence s) {
                if (s.length() == 4) {
                    et_register_code.setText("");
                    getBaseActivity().replaceFragment(RegisterActivity.fragment_layout, new InputContactMessageFragment(), true);
                }
            }
        });
        et_register_code.requestFocusFromTouch();
    }
}
