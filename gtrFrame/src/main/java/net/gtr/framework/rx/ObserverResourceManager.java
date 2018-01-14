package net.gtr.framework.rx;

import android.content.Context;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2018/1/14.
 */

public class ObserverResourceManager implements ObserverResourceHolder{
    private List<Subscription> compositeSubscription = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            return;
        }
        compositeDisposable.add(disposable);
    }

    @Override
    public void addSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            return;
        }
        compositeSubscription.add(subscription);
    }

    @Override
    public void removeDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            return;
        }
        compositeDisposable.remove(disposable);
    }

    @Override
    public void removeSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            return;
        }
        compositeSubscription.remove(subscription);
    }

    @Override
    public void clearWorkOnDestroy() {
        //disposable clear
        compositeDisposable.clear();
        //subscription clear
        for (Subscription subscription : compositeSubscription) {
            if (subscription != null)
                subscription.cancel();
            subscription = null;
        }
        compositeSubscription.clear();
    }

}
