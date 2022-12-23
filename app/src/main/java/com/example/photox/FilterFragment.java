package com.example.photox;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.uvstudio.him.photofilterlibrary.PhotoFilter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilterFragment# factory method to
 * create an instance of this fragment.
 */
public class FilterFragment extends Fragment {
    LinearLayout pipnkTint, tintTwo, tintThree, tintFour, tintFive
            ,tintSix, tintSeven, tintEight, tintNine, tintTen;
    LinearLayout imageOriginal;
    ItemViewModel itemViewModel;
    ImageView imageViewOne, imageViewTwo, imageViewThee, imageViewFour
            , imageViewFive, imageViewSix, imageViewSeven, imageViewEight
            ,imageViewNine, imageViewTen;
    ImageView imageViewOriginal;
    Bitmap sampleImage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_filter, container, false);

        //Assigning View's by their id's
        //Assignign images id's
        imageViewOne = v.findViewById(R.id.imageViewOne);
        imageViewTwo = v.findViewById(R.id.imageViewTwo);
        imageViewThee = v.findViewById(R.id.imageViewThree);
        imageViewFour = v.findViewById(R.id.imageViewFour);
        imageViewFive = v.findViewById(R.id.imageViewFive);
        imageViewSix = v.findViewById(R.id.imageViewSix);
        imageViewSeven = v.findViewById(R.id.imageViewSeven);
        imageViewEight = v.findViewById(R.id.imageViewEight);
        imageViewNine = v.findViewById(R.id.imageViewNine);
        imageViewTen = v.findViewById(R.id.imageViewTen);
        imageViewOriginal = v.findViewById(R.id.imageViewOriginal);

        //Assigning linear layout id's
        pipnkTint = v.findViewById(R.id.pintTintBtn);
        tintTwo = v.findViewById(R.id.tintTwo);
        tintThree = v.findViewById(R.id.tintThree);
        tintFour = v.findViewById(R.id.tintFour);
        tintFive  = v.findViewById(R.id.tintFive);
        tintSix = v.findViewById(R.id.tintSix);
        tintSeven = v.findViewById(R.id.tintSeven);
        tintEight = v.findViewById(R.id.tintEight);
        tintNine = v.findViewById(R.id.tintNine);
        tintTen = v.findViewById(R.id.tintTen);
        imageOriginal = v.findViewById(R.id.imageOriginal);


        int pinkColorValue = Color.parseColor("#ff99dd");
        PhotoFilter photoFilter = new PhotoFilter();

        //Converting sample image to bitmap
        sampleImage = BitmapFactory.decodeResource(getResources(),R.drawable.sample_img);

        //settings sample image to image View with filters
        imageViewOne.setImageBitmap(photoFilter.one(getContext(),sampleImage));
        imageViewTwo.setImageBitmap(photoFilter.two(getContext(),sampleImage));
        imageViewThee.setImageBitmap(photoFilter.three(getContext(),sampleImage));
        imageViewFour.setImageBitmap(photoFilter.four(getContext(),sampleImage));
        imageViewFive.setImageBitmap(photoFilter.five(getContext(),sampleImage));
        imageViewSix.setImageBitmap(photoFilter.six(getContext(),sampleImage));
        imageViewSeven.setImageBitmap(photoFilter.seven(getContext(),sampleImage));
        imageViewEight.setImageBitmap(photoFilter.eight(getContext(),sampleImage));
        imageViewNine.setImageBitmap(photoFilter.nine(getContext(),sampleImage));
        imageViewTen.setImageBitmap(photoFilter.ten(getContext(),sampleImage));

        //creatin new Viem Model for Item View Model class
        itemViewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        //Getting clicked on Filter One(pinkTint)
        pipnkTint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setPinkTint(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundResource(R.drawable.filter_layout_border);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        //Getting clicked on tint Two(imageViewTwo)
        tintTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintTwo(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundResource(R.drawable.filter_layout_border);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        ////Getting clicked on tint Thee(imageViewThree)
        tintThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintThree(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundResource(R.drawable.filter_layout_border);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        //Getting clicked on tint Four(imageView Four)
        tintFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintFour(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundResource(R.drawable.filter_layout_border);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        //Getting clicked on tint Five (imageView Five)
        tintFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintFive(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundResource(R.drawable.filter_layout_border);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        //Getting clicked on tint Six (imageView Six)
        tintSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintSix(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundResource(R.drawable.filter_layout_border);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });


        //Getting clicked on tint Seven (imageView Seven)
        tintSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintSeven(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundResource(R.drawable.filter_layout_border);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        //Getting clicked on tint Eight (imageView Eight)
        tintEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintEight(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundResource(R.drawable.filter_layout_border);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        //Getting clicked on tint Nine (imageView Nine)
        tintNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintNine(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundResource(R.drawable.filter_layout_border);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        //Getting clicked on tint Ten (imageView Ten)
        tintTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setTintTen(pinkColorValue);

                imageOriginal.setBackgroundColor(Color.TRANSPARENT);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundResource(R.drawable.filter_layout_border);
            }
        });

        imageOriginal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemViewModel.setOriginal(pinkColorValue);

                imageOriginal.setBackgroundResource(R.drawable.filter_layout_border);
                pipnkTint.setBackgroundColor(Color.TRANSPARENT);
                tintTwo.setBackgroundColor(Color.TRANSPARENT);
                tintThree.setBackgroundColor(Color.TRANSPARENT);
                tintFour.setBackgroundColor(Color.TRANSPARENT);
                tintFive.setBackgroundColor(Color.TRANSPARENT);
                tintSix.setBackgroundColor(Color.TRANSPARENT);
                tintSeven.setBackgroundColor(Color.TRANSPARENT);
                tintEight.setBackgroundColor(Color.TRANSPARENT);
                tintNine.setBackgroundColor(Color.TRANSPARENT);
                tintTen.setBackgroundColor(Color.TRANSPARENT);
            }
        });

        return v;
    }
}