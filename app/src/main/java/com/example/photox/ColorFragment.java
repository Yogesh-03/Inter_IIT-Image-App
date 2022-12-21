package com.example.photox;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_color, container, false);
        sat = v.findViewById(R.id.sliderSaturation);
        cont = v.findViewById(R.id.sliderContrast);
        bright = v.findViewById(R.id.sliderBrightness);
        opac = v.findViewById(R.id.sliderOpacity);

        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        sat.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                itemViewModel.setSaturation(value);

            }
        });
        cont.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                itemViewModel.setContrast(value);
            }
        });
        bright.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider,float value, boolean fromUser) {
                itemViewModel.setBrightness(value);
            }
        });

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