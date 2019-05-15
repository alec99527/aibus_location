# aibus_location
## 功能描述
封装地图（目前只支持高德地图，后续会集成百度地图），对外提供统一的定位接口。

## 使用方法
1. 底层封装的高德地图，所以需要申请高德地图API的key，然后再AndroidManifest.xml 中申明。
申请地址：https://lbs.amap.com/dev/key/app
```
<meta-data
android:name="com.amap.api.v2.apikey"
android:value="申请的key" />   
```         

2. 添加依赖
```
allprojects {
    repositories {
        maven { url "https://jitpack.io"}   
    }
}
```
```
    implementation 'com.github.alec99527:aibus_location:1.6'
```

3.
#### 基本用法
1）获取实列
2）初始化
3）设置定位监听。
4）开始监听
```
DeviceLocationManager deviceLocationManager = DeviceLocationManager.getInstance(App.getContext());
deviceLocationManager.init();
deviceLocationManager.setLocationListener(new LocationListener() {
    @Override
    public void updateGpsData(GpsData var1) {
        Log.d(TAG, "updateGpsData" + var1.getLatitude() + "," + var1.getLongitude());
    }
});

deviceLocationManager.startLocation();
```

#### 扩展功能
1. 设置模拟定位
1）设置模拟定位的GPS坐标
2）使能模拟定位
```
GpsData gpsBean = new GpsData();
gpsBean.setLatitude(22);
gpsBean.setLongitude(117);
gpsBean.setBearing(0);
deviceLocationManager.setMockLocationGps(gpsBean);
deviceLocationManager.setMockLocationEnable(true);
```

2. 设置定位间隔，单位为ms，最小间隔为1000ms。此功能需要在调用startLocation方法前设置才有效。
```
deviceLocationManager.setInterval(1000);
```

3. 设置是否为模拟导航(模拟导航的模式下，会降低GPS位置上报的频率，以免上报的太频繁)
```
deviceLocationManager.setNaviEmulatorEnable(true);
```
