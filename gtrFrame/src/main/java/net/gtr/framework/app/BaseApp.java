/*
 * Copyright (c) 2017. heisenberg.gong
 */

package net.gtr.framework.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Created by heisenberg on 2017/10/15.
 * heisenberg.gong@koolpos.com
 */

public class BaseApp extends Application {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getBaseContext();
    }

    /**
     * 获取进程号对应的进程名
     *
     * @return 进程名
     */
    public static String getProcessName() {
        int pid= android.os.Process.myPid();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return "unKnow";
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return "unKnow";
    }
}
