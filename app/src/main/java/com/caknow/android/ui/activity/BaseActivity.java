package com.caknow.android.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.caknow.android.R;

import net.gtr.framework.app.activity.RxBaseActivity;

import butterknife.OnClick;

/**
 * Created by caroline on 2017/12/15.
 */
public abstract class BaseActivity extends RxBaseActivity implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this instanceof LoginActivity||this instanceof RegisterActivity){
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        }
    }

    @OnClick({R.id.iv_titleBack})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_titleBack:
                onBackPressed();
                break;
        }
    }


    public void replaceFragment(int layoutId, @Nullable Fragment fragment,
                                boolean isAddBack) {
        if (fragment == null){
            return;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(layoutId, fragment);
        if (isAddBack) {
            ft.addToBackStack(null);
        }
        ft.commitAllowingStateLoss();
    }

}
