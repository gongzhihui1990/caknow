package com.caknow.android.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.caknow.android.R;

import net.gtr.framework.rx.ProgressObserverImplementation;
import net.gtr.framework.rx.RxHelper;
import net.gtr.framework.util.Loger;

import org.reactivestreams.Subscriber;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;

/**
 * @author heisenberg
 */
public class DisableFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.hint)
    TextView hintTv;
    @BindView(R.id.hint_1)
    TextView hint1Tv;

    @Override
    protected int getContentView() {
        return R.layout.layout_disable;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Loger.d("onViewCreated-" + this);
        testObserver();
    }

    @OnClick(R.id.hint_1)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hint_1:
                if (disposable != null && !disposable.isDisposed()){
                    removeDisposable(disposable);
                }
                break;
            default:
                break;
        }
    }

    Disposable disposable;

    private void testObserver() {
        Observable<Integer> observable = RxHelper.countdown(5, 1, TimeUnit.SECONDS);
        Observer<Integer> observer = new ProgressObserverImplementation<Integer>(this) {
            @Override
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                super.onNext(integer);
                hintTv.setText(String.valueOf(integer));
            }

            @Override
            protected void onBegin() {
                super.onBegin();
                hint1Tv.setText("开始加载Observer");
            }

            @Override
            protected void onRelease() {
                super.onRelease();
                hintTv.setText("");
                hint1Tv.setText("Observer加载完成");
                test();
            }
        }.setShow(false);
        RxHelper.bindOnUI(observable, observer);
    }

    private void test() {

        Flowable<String> flowable = Flowable.just(new Date())
                .delay(2, TimeUnit.SECONDS)
                .map(new Function<Date, String>() {
                    @Override
                    public String apply(Date date) throws Exception {
                        return date.toString();
                    }
                });
        Subscriber<String> subscriber = new ProgressObserverImplementation<String>() {
            @Override
            public void onNext(String o) {
                super.onNext(o);
                hint1Tv.setText(hint1Tv.getText() + " at " + o);
            }
        }.setShow(false);
        RxHelper.bindOnUI(flowable, subscriber);
    }

}
