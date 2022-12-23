package com.example.photox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class SaturationBuilder {
    public static Bitmap Saturate( Bitmap image, float f){

        int width, height;
         width = Math.round(image.getWidth());
         height = Math.round(image.getHeight());

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter mat = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(mat);
        c.drawBitmap(image, 0, 0, paint);
        return bmpGrayscale;
    }
}
