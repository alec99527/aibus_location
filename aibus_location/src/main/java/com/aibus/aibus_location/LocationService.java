package com.aibus.aibus_location;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.IBinder;
import android.util.Log;

//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationListener;
//import com.haylion.shuttle.common.Constants;
//import com.haylion.shuttle.common.util.BusUtils;
//import com.haylion.shuttle.common.util.LogUtils;
//import com.haylion.shuttle.data.model.GpsBean;

public class LocationService extends Service {
    private static final String TAG = "LocationService";
/*    private static GpsBean locationGps;
    private static boolean firstLocationSuccess = true; *//*第一次定位*//*
    private static LocationHelper locationHelper;*/

    private static String EXTRA_DUTY_ID = "duty_id";
    private static String EXTRA_VEHICLE_ID = "vehicle_id";

    public LocationService() {
    }

    public static void start(Context context, long dutyId, long vehicleId) {
        Intent intent = new Intent(context, LocationService.class);
        intent.putExtra(EXTRA_DUTY_ID, dutyId);
        intent.putExtra(EXTRA_VEHICLE_ID, vehicleId);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        //
//        new LocationPresenter(null);
//        startLocation();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        if (intent != null) {
            long dutyId = intent.getLongExtra(EXTRA_DUTY_ID, -1);
            long vehicleId = intent.getLongExtra(EXTRA_VEHICLE_ID, -1);
//            GpsSenderHelper.setExtraData(dutyId, vehicleId);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
//        locationHelper.stopLocation();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
/*        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");*/
    }


/*    public void startLocation() {
        locationHelper = LocationHelper.getInstance();
        locationHelper.initLocation(getApplicationContext(), new AMapLocationListener() {
            *//**
             * 经纬度回调。
             * 1、通过EVENTBUS将其发送到主界面上，主要用于计算到站行为
             * 2、将其经纬度通过MQTT发送给后台。
             * 3、打印日志。
             *//*
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                Log.d(TAG, "onLocationChanged");
//                Log.d(TAG, "onLocationChanged: " + aMapLocation + ",errorCode:" + aMapLocation.getErrorCode());
                if ((aMapLocation != null) && (aMapLocation.getErrorCode() == 0)) {
                    locationGps = new GpsBean();
                    locationGps.setLatitude(aMapLocation.getLatitude());
                    locationGps.setLongitude(aMapLocation.getLongitude());
                    locationGps.setBearing(aMapLocation.getBearing());
                    Log.d(TAG, "sendGps, lng: " + locationGps.getLongitude() + ",lat:" + locationGps.getLatitude()
                            + "," + locationGps.getBearing());
//                    //todo
//                    GpsSenderHelper.sendGps(locationGps);

                    //模拟定位打开
                    if (LocationConfig.getMockLocationStatus()) {
                        String location = LocationConfig.getMockLocation();
                        switch (location) {
                            case Constants.Gps.NANKEDA:
                                locationGps.setLongitude(Constants.Gps.NANKEDA_LNG);
                                locationGps.setLatitude(Constants.Gps.NANKEDA_LAT);
                                break;
                            case Constants.Gps.LONGSHENG:
                                locationGps.setLongitude(Constants.Gps.LONGSHENG_LNG);
                                locationGps.setLatitude(Constants.Gps.LONGSHENG_LAT);
                                break;
                            default:
                                locationGps.setLongitude(Constants.Gps.DEFAULT_LNG);
                                locationGps.setLatitude(Constants.Gps.DEFAULT_LAT);
                                break;
                        }
                    }

                    //不管是模拟导航还是实际导航，必须上报一次车辆位置
                    if (firstLocationSuccess == true) {
                        GpsSenderHelper.sendGps(locationGps);
                        BusUtils.post(locationGps);
                        firstLocationSuccess = false;
                    } else {
                        if (LocationConfig.getNaviEmulatorStatus()) {*//*模拟导航，此处不容许上报位置*//*
                            Log.d(TAG, "getNaviEmulatorStatus: true, not update location gps here");
                        } else {
                            GpsSenderHelper.sendGps(locationGps);
                            BusUtils.post(locationGps);
                        }
                    }
                } else {
                    LogUtils.d("onLocationChanged error:" + aMapLocation);
                }
            }
        });

        locationHelper.startLocation();

        locationHelper.enableBackgroundLocation(2001, buildNotification());

    }*/

    public static void stop(Context context) {
        Intent intent = new Intent(context, LocationService.class);
        context.stopService(intent);
    }


    private static final String NOTIFICATION_CHANNEL_NAME = "BackgroundLocation";
    private NotificationManager notificationManager = null;
    boolean isCreateChannel = false;
/*    @SuppressLint("NewApi")
    private Notification buildNotification() {

        Notification.Builder builder = null;
        Notification notification = null;
        if(android.os.Build.VERSION.SDK_INT >= 26) {
            //Android O上对Notification进行了修改，如果设置的targetSDKVersion>=26建议使用此种方式创建通知栏
            if (null == notificationManager) {
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            }
            String channelId = getPackageName();
            if(!isCreateChannel) {
                NotificationChannel notificationChannel = new NotificationChannel(channelId,
                        NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.enableLights(true);//是否在桌面icon右上角展示小圆点
                notificationChannel.setLightColor(Color.BLUE); //小圆点颜色
                notificationChannel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
                notificationManager.createNotificationChannel(notificationChannel);
                isCreateChannel = true;
            }
            builder = new Notification.Builder(getApplicationContext(), channelId);
        } else {
            builder = new Notification.Builder(getApplicationContext());
        }
        builder.setSmallIcon(R.drawable.caricon)
                .setContentTitle("定位")
                .setContentText("正在后台运行")
                .setWhen(System.currentTimeMillis());

        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification = builder.build();
        } else {
            return builder.getNotification();
        }
        return notification;
    }*/

}
