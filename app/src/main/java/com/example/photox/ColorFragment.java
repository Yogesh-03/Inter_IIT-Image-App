package com.example.photox;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ColorFragment# factory method to
 * create an instance of this fragment.
 */
public class ColorFragment extends Fragment {

    Slider sat;
    Slider cont, bright, opac;
    ItemViewModel itemViewModel;
    TextView colorReset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_color, container, false);

        //Assigning views their id's
        sat = v.findViewById(R.id.sliderSaturation);
        cont = v.findViewById(R.id.sliderContrast);
        bright = v.findViewById(R.id.sliderBrightness);
        opac = v.findViewById(R.id.sliderOpacity);
        colorReset = v.findViewById(R.id.resetColor);

        //Creating view model obj for Item View Model class
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);


        colorReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opac.setValue(255f);
                cont.setValue(1f);
                bright.setValue(0f);
            }
        });

        //When saturation value changes
        sat.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                itemViewModel.setSaturation(value);
            }
        });

        //when Contrast value changes
        cont.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                itemViewModel.setContrast(value);
            }
        });

        //When brightness value changes
        bright.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider,float value, boolean fromUser) {
                itemViewModel.setBrightness(value);
            }
        });

        //When opacity value changes
        opac.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                int val = (int) value;
                itemViewModel.setOpacity(val);
            }
        });


        return v;
    }
}