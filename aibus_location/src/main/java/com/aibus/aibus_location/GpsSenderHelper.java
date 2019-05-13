/*
package com.aibus.aibus_location;

import android.util.Log;

import com.amap.api.navi.model.AMapNaviLocation;
import com.google.gson.Gson;
import com.haylion.shuttle.data.model.GpsBean;
import com.haylion.shuttle.data.model.GpsData;

*/
/**
 * 位置上报服务类
 * Created by 李振强 on 2018/6/21.
 * 底层通过MQTT实现，此类对其进一步封装
 *//*


public class GpsSenderHelper {
    private static final String TAG = "GpsSenderHelper";
    private static Gson gson = new Gson();
    // todo 模拟标记
    private static boolean isMockPosition = false;
    public static GpsBean currentGps;
    private static LocationPresenter locationPresenter = null;

    //生成环境关闭，模拟导航中的经纬度上传， 十个传1个
    private static int count = 0;
    private static int count2 = 0;

    */
/**
     * 导航时调用的上报GPS位置接口
     *//*

    public static void sendGps(AMapNaviLocation location) {
        if (location == null) {
            Log.e(TAG, "sendGps, location or duty id is invalid");
            return;
        }

        // 生成环境关闭，模拟导航中的经纬度上传， 十个传1个
        if (NaviConfig.getNaviEmulatorStatus()) {
*/
/*            long time = System.currentTimeMillis();
            if(time % 1000 == 0) {

            }*//*

            if (count % 10 == 0) {
                count = 0;
                Log.d(TAG, "sendGps" + location.getCoord().getLatitude() + "," + location.getCoord().getLongitude() + "," + location.getBearing());
                sendGps(location.getCoord().getLatitude(), location.getCoord().getLongitude(), location.getBearing());
            }
            //必须上报一次
            count++;
        } else {
            sendGps(location.getCoord().getLatitude(), location.getCoord().getLongitude(), location.getBearing());
        }
    }

    */
/**
     * 设备监听到GSP数据回调时，通过该方法向后台发送数据。
     *//*

    public static void sendGps(GpsBean location) {
        if (location == null) {
            Log.e(TAG, "sendGps, location or duty id is invalid");
            return;
        }
        // 生成环境关闭，模拟导航中的经纬度上传， 十个传1个
        if (NaviConfig.getNaviEmulatorStatus()) {
            if (count2 % 10 == 0) {
                count2 = 0;
                Log.d(TAG, "count2 " + count2);
                Log.d(TAG, "sendGps location: " + location.getLatitude() + "," + location.getLongitude() + "," + location.getBearing());
                sendGps(location.getLatitude(), location.getLongitude(), location.getBearing());
            }
            //必须上报一次
            count2++;
        } else {
            Log.d(TAG, "sendGps");
            sendGps(location.getLatitude(), location.getLongitude(), location.getBearing());
        }
    }


    */
/**
     * 设备监听到GSP数据回调时，通过该方法向后台发送数据。
     *//*

    public static void sendGps(double latitude, double longitude, float bearing) {

        //保存当前GPS位置
        if (null == currentGps) {
            currentGps = new GpsBean();
        }
        currentGps.setLongitude(longitude);
        currentGps.setLatitude(latitude);
        currentGps.setBearing(bearing);

        GpsData data = new GpsData();
        data.setTime(System.currentTimeMillis());

        //将数据转换成和服务器协商好的格式和字段
        data.setLat(latitude);
        data.setLng(longitude);
        data.setAngle(String.valueOf(bearing));
        Log.d(TAG, gson.toJson(data));
        //todo
////        MqttSendHelper.send(sendCode, receCode, gson.toJson(data));
//        MqttManagerHelper.sendMqttMessage(gson.toJson(data));
        if (locationPresenter == null) {
            locationPresenter = new LocationPresenter(null);
        }
        if (mDutyId != -1 || mVehicleId != -1) {
            locationPresenter.uploadLocationGps(mDutyId, mVehicleId, data);
        }
    }

    public static class OnLocationListener {
        public void onLocation(GpsData gpsData) {

        }
    }


    private static long mDutyId = -1;
    private static long mVehicleId = -1;

    static void setExtraData(long dutyId, long vehicleId) {
        mDutyId = dutyId;
        mVehicleId = vehicleId;
    }

}
*/
