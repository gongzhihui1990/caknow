/*
 * Copyright (c) 2017. heisenberg.gong
 */

package net.gtr.framework.rx;

import android.content.Context;

/**
 * Created by heisenberg on 2017/7/21.
 * heisenberg.gong@koolpos.com
 */

public interface ApplicationObserverResourceHolder<DialogParams> extends ObserverResourceHolder {
    Context getContext();

    void showDialog(DialogParams params);
}
