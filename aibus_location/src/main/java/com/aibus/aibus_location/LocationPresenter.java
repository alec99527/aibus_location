/*
package com.aibus.aibus_location;

import android.util.Log;

import com.haylion.shuttle.common.base.AbstractView;
import com.haylion.shuttle.common.base.BasePresenter;
import com.haylion.shuttle.common.rx.ApiSubscriber;
import com.haylion.shuttle.data.model.GpsData;
import com.haylion.shuttle.data.repo.LocationRepository;

public class LocationPresenter extends BasePresenter<AbstractView, LocationRepository> {
    private static final String TAG = "LocationPresenter";

    public LocationPresenter(AbstractView view) {
        super(view, new LocationRepository());
    }

    */
/**
     * 发送位置信息到后台
     *//*

    public void uploadLocationGps(long dutyId, long vehicleId, GpsData gpsData) {
        Log.d(TAG, "uploadLocationGps:" + gpsData.toString());

        addDisposable(repo.uploadCarPostion(dutyId, vehicleId, gpsData, new ApiSubscriber<Void>() {
            @Override
            public void onSuccess(Void object) {
                Log.d(TAG, "uploadLocationGps onSuccess: ");
            }

            @Override
            public void onError(int code, String msg) {
                Log.e(TAG, "uploadLocationGps onFailed: " + msg);
            }
        }));
    }

}
*/
