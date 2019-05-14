package com.aibus.aibus_location;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.aibus.aibus_location.data.GpsBean;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;


public class DeviceLocationManager implements AMapLocationListener {
    private static final String TAG = "LocationService";
    private Context mContext;
    private int interval = 10;
    private Boolean mockLocationEnable = false;
    private GpsBean mockGps;
    private LocationListener locationListener;

    public DeviceLocationManager() {
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void init(Context context) {
        mContext = context;

        SharedPreferenceUtil.initSp(context, "DeviceLocationManager");

        locationHelper = LocationHelper.getInstance();
        locationHelper.initLocation(mContext, this);
    }

    /**
     * 定位间隔
     *
     * @param time
     */
    public void setInternal(long time) {
        locationHelper.setInterval(time);
    }

    public void setMockLocationGps(GpsBean gps) {
        mockGps = gps;
    }

    public void setMockLocationEnable(Boolean mock) {
        mockLocationEnable = mock;
    }

    /**
     * 定位回调方法
     *
     * @param aMapLocation
     */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if ((aMapLocation != null) && (aMapLocation.getErrorCode() == 0)) {
            GpsBean locationGps;
            if (mockLocationEnable && mockGps != null) {
                locationGps = mockGps;
            } else {
                locationGps = new GpsBean();
                locationGps.setLatitude(aMapLocation.getLatitude());
                locationGps.setLongitude(aMapLocation.getLongitude());
                locationGps.setBearing(aMapLocation.getBearing());
            }
            //回调
            locationListener.updateGpsData(locationGps);
        } else {
            Log.e(TAG, "定位错误：" + aMapLocation);
        }
    }

    private static LocationHelper locationHelper;

    public void startLocation() {
//        locationHelper = LocationHelper.getInstance();
//        locationHelper.initLocation(mContext, this);

        locationHelper.startLocation();

        locationHelper.enableBackgroundLocation(2001, buildNotification());
    }

    public void stopLocation() {
        if (locationHelper != null) {
            locationHelper.stopLocation();
        }
    }

    public void destroyLocation() {

    }

    private static final String NOTIFICATION_CHANNEL_NAME = "BackgroundLocation";
    private NotificationManager notificationManager = null;
    boolean isCreateChannel = false;

    @SuppressLint("NewApi")
    private Notification buildNotification() {

        Notification.Builder builder = null;
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= 26) {
            //Android O上对Notification进行了修改，如果设置的targetSDKVersion>=26建议使用此种方式创建通知栏
            if (null == notificationManager) {
                notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
            }
            String channelId = mContext.getPackageName();
            if (!isCreateChannel) {
                NotificationChannel notificationChannel = new NotificationChannel(channelId,
                        NOTIFICATION_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                notificationChannel.enableLights(true);//是否在桌面icon右上角展示小圆点
                notificationChannel.setLightColor(Color.BLUE); //小圆点颜色
                notificationChannel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
                notificationManager.createNotificationChannel(notificationChannel);
                isCreateChannel = true;
            }
            builder = new Notification.Builder(mContext, channelId);
        } else {
            builder = new Notification.Builder(mContext);
        }
        builder.setSmallIcon(null)
                .setContentTitle("定位")
                .setContentText("正在后台运行")
                .setWhen(System.currentTimeMillis());

        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification = builder.build();
        } else {
            return builder.getNotification();
        }
        return notification;
    }

}
