package com.example.photox;

import android.graphics.Bitmap;
import android.net.Uri;

public class ImageModel {
    Uri img;

    public ImageModel(Uri img) {
        this.img = img;
    }

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }
}
