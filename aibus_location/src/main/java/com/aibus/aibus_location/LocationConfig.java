/*
package com.aibus.aibus_location;

import android.util.Log;

import com.haylion.shuttle.common.util.LogUtils;
import com.haylion.shuttle.data.PrefserHelper;

public class LocationConfig {
        private static final String TAG = "GlobalConfigHelper";

        public static void enableMockLocation(boolean enable) {
            Log.d("Location", "enableMockLocation enable:" + enable);
            String value = PrefserHelper.getCache(PrefserHelper.KEY_LOCATION_SIMULATE_ENABLE);
            Log.d("Location", "before, simulate enable:" + value);
            if(enable){
                Log.d("Location", "模拟定位开启");
                PrefserHelper.setCache(PrefserHelper.KEY_LOCATION_SIMULATE_ENABLE, "1");
            }else{
                Log.d("Location", "模拟定位关闭");
                PrefserHelper.setCache(PrefserHelper.KEY_LOCATION_SIMULATE_ENABLE, "0");
            }
            value = PrefserHelper.getCache(PrefserHelper.KEY_LOCATION_SIMULATE_ENABLE);
            Log.d("Location", "after, simulate enable:" + value);
        }

        public static boolean getMockLocationStatus() {
//        Log.d("Location", "getMockLocationStatus ");
            String value = PrefserHelper.getCache(PrefserHelper.KEY_LOCATION_SIMULATE_ENABLE);
            if(value != null) {
                return value.equals("1");
            } else {
                return false;
            }
        }

        public static void setMockLocation(String location) {
            Log.d("Location", "setMockLocation location:" + location);
            PrefserHelper.setCache(PrefserHelper.KEY_LOCATION_SIMULATE_DETAIL, location);
        }

        public static String getMockLocation() {
            String location = PrefserHelper.getCache(PrefserHelper.KEY_LOCATION_SIMULATE_DETAIL);
            Log.d("Location", "getMockLocation location:" + location);
            if(location != null) {
                return location;
            } else {
                return "";
            }
        }


        */
/**
         * 模拟导航是否开启
         *//*

        public static boolean getNaviEmulatorStatus(){
             boolean enable;
            String mode = PrefserHelper.getCache(PrefserHelper.KEY_NAVI_EMULATOR_ENABLE);
            Log.d("Location", "getNaviEmulator mode:" + mode);
            if(mode != null && mode.equals("1")){
                Log.d("Location", "模拟导航开启");
                enable = true;
            }else{
                Log.d("Location", "模拟导航关闭");
                enable = false;
            }
            return enable;
        }

        public static void setNaviEmulator(boolean enable) {
            Log.d("Location", "setNaviEmulator enable:" + enable);
            if(enable){
                PrefserHelper.setCache(PrefserHelper.KEY_NAVI_EMULATOR_ENABLE, "1");
                NaviConfig.setNaviEmulatorStatus(true);
                Log.d("Location", "模拟导航开启");
            }else{
                PrefserHelper.setCache(PrefserHelper.KEY_NAVI_EMULATOR_ENABLE, "0");
                NaviConfig.setNaviEmulatorStatus(false);
                Log.d("Location", "模拟导航关闭");
            }
        }

    }

*/
