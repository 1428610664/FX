package com.weisj.fx.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import com.weisj.fx.MainActivity;

import java.util.List;

/**
 * Created by BBMJ on 2016/8/3.
 */
public class LocationUtils {


    private static final String TAG = MainActivity.class.getSimpleName();

    public double latitude = 10.0;

    public double longitude = 10.0;

    private LocationManager locationManager;

    private Activity activity;


    @TargetApi(Build.VERSION_CODES.M)
    public LocationUtils(Activity activity) {

        this.activity = activity;
        //获得位置服务的管理对象
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) activity.getSystemService(serviceName);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        12);
                return;
            }
        }

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = locationManager.getBestProvider(criteria, true);

        if (provider != null) {
            Location location = locationManager.getLastKnownLocation(provider);
            updateWithNewLocation(location);
            locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            12);
                    return;
                }
            }
            locationManager.removeUpdates(locationListener);
        }

        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (activity.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && activity.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                            12);
                    return;
                }
            }
            locationManager.removeUpdates(locationListener);
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
    };

    private void updateWithNewLocation(Location location) {
        String latLongString;
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            Geocoder geocoder = new Geocoder(activity);
//                        Geocoder geocoder = new Geocoder(this, Locale.CHINA);
            List places = null;

            try {
//                                Thread.sleep(2000);
                places = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);
//                                Thread.sleep(2000);
//                Toast.makeText(activity, places.size()+"", Toast.LENGTH_LONG).show();
                System.out.println(places.size() + "");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String placename = "";
            if (places != null && places.size() > 0) {
                // placename=((Address)places.get(0)).getLocality();
                //一下的信息将会具体到某条街
                //其中getAddressLine(0)表示国家，getAddressLine(1)表示精确到某个区，getAddressLine(2)表示精确到具体的街
                placename = ((Address) places.get(0)).getLocality();
                CommenString.locationState = true;
                CommenString.city = placename;
                CommenString.selectCity = placename;
                try {
                    if ('市' == CommenString.city.charAt(CommenString.city.length() - 1)) {
                        CommenString.city = CommenString.city.substring(0, CommenString.city.length()-1);
                        CommenString.selectCity =  CommenString.city ;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

//
            }


//            Toast.makeText(activity, placename, Toast.LENGTH_LONG).show();


            CommenString.Latitude = lat;
            CommenString.Longitude = lng;

            latLongString = "纬度:" + lat + "/n经度:" + lng;

        } else {
            latLongString = "无法获取地理信息";
        }
//        Toast.makeText(activity, latLongString, Toast.LENGTH_LONG).show();
    }


}
