package com.data.www.datasavedemo.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.location.Location;

import com.data.www.datasavedemo.interfaces.DataInterface;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by qwh on 2017/12/4.
 */

public class LocationUtil {
    private  FusedLocationProviderClient mFusedLocationClient;
//    private static LocationUtil locationUtil = null;
    private Context context;
    public LocationUtil(Context c){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(c);
        this.context = c;
    }
//    public static LocationUtil getInstance(Context context){
//        if (locationUtil == null){
//            synchronized (locationUtil){
//                if (locationUtil == null){
//                    locationUtil = new LocationUtil(context);
//                }
//            }
//        }
//        return locationUtil;
//    }
    public void getLastLocation(final DataInterface callback) throws SecurityException{
        checkPermission();
        mFusedLocationClient.getLastLocation().addOnSuccessListener((Activity) context, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                callback.success(location,"获取地址。。。");
            }
        });
    }

    private void checkPermission() {
        String[] permissions = new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

    }
//    private static class LocationHolder{
//        private static LocationUtil util = new LocationUtil();
//    }
}
