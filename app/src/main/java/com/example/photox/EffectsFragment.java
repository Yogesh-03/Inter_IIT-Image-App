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
 * Use the {@link EffectsFragment# factory method to
 * create an instance of this fragment.
 */
public class EffectsFragment extends Fragment {

    Slider temp, sharp;
    ItemViewModel itemViewModel;
    TextView resetEffects;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_effects, container, false);

        //Assigning view their id's
        temp = v.findViewById(R.id.sliderTempereature);
        sharp = v.findViewById(R.id.sliderSharpness);
        resetEffects = v.findViewById(R.id.resetEffects);

        //crating view model obj
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        resetEffects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp.setValue(0f);
            }
        });


        //when tempereature value changes
        temp.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                itemViewModel.setTempereature(value);
            }
        });

        //when sharpness value changes
        sharp.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                itemViewModel.setSharpness(value);
            }
        });


        return v;
    }
}