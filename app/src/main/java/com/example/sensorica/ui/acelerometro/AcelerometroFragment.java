package com.example.sensorica.ui.acelerometro;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.sensorica.R;

public class AcelerometroFragment extends Fragment {


    TextView tv1;
    View vista;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vista= inflater.inflate(R.layout.fragment_acelerometro, container, false);
        tv1=vista.findViewById(R.id.text_gallery);
        return new Burbuja(getActivity(), this);

    }

}