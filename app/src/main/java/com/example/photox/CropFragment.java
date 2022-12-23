package com.example.photox;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CropFragment# factory method to
 * create an instance of this fragment.
 */
public class CropFragment extends Fragment {

    ImageView rotate, rotateLeft;
    int angle;
    int angleLeft;
    ItemViewModel itemViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_crop, container, false);

        //Assigning views by their id's
        rotate = v.findViewById(R.id.imageViewRotate);
        rotateLeft = v.findViewById(R.id.imageViewRotateLeft);

        //Initialing angle of rotation right and left respct.
        angle = 90;
        angleLeft = -90;

        //creatin view model obj of Item View model class
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        //Getting clicked on rotate right image view
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setRotation(angle);
                rotate.requestFocus();
            }
        });

        //Getting clicked on rotate left image view
        rotateLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setRotationLeft(angleLeft);
                rotateLeft.requestFocus();
            }
        });
        return v;
    }
}