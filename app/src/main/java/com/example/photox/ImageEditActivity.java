package com.example.photox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uvstudio.him.photofilterlibrary.PhotoFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class ImageEditActivity extends AppCompatActivity {

    ImageFilterView imageView;
    ImageButton backBtn,imgOptions;
    Bitmap bitmap;
    BottomNavigationView bottomNavigationView;
    float blurScale, blurRadius;
    private ItemViewModel viewModel;
   public Bitmap blurredBitmap;
    float saturationValue;
    Bitmap currentImg;
    String image_path;
    float contrastValue, brightnessValue;
    int opacityValue;
    float tempValue, sharpValue;
    int pinkColorTint;
    PhotoFilter photoFilter;
    Bitmap currentBlurImg;
    Bitmap colorBitmap;



    //method to convert string path to bitmap, string path is taken from adapter class
    public Bitmap toBitmap() {

        Intent intent = getIntent();
        //Getting string path
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

        //Assigning views by id's
        imageView = findViewById(R.id.imageView);
        backBtn = findViewById(R.id.backToHomeFrag);
        bottomNavigationView = findViewById(R.id.bottomNavEditBar);
        imgOptions = findViewById(R.id.imageOptionsButton);

        photoFilter = new PhotoFilter();

        //setting image view with imported image bitmap
        currentImg = toBitmap();
        imageView.setImageBitmap(currentImg);
         //imageView.buildDrawingCache();
         //currentImg = imageView.getDrawingCache();

        //Assignign Perissions
        ActivityCompat.requestPermissions((ImageEditActivity.this), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions((ImageEditActivity.this), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        //Setting default value of contrastValue, brightnessValue and blurRadius
        contrastValue = 1f;
        brightnessValue = 0f;
        blurRadius = 0f;
        blurScale = 0f;
        opacityValue = 255;

        //Setting default fragment to be Blur Fragment()
        getSupportFragmentManager().beginTransaction().replace(R.id.editFragmentContainer, new BlurFragment()).commit();

        //Setting selected item to be Blur Icon in nav_edit_bar
        bottomNavigationView.setSelectedItemId(R.id.blurButton);

        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);

        currentBlurImg = currentImg;
        colorBitmap = currentImg;
        blurredBitmap = currentImg;

        //Getting blur scale value from view model
        viewModel.getSelectedItem().observe(this, value ->{
            blurScale = value;
            if (blurRadius ==0){
                if (currentImg != toBitmap()){
                imageView.setImageBitmap(currentBlurImg);
            } else {
                imageView.setImageBitmap(currentImg);}
            } else if (blurScale == 0 ){
                if (currentImg != toBitmap()){
                    imageView.setImageBitmap(currentBlurImg);
                } else {
                    imageView.setImageBitmap(currentImg);}
            }
            else {
                blurredBitmap = BlurBuilder.blur(this, currentBlurImg, blurScale, blurRadius);
                currentImg = blurredBitmap;
                imageView.setImageBitmap(currentImg);
            }

        });

        //Getting blur radius from ItemViewModel class
        viewModel.getSelectedRadius().observe(this, radius -> {
            blurRadius = radius;
            if (blurRadius ==0){
                if (currentImg != toBitmap()){
                    imageView.setImageBitmap(currentBlurImg);
                } else {
                    imageView.setImageBitmap(currentImg);}
            } else if (blurScale == 0){
                if (currentImg != toBitmap()){
                    imageView.setImageBitmap(currentBlurImg);
                } else {
                    imageView.setImageBitmap(currentImg);}
            }
            else {
                blurredBitmap = BlurBuilder.blur(this, currentBlurImg, blurScale, blurRadius);
                currentImg = blurredBitmap;
                imageView.setImageBitmap(currentImg);
            }
        });



        //Getting saturation value from ItemViewModel class
        /*viewModel.getSelectedSaturation().observe(this, sat ->{
            saturationValue = sat;
            Bitmap satbmp = SaturationBuilder.Saturate(currentImg,saturationValue);
            currentImg = satbmp;
            imageView.setImageBitmap(satbmp);

        });*/

        //Getting Contrast value from ItemViewModel class

        viewModel.getSelectedContrast().observe(this,con ->{
            contrastValue = con;
            //colorBitmap = ContrastAndBrightnessBuilder.changeBitmapContrastBrightness(blurredBitmap,contrastValue,brightnessValue);
            //currentImg = colorBitmap;
             colorBitmap = ContrastAndBrightnessBuilderAndOpacity
                    .changeBitmapContrastBrightness(blurredBitmap,contrastValue,brightnessValue,opacityValue);
            currentImg = colorBitmap;
            imageView.setImageBitmap(currentImg);
        });

        //Getting brightness value from ItemViewModel class
        viewModel.getSelectedBrightness().observe(this, bright -> {
            brightnessValue = bright;
             //colorBitmap = ContrastAndBrightnessBuilder.changeBitmapContrastBrightness(blurredBitmap,contrastValue,brightnessValue);
            //currentImg = colorBitmap;
             colorBitmap = ContrastAndBrightnessBuilderAndOpacity
                    .changeBitmapContrastBrightness(blurredBitmap,contrastValue,brightnessValue,opacityValue);
            currentImg = colorBitmap;
            imageView.setImageBitmap(currentImg);
        });

        //Getting Opacity value from ItemViewModel class
        viewModel.getSelectedItemOpacity().observe(this, op -> {
            opacityValue = op;
           //Bitmap opbmp = OpacityBuilder.makeOpacity(colorBitmap,opacityValue);
           // currentImg = opbmp;
             colorBitmap = ContrastAndBrightnessBuilderAndOpacity
                    .changeBitmapContrastBrightness(blurredBitmap,contrastValue,brightnessValue,opacityValue);
            currentImg = colorBitmap;
            imageView.setImageBitmap(currentImg);
        });

        //Getting Tempereature value from ItemViewModel class
        viewModel.getSelectedItemTempereature().observe(this, temp ->{
            tempValue = temp;
            Bitmap tempbmp = TempereatureBuilder.doTemperature(colorBitmap, tempValue);
            currentImg = tempbmp;
            imageView.setImageBitmap(currentImg);
        });

        //Getting Sharpness value from ItemViewModel class
        viewModel.getSelectedItemSharpness().observe(this, shp -> {
            sharpValue = shp;
            Bitmap sharpbmp = SharpenBuilder.doSharpen(this,blurredBitmap,sharpValue);
            currentImg = sharpbmp;
            imageView.setImageBitmap(sharpbmp);
        });

        //Getting Filter One from ItemViewModel class
        viewModel.getSelectedItemPinkTint().observe(this, in ->{
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.one(getApplicationContext(), toBitmap()));
            //Bitmap pinktintbmp = TintBuilder.tintImage(blurredBitmap, pinkColorTint);
            //currentImg = pinktintbmp;
            //imageView.setImageBitmap(pinktintbmp);

        });

        //Getting Filter Two from ItemViewModel class
        viewModel.getSelectedItemTintTwo().observe(this, in ->{
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.two(getApplicationContext(),toBitmap()));
        });

        //Getting Filter Three from ItemViewModel class
        viewModel.getSelectedItemTintThree().observe(this, in ->{
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.three(getApplicationContext(),toBitmap()));
        });

        //Getting Filter Four from ItemViewModel class
        viewModel.getSelectedItemTintFour().observe(this, in -> {
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.four(getApplicationContext(), toBitmap()));
        });

        //Getting Filter Five from ItemViewModel class
        viewModel.getSelectedItemTintFive().observe(this, in -> {
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.five(getApplicationContext(), toBitmap()));
        });

        //Getting Filter Six from ItemViewModel class
        viewModel.getSelectedItemTintSix().observe(this, in -> {
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.six(getApplicationContext(), toBitmap()));
        });

        //Getting Filter Seven from ItemViewModel class
        viewModel.getSelectedItemTintSeven().observe(this, in -> {
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.seven(getApplicationContext(), toBitmap()));
        });

        //Getting Filter Eight from ItemViewModel class
        viewModel.getSelectedItemTintEight().observe(this, in -> {
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.eight(getApplicationContext(), toBitmap()));
        });

        //Getting Filter Nine from ItemViewModel class
        viewModel.getSelectedItemTintNine().observe(this, in -> {
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.nine(getApplicationContext(), toBitmap()));
        });

        //Getting Filter Ten from ItemViewModel class
        viewModel.getSelectedItemTintTen().observe(this, in -> {
            pinkColorTint = in;
            imageView.setImageBitmap(photoFilter.ten(getApplicationContext(), toBitmap()));
        });

        //Getting roatation right value and rotating the bitmap
        viewModel.getSelectedItemRotation().observe(this, in ->{
            Matrix matrix = new Matrix();
            matrix.postRotate(in);

            Bitmap rotated = Bitmap.createBitmap(currentImg, 0, 0, currentImg.getWidth(), currentImg.getHeight(),
                    matrix, true);
            currentImg = rotated;

            imageView.setImageBitmap(currentImg);
        });

        //Getting roatation left value and rotating the bitmap
        viewModel.getSelectedItemRotationLeft().observe(this, in -> {
            Matrix matrix = new Matrix();
            matrix.postRotate(in);

            Bitmap rotated = Bitmap.createBitmap(currentImg, 0, 0, currentImg.getWidth(), currentImg.getHeight(),
                    matrix, true);
            currentImg = rotated;

            imageView.setImageBitmap(currentImg);

        });
        viewModel.getSelectedItemOriginal().observe(this,in -> {
            imageView.setImageBitmap(toBitmap());
        });


        //Setting bottom navigation View
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
                               // addImageToGallery();
                                SaveImage();
                                //MediaStore.Images.Media.insertImage(getContentResolver(), toBitmap(), "Title" , "Desc");
                                //Toast.makeText(getApplicationContext(), "Save To Gallery", Toast.LENGTH_SHORT).show();
                                break;

                            case R.id.shareMenuBtn:
                                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
                                Bitmap bms = bitmapDrawable.getBitmap();
                                //shareImage(blurredBitmap);
                                shareImage(bms);
                        }
                        return false;
                    }
                });
                popupMenu.show();
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


    private void SaveImage() {

        String root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/photoX");

          /*  int w = toBitmap().getWidth();
            int h = toBitmap().getHeight();*/

        BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bm = bitmapDrawable.getBitmap();
        /*bm.setHeight(h);
        bm.setWidth(w);*/
       boolean wasSuccessful =  myDir.mkdirs();
        if (!wasSuccessful){
            Log.d("TAG","was not successful");
        }

        Random generator = new Random();

        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 90, out);
            // sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
            //     Uri.parse("file://"+ Environment.getExternalStorageDirectory())));
            out.flush();
            out.close();
            Toast.makeText(getApplicationContext(),"Saved to Gallery", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
// Tell the media scanner about the new file so that it is
// immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }

    public void shareImage(Bitmap bmpp){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("*image/jpeg");
        Uri bmpUri;
        bmpUri = saveShareImage(bmpp, getApplicationContext());
        share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        share.putExtra(Intent.EXTRA_STREAM, bmpUri);
        share.putExtra(Intent.EXTRA_SUBJECT,"photoX App");
        share.putExtra(Intent.EXTRA_TEXT, " ");
        startActivity(Intent.createChooser(share, "Share Content"));
    }

    private  static   Uri saveShareImage(Bitmap bmpp, Context context){
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
    }

}