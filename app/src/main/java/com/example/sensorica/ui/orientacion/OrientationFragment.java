package com.example.sensorica.ui.orientacion;

import static android.content.Context.SENSOR_SERVICE;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sensorica.R;

public class OrientationFragment extends Fragment implements SensorEventListener {
    
    ImageView aguja, imageView2;

    private TextView txtX;
    private static SensorManager msensorManager;
    private Sensor mSensor;
    private float currentDegree;

    public OrientationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_orientacion, container, false);
        imageView2= view.findViewById(R.id.imageView2);
        aguja = view.findViewById(R.id.aguja);
        txtX = (TextView)view.findViewById(R.id.txtX);
        msensorManager = (SensorManager)getActivity().getSystemService(SENSOR_SERVICE);
        mSensor = msensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        return view;

    }
    @Override
    public void onResume() {
        super.onResume();

        if(mSensor!=null){
            msensorManager.registerListener(this,mSensor,SensorManager.SENSOR_DELAY_FASTEST);
        }
        else {
            Toast.makeText(getActivity(),"El celular no tiene sensor de orientación ", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        msensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        int degree = Math.round(event.values[0]);
        RotateAnimation rotateAnimation = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(500);
        rotateAnimation.setFillAfter(true);
        aguja.setAnimation(rotateAnimation);
        currentDegree=-degree;

        txtX.setText(String.valueOf(degree)+ " °");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
