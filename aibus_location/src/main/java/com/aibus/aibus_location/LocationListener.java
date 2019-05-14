package com.aibus.aibus_location;

import com.aibus.aibus_location.data.GpsData;

public interface LocationListener {
    //GPS数据更新
    void updateGpsData(GpsData gpsdata);
}
