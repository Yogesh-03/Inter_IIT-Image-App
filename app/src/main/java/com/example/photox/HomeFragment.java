package com.example.photox;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<ImageModel> arrImages = new ArrayList<>();
    ImageButton imageImport;
    int SELECT_IMAGE_CODE = 1;
    int CAMERA_REQ_CODE = 2;
    Bitmap image;
    ImageButton imageCamera;
    RecyclerImageAdapter recyclerImageAdapter;
    Bitmap galleryBitmap;


   /* public Bitmap getBitmap(Uri uri) {
        Bitmap galleryBitmap = null;

        String path = uri.getPath();
        try
        {galleryBitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver() , Uri.parse(path)); }
        catch (Exception e)
        {
            //handle exception
        }
        return galleryBitmap;

    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        //Assigning view their id's
        recyclerView = v.findViewById(R.id.recyclerImages);
        imageImport = v.findViewById(R.id.imageImport);
        imageCamera = v.findViewById(R.id.imageImportCamera);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));



        recyclerImageAdapter = new RecyclerImageAdapter(getContext(), arrImages);

        //Getting clicked on gallery import image button (top left corner)
        imageCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCamera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iCamera, CAMERA_REQ_CODE);
            }
        });


        //Getting clicked on camera import image button (top right corner)
        imageImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);

            }
        });


        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode ==SELECT_IMAGE_CODE){

            assert data != null;
            Uri uri = data.getData();
            //assert data != null;
            //image = (Bitmap) data.getExtras().get("data");
            // ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //  image.compress(Bitmap.CompressFormat.JPEG,90,bytes);
            arrImages.add(new ImageModel(uri));
              //saveToGallery();
            recyclerView.setAdapter(recyclerImageAdapter);
        }

        if (resultCode== Activity.RESULT_OK){
            if (requestCode == CAMERA_REQ_CODE){

                assert data != null;
                Bitmap img = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                img.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
               // String path = MediaStore.Images.Media.insertImage(getContext().getContentResolver(), img, "Title", null);
                String path = MediaStore.Images.Media.insertImage(this.getContext().getContentResolver(), img,"Tite", null);
                Uri uri = Uri.parse(path);
                arrImages.add(new ImageModel(uri));
                recyclerView.setAdapter(recyclerImageAdapter);

            }
        }

    }



}