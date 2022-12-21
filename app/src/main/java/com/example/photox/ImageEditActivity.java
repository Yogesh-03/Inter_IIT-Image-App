package com.example.photox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.InetAddresses;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class ImageEditActivity extends AppCompatActivity {
    ImageView imageView, imageView2;
    ImageButton backBtn,imgOptions;
    Bitmap bitmap;
    BottomNavigationView bottomNavigationView;
    float blurScale, blurRadius;
    private ItemViewModel viewModel;
   public Bitmap blurredBitmap, saturatedBitmap;
    float saturationValue;
    Bitmap currentImg;
    String image_path;
    float contrastValue, brightnessValue;
    int opacityValue;
    float tempValue, sharpValue;


    public Bitmap toBitmap() {

        Intent intent = getIntent();
        image_path = intent.getStringExtra("image");
        try
        {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver() , Uri.parse(image_path)); }
        catch (Exception e)
        {
         //handle exception
        }
        return bitmap;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_edit);

        imageView = findViewById(R.id.imageView);
        //imageView2 = findViewById(R.id.imageView2);
        backBtn = findViewById(R.id.backToHomeFrag);
        bottomNavigationView = findViewById(R.id.bottomNavEditBar);
        imgOptions = findViewById(R.id.imageOptionsButton);


         imageView.setImageBitmap(toBitmap());
         //imageView.buildDrawingCache();
         //currentImg = imageView.getDrawingCache();

        ActivityCompat.requestPermissions((ImageEditActivity.this), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions((ImageEditActivity.this), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        // Getting pop up menu when clicked on three dots in top right corner
        imgOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Pop menu created
                PopupMenu popupMenu = new PopupMenu(ImageEditActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.image_options_menu, popupMenu.getMenu());

                // Getting clicked on pop up menu items
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.saveMenuBtn:
                                //addImageToGallery();
                                //MediaStore.Images.Media.insertImage(getContentResolver(), toBitmap(), "Title" , "Desc");
                                //Toast.makeText(getApplicationContext(), "Save To Gallery", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.shareMenuBtn:
                                //shareImage(blurredBitmap);
                        }
                        return false;
                    }
                });
                 popupMenu.show();
            }
        });
        contrastValue = 1f;
        brightnessValue = 0;


        getSupportFragmentManager().beginTransaction().replace(R.id.editFragmentContainer, new BlurFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.blurButton);

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        blurRadius = 1f;
        viewModel.getSelectedItem().observe(this, value ->{
            blurScale = value;


            if (blurScale !=0) {
                blurredBitmap = BlurBuilder.blur(this, toBitmap(), blurScale, blurRadius);
                currentImg = blurredBitmap;
                imageView.setImageBitmap(currentImg);
            } else {
                imageView.setImageBitmap(toBitmap());
            }

        });

        viewModel.getSelectedRadius().observe(this, radius -> {
            blurRadius = radius;

            if (blurScale !=0) {
                blurredBitmap = BlurBuilder.blur(this, toBitmap(), blurScale, blurRadius);
                currentImg = blurredBitmap;
                imageView.setImageBitmap(currentImg);
            } else {
                imageView.setImageBitmap(toBitmap());
            }
        });

        viewModel.getSelectedSaturation().observe(this, sat ->{
            saturationValue = sat;
            Bitmap satbmp = SaturationBuilder.Saturate(this,blurredBitmap,sat);
            currentImg = satbmp;
            imageView.setImageBitmap(satbmp);

        });
        viewModel.getSelectedContrast().observe(this,con ->{
            contrastValue = con;
            Bitmap conbmp = ContrastAndBrightnessBuilder.changeBitmapContrastBrightness(blurredBitmap,contrastValue,brightnessValue);
            currentImg = conbmp;
            imageView.setImageBitmap(conbmp);
        });

        viewModel.getSelectedBrightness().observe(this, bright -> {
            brightnessValue = bright;
            Bitmap brightbmp = ContrastAndBrightnessBuilder.changeBitmapContrastBrightness(blurredBitmap,contrastValue,brightnessValue);
            currentImg = brightbmp;
            imageView.setImageBitmap(brightbmp);
        });

        viewModel.getSelectedItemOpacity().observe(this, op -> {
            opacityValue = op;
            Bitmap opbmp = OpacityBuilder.makeOpacity(blurredBitmap,op);
            currentImg = opbmp;
            imageView.setImageBitmap(opbmp);
        });

        viewModel.getSelectedItemTempereature().observe(this, temp ->{
            tempValue = temp;
            Bitmap tempbmp = TempereatureBuilder.doTemperature(blurredBitmap, tempValue);
            currentImg = tempbmp;
            imageView.setImageBitmap(tempbmp);
        });

        viewModel.getSelectedItemSharpness().observe(this, shp -> {
            sharpValue = shp;
            Bitmap sharpbmp = SharpenBuilder.doSharpen(this,blurredBitmap,sharpValue);
            currentImg = sharpbmp;
            imageView.setImageBitmap(sharpbmp);
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.blurButton:
                        fragment = new BlurFragment();

                        break;

                    case R.id.filterButton:
                        fragment = new FilterFragment();
                        break;

                    case  R.id.colorButton:
                        fragment = new ColorFragment();
                        break;

                    case R.id. cropButton:
                        fragment = new CropFragment();
                        break;

                    case R.id.effectsButton:
                        fragment = new EffectsFragment();
                        break;
                }

                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.editFragmentContainer, fragment).commit();
                return true;
            }
        });

         //blurredBitmap = BlurBuilder.blur( this, toBitmap(), 0.4f, 5.1f);
        //imageView2.setImageBitmap(blurredBitmap);



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent   intent = new Intent(ImageEditActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }



   /* private   void addImageToGallery() {

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap myBitmap = bitmapDrawable.getBitmap();

        *//*Bitmap myBitmap;
        myBitmap = BitmapFactory.decodeResource(getResources(), R.id.imageView);
        myBitmap = ((BitmapDrawable) imageView.getDrawable()).toBitmap();*//*

        FileOutputStream outputStream = null;
        File file = Environment.getExternalStorageDirectory();
        File dir = new File(file.getAbsolutePath()+"/photoX");
        //dir.mkdirs();
        if (!file.exists()){
            boolean mkdir = dir.mkdirs();
            if (!mkdir){
                Log.e("TAG","Directory creation failed");
            }
        }

        @SuppressLint("DefaultLocale") String filename =  String.format("%d.jpg", System.currentTimeMillis());
        File outFile =  new File(dir, filename);
        try {
            outputStream = new FileOutputStream(outFile);
        } catch (Exception e){
            e.printStackTrace();
        }
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , outputStream);
        try {
            assert outputStream != null;
            outputStream.flush();
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }*/

   /* public void shareImage(Bitmap bmpp){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("*image/jpeg");
        Uri bmpUri;
        bmpUri = saveShareImage(bmpp, getApplicationContext());
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.putExtra(Intent.EXTRA_STREAM, bmpUri);
        share.putExtra(Intent.EXTRA_SUBJECT,"photoX App");
        share.putExtra(Intent.EXTRA_TEXT, "Hello");
        startActivity(Intent.createChooser(share, "Share Content"));
    }*/

   /* private  static   Uri saveShareImage(Bitmap bmpp, Context context){
        File imageFolder = new File(context.getCacheDir(),"images");
        Uri uri = null;
        Date currentTime = Calendar.getInstance().getTime();
        try {
            if (!imageFolder.exists()){
                boolean mkdir = imageFolder.mkdirs();
                if (!mkdir){
                    Log.e("TAG","Directory creation failed");
                }
            }
            File file = new File(imageFolder,"Image.jpg");

            FileOutputStream stream = new FileOutputStream(file);
            bmpp.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
            uri = FileProvider.getUriForFile(context.getApplicationContext(),BuildConfig.APPLICATION_ID+".provider",file);

        } catch (IOException e){
            e.printStackTrace();
        }
        return  uri;
    }*/


}