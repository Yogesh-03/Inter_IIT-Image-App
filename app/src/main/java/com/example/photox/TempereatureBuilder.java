package com.example.photox;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class TempereatureBuilder {

    public static Bitmap doTemperature(Bitmap bmp, float temp){

        ColorMatrix cm = new ColorMatrix(new float[]
                {
                        1, 0, 0, temp, 0,
                        0, 1, 0, temp/2, 0,
                        0, 0, 1, temp/4, 0,
                        0, 0, 0, 1, 0

                });

        Bitmap ret = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), bmp.getConfig());
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(cm));
        canvas.drawBitmap(bmp, 0, 0, paint);
        return ret;
    }

    }
