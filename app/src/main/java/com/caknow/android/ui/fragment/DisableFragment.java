package com.caknow.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.caknow.android.R;
import com.jkb.fragment.rigger.annotation.LazyLoad;

import net.gtr.framework.rx.ProgressObserverImplementation;
import net.gtr.framework.rx.RxHelper;
import net.gtr.framework.util.Loger;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * @author heisenberg
 */
@LazyLoad
public class DisableFragment extends BaseFragment {

    @BindView(R.id.hint)
    TextView hintTv;

    @Override
    protected int getContentView() {
        return R.layout.layout_disable;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Loger.d("onViewCreated-" + this);

    }
    public void onLazyLoadViewCreated(Bundle savedInstanceState) {
        Loger.d("onLazyLoadViewCreated-" + this);
        RxHelper.bindOnUI(RxHelper.countdown(5, 1, TimeUnit.SECONDS),
                new ProgressObserverImplementation<Integer>(this) {
                    @Override
                    public void onNext(Integer integer) {
                        super.onNext(integer);
                        hintTv.setText(String.valueOf(integer));
                    }

                    @Override
                    protected void onRelease() {
                        super.onRelease();
                        hintTv.setText("");
                    }
                }.setShow(false));    }
}
