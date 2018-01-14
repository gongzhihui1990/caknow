/*
 * Copyright (c) 2017. heisenberg.gong
 */

package net.gtr.framework.app.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;

import net.gtr.framework.app.activity.RxBaseActivity;
import net.gtr.framework.rx.ApplicationObserverResourceHolder;
import net.gtr.framework.rx.ObserverResourceManager;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class RxBaseFragment extends Fragment implements ApplicationObserverResourceHolder {
    protected RxBaseActivity mActivity;
    //observer 观察者管理
    ObserverResourceManager observerResourceManager = new ObserverResourceManager();

    @Override
    public void showDialog(Object o) {
        //TODO
    }

    protected RxBaseActivity getBaseActivity() {
        return mActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected View findViewById(@IdRes int id) {
        return getView().findViewById(id);
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable View container, @Nullable Bundle savedInstanceState) {
        if (container != null) {
            ButterKnife.bind(this, container);
        }
        return container;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (RxBaseActivity) activity;
    }


    public void addFragment(int layout, Fragment fragment) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout, fragment).commitAllowingStateLoss();
    }

    public void addFragment(int layout, Fragment fragment, boolean isSave) {
        FragmentManager manager = getChildFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout, fragment);
        if (isSave) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    public void addSupportFragment(int layout, Fragment fragment) {
        FragmentManager manager = mActivity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout, fragment).commitAllowingStateLoss();
    }

    @Override
    public void onDestroy() {
        clearWorkOnDestroy();
        super.onDestroy();
    }

    /**
     * onDestroy时调用此方法
     * 切断此Activity中的观察者容器中包含的观察者
     */
    public void clearWorkOnDestroy() {
        observerResourceManager.clearWorkOnDestroy();
    }

    /**
     * 添加disposable到Activity生命周期，Activity销毁时候，disposable执行dispose
     *
     * @param disposable
     */
    @Override
    public void addDisposable(Disposable disposable) {
        observerResourceManager.addDisposable(disposable);
    }

    /**
     * 类似 addSubscription(Disposable disposable)
     *
     * @param subscription
     */
    @Override
    public void addSubscription(Subscription subscription) {
        observerResourceManager.addSubscription(subscription);
    }

    @Override
    public void removeDisposable(Disposable disposable) {
        observerResourceManager.removeDisposable(disposable);
    }

    @Override
    public void removeSubscription(Subscription subscription) {
        observerResourceManager.removeSubscription(subscription);
    }

}
