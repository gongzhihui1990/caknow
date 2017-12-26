package com.caknow.android.ui.fragment.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.caknow.android.R;
import com.caknow.android.ui.activity.MainPageActivity;
import com.caknow.android.ui.fragment.BaseFragment;

import butterknife.OnClick;

/**
 * Created by caroline on 2017/12/17.
 */

public class InputPasswordConfirmFragment extends BaseFragment implements View.OnClickListener {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_register_step_4, container, false);
        return super.onCreateView(inflater, view, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //InputMethodManager inputMethodManager = (InputMethodManager)getBaseActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
// 接受软键盘输入的编辑文本或其它视图
        //inputMethodManager.showSoftInput(submitBt, InputMethodManager.SHOW_FORCED);
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
        }

    }
}
