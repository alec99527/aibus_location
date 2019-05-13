package com.aibus.aibus_location;


import android.util.Log;

public class NaviConfig {
    private static final String TAG = "NaviConfig";
    //todo
    private static boolean isEmulatorMode = false;

    /**
     * 设置模拟导航模式
     *
     * @param enable
     */
    public static void setNaviEmulatorStatus(boolean enable) {
        isEmulatorMode = enable;
        Log.d(TAG, "setEmulatorStatus isEmulatorMode " + isEmulatorMode);
    }

    /**
     * 是否为模拟导航
     */
    public static boolean getNaviEmulatorStatus() {
        return isEmulatorMode;
    }
}
