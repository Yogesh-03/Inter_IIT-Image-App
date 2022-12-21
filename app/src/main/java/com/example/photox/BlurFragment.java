package com.example.photox;

import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.slider.RangeSlider;
import com.google.android.material.slider.Slider;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlurFragment# method to
 * create an instance of this fragment.
 */
public class BlurFragment extends Fragment {
    float sclae, radius;
    Slider scaleSlider;
    RangeSlider sliderRadius;
    ItemViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_blur, container, false);

        // Assigning ID's to Views
        scaleSlider = v.findViewById(R.id.sliderBlurScale);
        sliderRadius = v.findViewById(R.id.sliderBlurRadius);

        //Creating a viewModel Object
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        //Adding data to ViewModel class
        // When slider value changes
        scaleSlider.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                //Adding data to ViewModel class
                viewModel.setScale(value);
                //String string = String.valueOf(value);
                //Log.d("Value", string);


            }
        });


        // When slider value changes
        sliderRadius.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                //Adding data to ViewModel class
                viewModel.setRadius(value);

            }
        });

        return v;
    }
}