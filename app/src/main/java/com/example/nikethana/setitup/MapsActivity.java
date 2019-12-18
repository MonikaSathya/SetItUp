package com.example.nikethana.setitup;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;

import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.lang.Math;
import static java.lang.Math.sqrt;
import android.media.*;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    public LatLng latLng;
    public double lat_a, lng_a;
    public boolean isAlarmSet = false;
    AlarmManager am;
    PendingIntent pi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {


                @Override
                        public void onLocationChanged(Location location) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            latLng = new LatLng(latitude, longitude);
                            //lat_a = latitude;
                            //lng_a = longitude;
                            Geocoder geocoder = new Geocoder(getApplicationContext());
                            try {
                                List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                                lat_a = addressList.get(0).getLatitude();
                                lng_a = addressList.get(0).getLongitude();
                                double r = distance333(lat_a, lng_a);
                                Log.d("ADebugTag", "Value: " + Double.toString(r));
//                                Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
//                                intent.putExtra("distance", r);
//                                startActivity(intent);
                                long rs=(long)r;
                                Calendar calendar = Calendar.getInstance();
                                if ((android.os.Build.VERSION.SDK_INT >= 23)&&(rs <= 3)) {
                                    calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                                    if (!isAlarmSet) {
                                        isAlarmSet = setAlarm(calendar.getTimeInMillis());
                                    }
//
//
//
                                    Toast toast1 = Toast.makeText(getApplicationContext(), "within radius", Toast.LENGTH_SHORT);
                                    toast1.show();
                                }

                                String str = addressList.get(0).getSubLocality() + ",";
                                str += addressList.get(0).getLocality() + ",";
                                str += addressList.get(0).getCountryName() + ",";
                                str += addressList.get(0).getLatitude() + ",";
                                str += addressList.get(0).getLongitude();
                                mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }


                        @Override
                        public void onStatusChanged(String s, int i, Bundle bundle) {

                        }

                        @Override
                        public void onProviderEnabled(String s) {

                        }

                        @Override
                        public void onProviderDisabled(String s) {

                        }

                    }
            );


        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    latLng = new LatLng(latitude, longitude);
                    //lat_a = latitude;
                    //lng_a = longitude;
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        lat_a = addressList.get(0).getLatitude();
                        lng_a = addressList.get(0).getLongitude();
                        double r = distance333(lat_a, lng_a);
                        Log.d("ADebugTag", "Value: " + Double.toString(r));
//                        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
//                        intent.putExtra("distance", r);
//                        startActivity(intent);
                        long rs=(long)r;
                        Calendar calendar = Calendar.getInstance();
                        if ((android.os.Build.VERSION.SDK_INT >= 23)&&(rs <= 3)) {
                            calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                            if (!isAlarmSet) {
                                isAlarmSet = setAlarm(calendar.getTimeInMillis());
                            }
                            Toast toast1 = Toast.makeText(getApplicationContext(), "within radius", Toast.LENGTH_SHORT);
                            toast1.show();
                        }



                        String str = addressList.get(0).getSubLocality() + ",";
                        str += addressList.get(0).getLocality() + ",";
                        str += addressList.get(0).getCountryName() + ",";
                        str += addressList.get(0).getLatitude() + ",";
                        str += addressList.get(0).getLongitude();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        }




    }

    private boolean setAlarm(long time) {




        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(this, MyAlarm.class);


        pi = PendingIntent.getBroadcast(this, 0, i, 0);


        am.setRepeating(AlarmManager.RTC_WAKEUP, time, AlarmManager.INTERVAL_FIFTEEN_MINUTES, pi);
        Toast.makeText(this, "Alarm is set", Toast.LENGTH_SHORT).show();
        return true;
    }







    public double distance333(double lat_a, double lng_a) {
        LatLng latLng7 = getIntent().getExtras().getParcelable("dest");
        int Radius = 6371;// radius of earth in Km
        mMap.addMarker(new MarkerOptions().position(latLng7).title("destination"));
        double lat1 = lat_a;//latLng.latitude
        double lat2 = latLng7.latitude;
        double lon1 = lng_a;// latLng.longitude;
        double lon2 = latLng7.longitude;
        Log.d("a debug tag", "Value: " + Double.toString(lat2));
        Double res1 = lat2 - lat1;
        double dLat = Math.toRadians(res1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double s = Math.sqrt(a);
        double mon = Math.sqrt(4);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult = Radius * c;
        double km = valueResult / 1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec = Integer.valueOf(newFormat.format(km));
        double meter = valueResult % 1000;
        int meterInDec = Integer.valueOf(newFormat.format(meter));
        // Log.i("Radius Value", "" + valueResult + "   KM  " + kmInDec
        // + " Meter   " + meterInDec+"operation a"+a+"operation c"+c+"kilo"+km+"metre"+meter);
        //return km;
        Log.d("MONIKAAA", "Value: " + Double.toString(res1));
        Log.d("lat of cur ", "Value: " + Double.toString(lat_a));
        Log.d("long of cur", "Value: " + Double.toString(lng_a));
        //Log.d("MONIKAAA++++++", "Value: " + Double.toString(latitude));
        Log.d("worst", "Value: " + Double.toString(dLon));
        return Radius * c;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)

    {
        mMap = googleMap;
    }
}


