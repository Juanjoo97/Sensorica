package com.example.sensorica.ui.proximidad;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.widget.TextView;


import static android.content.Context.SENSOR_SERVICE;

import androidx.fragment.app.Fragment;

import com.example.sensorica.R;



public class proximidad extends Fragment implements SensorEventListener {
        FrameLayout fl;
        SensorManager sm;
        Sensor sensor;
        TextView textico;




        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_proximidad, container, false);
            textico = (TextView)view.findViewById(R.id.texto);
            fl = (FrameLayout)view.findViewById(R.id.sensorProxi);
            sm = (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
            sensor = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

            return view;

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            String valorTexto = String.valueOf(event.values[0]);
            float valor = Float.parseFloat(valorTexto);
            if (valor==0){
                textico.setText("Cerca");
                textico.setTextColor(Color.WHITE);
                fl.setBackgroundColor(Color.BLACK);

            }
            else {
                textico.setText("Lejos");
                textico.setTextColor(Color.BLACK);
                fl.setBackgroundColor(Color.WHITE);
            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }


    }