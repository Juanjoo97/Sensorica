package com.example.sensorica.Servicio;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

public class servicio extends Service implements LocationListener {

    private final Context ctx;
    double longitud, altitud;
    Location location;
    boolean gpsActivo;
    TextView cordenadas;
    LocationManager locationManager; //Objeto que permite manejar el tipo de conexion

    public servicio() {
        super();
        this.ctx = this.getApplicationContext();
    }

    public servicio(Context c) {
        super();
        this.ctx = c;
        getLocation();
    }

    public void setView(View v) {
        cordenadas = (TextView) v;
        cordenadas.setText("Altitud= " + String.valueOf(altitud) + " Longitud= " + String.valueOf(longitud));
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) this.ctx.getSystemService(LOCATION_SERVICE);
            //El contexto que pasamos a ese servicio nos permite acceder al servicio del sistema
            gpsActivo = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            if (gpsActivo) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60, 5, this);
            }
            if (locationManager!=null){
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                altitud = location.getAltitude();
                longitud = location.getLongitude();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
