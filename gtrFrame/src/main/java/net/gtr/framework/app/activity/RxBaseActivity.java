/*
 * Copyright (c) 2017. heisenberg.gong
 */

package net.gtr.framework.app.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import net.gtr.framework.rx.ApplicationObserverResourceHolder;
import net.gtr.framework.rx.ObserverResourceManager;

import org.reactivestreams.Subscription;

import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;

public abstract class RxBaseActivity extends BaseFragmentActivity implements ApplicationObserverResourceHolder {

    //observer 观察者管理
    ObserverResourceManager observerResourceManager = new ObserverResourceManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    public void addFragment(int layout, Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        try {
            transaction.replace(layout, fragment).commitAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addFragment(int layout, Fragment fragment, boolean isSave) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout, fragment);
        if (isSave) {
            transaction.addToBackStack(null);
        }
        transaction.commitAllowingStateLoss();
    }

    /**
     * 清除FragmentManager内的Fragment层级到指定个数
     *
     * @param popLevel popLevel
     */

    protected void popFragmentToLevel(int popLevel) {
        FragmentManager manager = getSupportFragmentManager();
        while (manager.getBackStackEntryCount() >= popLevel) {
            manager.popBackStackImmediate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
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
     * @param disposable disposable
     */
    @Override
    public void addDisposable(Disposable disposable) {
        observerResourceManager.addDisposable(disposable);
    }

    /**
     * 类似 addSubscription(Disposable disposable)
     *
     * @param subscription subscription
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


    @Override
    public Context getContext() {
        return this;
    }
}