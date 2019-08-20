package com.fzzz.mydemo.ui.gps;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;

import java.util.List;

/**
 * description:
 * author: ShenChao
 * time: 2019-04-23
 * update:
 */
public class LocationUtil {

    private static LocationUtil locationUtil = new LocationUtil();
    public static String longitude, latitude;
    private LocationManager locationManager;
    private CustomLocationListener locationListener;

    private LocationUtil() {

    }

    public static LocationUtil getInstance() {
        return locationUtil;
    }

    public void getLocation(Activity context) {
        //针对vivo做权限处理
        if ("vivo".equals(Build.BRAND)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                LocationProvider provider = locationManager.getProvider(LocationManager.GPS_PROVIDER);

                if (provider == null) {
                    goToLbsSetting(context);
                } else {
                    //已授权
                    getLBS(context);
                }
            }
            return;
        }

        //其他机型处理
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //已授权
            getLBS(context);
        }
    }

    private void goToLbsSetting(Context context) {
        Activity activity = (Activity) context;
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Intent mIntent = new Intent();
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", activity.getPackageName(), null));
            activity.startActivity(mIntent);
        } else {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            activity.startActivity(intent);
        }
    }

    public void getLBS(Context context) {
        //获取用户手机的位置服务
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location = null;
        String providerResult = "passive";
        List<String> providers = locationManager.getProviders(true);
        locationListener = new CustomLocationListener();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        for (String provider : providers) {
            Location nextLocation = locationManager.getLastKnownLocation(provider);
            if (nextLocation == null) {
                continue;
            }
            if (location == null || nextLocation.getAccuracy() < location.getAccuracy()) {
                location = nextLocation;
                providerResult = provider;
            }
        }
        if (location != null) {
            longitude = location.getLongitude() + "";
            latitude = location.getLatitude() + "";
        } else {
            locationManager.requestLocationUpdates(providerResult, 0, 0, locationListener);
        }
    }


    private class CustomLocationListener implements LocationListener {

        //当位置发生变化的时候 调用.
        @Override
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude() + "";
            latitude = location.getLatitude() + "";
        }

        //位置提供者状态变化的时候 调用
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        //当某个位置提供者 被启用
        @Override
        public void onProviderEnabled(String provider) {

        }

        //当某个位置提供者 被禁用
        @Override
        public void onProviderDisabled(String provider) {

        }

    }

}
