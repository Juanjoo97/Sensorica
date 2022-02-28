package com.example.sensorica;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ListarSensores extends AppCompatActivity {

    private EditText txtListaSensores;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_sensores);

        txtListaSensores = (EditText) findViewById(R.id.txtListaSensores);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    public void btnListarSensores_Click(View view){
        String cad = "";

        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for(Sensor s: deviceSensors){
            cad = cad + "* Sensor: " + s.getName() +" - " + s.getStringType() + "\n";
        }

        txtListaSensores.setText(cad);
    }

}
