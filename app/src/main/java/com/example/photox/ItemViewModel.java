package com.example.photox;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ItemViewModel extends ViewModel {

    // @MutableLiveData to get value to send the scale float value from Blur Fragment
    private final MutableLiveData<Float>  selectedItem = new MutableLiveData<Float>();
    public void setScale(float value){
        selectedItem.setValue(value);
    }

    public LiveData<Float> getSelectedItem(){
        return selectedItem;
    }

    // @MutableLiveData to get value to send the Radius float value from Blur Fragment
    private final MutableLiveData<Float> selectedItemRadius = new MutableLiveData<Float>();
    public void setRadius(float radius){
        selectedItemRadius.setValue(radius);
    }
    public  LiveData<Float> getSelectedRadius(){
        return selectedItemRadius;
    }

    // @MutableLiveData to get value to send the Saturation float value from Color Fragment
    private final MutableLiveData<Float> selectedItemSaturation = new MutableLiveData<Float>();

    public void setSaturation(Float sat){
        selectedItemSaturation.setValue(sat);
    }
    public LiveData<Float> getSelectedSaturation(){
        return selectedItemSaturation;
    }

    // @MutableLiveData to get value to send the Contrast float value from Color Fragment
    private final MutableLiveData<Float> selectedItemContrast = new MutableLiveData<Float>();
    public void setContrast(Float con){
        selectedItemContrast.setValue(con);
    }
    public  LiveData<Float> getSelectedContrast(){
        return selectedItemContrast;
    }

    // @MutableLiveData to get value to send the Brightness float value from Color Fragment
    private final MutableLiveData<Float> selectedItemBrightness = new MutableLiveData<Float>();
    public void setBrightness(Float bright){
        selectedItemBrightness.setValue(bright);
    }
    public  LiveData<Float> getSelectedBrightness(){
        return selectedItemBrightness;
    }

    // @MutableLiveData to get value to send the Opacity int value from Color Fragment
    private final MutableLiveData<Integer> selectedItemOpacity = new MutableLiveData<Integer>();

    public void setOpacity(int op){
        selectedItemOpacity.setValue(op);
    }
    public LiveData<Integer> getSelectedItemOpacity(){
        return selectedItemOpacity;
    }

    // @MutableLiveData to get value to send the Tempereature flaat value from Effects Fragment
    private final MutableLiveData<Float> selectedItemTempereature = new MutableLiveData<Float>();

    public void setTempereature(Float tem){
        selectedItemTempereature.setValue(tem);
    }

    public  LiveData<Float> getSelectedItemTempereature(){
        return selectedItemTempereature;
    }


    // @MutableLiveData to get value to send the Sharpness flaat value from Effects Fragment
    private final MutableLiveData<Float> selectedItemSharpness = new MutableLiveData<Float>();

    public void setSharpness(Float shp){
        selectedItemSharpness.setValue(shp);
    }

    public  LiveData<Float> getSelectedItemSharpness(){
        return selectedItemSaturation;
    }


}
